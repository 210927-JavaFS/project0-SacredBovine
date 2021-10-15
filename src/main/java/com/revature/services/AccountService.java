package com.revature.services;

import com.revature.controllers.AccountHolderController;
import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;
import com.revature.models.User;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountService {

	private static Logger log = LoggerFactory.getLogger(AccountService.class);
	private final double newAccountInitialBalance = 0.00;
 	private AccountDAO accountDAO = new AccountDAOImpl();

// AccountHolder and Admin use Methods.
	public boolean deposit(Account account, double amount){
 		double balance = account.getBalance();
 		if(amount > 0 && 100*amount%1 == 0) {
 			balance += amount;
 			account.setBalance(balance);
 			return accountDAO.updateAccount(account);
 		}
 		return false;
 	} 
	public boolean withdraw(Account account, double amount){
 		double balance = account.getBalance();
 		if(amount > 0 && 100*amount%1 == 0) {
 			if (balance-amount >= 0) {
 				balance -= amount;
 		 		account.setBalance(balance);		 		
 		 		System.out.println(toString(account));
 		 		return accountDAO.updateAccount(account);
 	 		}
 		}
 		return false;
  	}
  	public boolean transfer(Account source, Account destination, double amount) {
 		if(amount > 0 && 100*amount%1 == 0) {
 			if(withdraw(source, amount)) {
 				if(deposit(destination, amount)) {
 					return true;
 				}
 				deposit(source, amount);
 			} 
 		}
 		return false;
 	}
 	
// Teller and Admin use methods
  	public boolean createAccount(User user, int type){ 
  		switch (type) {
 			case 1: {
 				Account account = new Account(newAccountInitialBalance, "checking");
 				int accountID = accountDAO.addAccount(account);
 				if(accountID != 0) {
 					account.setID(accountID);
 					if(accountDAO.addUserAccount(user, account)) {
 						return true;
 					} else {
 						log.error("Account could not be assigned to Users");
 					}
 				} else {
 					log.error("System could not create new Account Entry");
 				}
 				break;
 			}
 			case 2: {
 				Account account = new Account(newAccountInitialBalance, "savings");
 				int accountID = accountDAO.addAccount(account);
 				if(accountID != 0) {
 					account.setID(accountID);
 					if(accountDAO.addUserAccount(user, account)) {
 						return true;
 					} else {
 						log.error("Account could not be assigned to Users");
 					}
 				} else {
 					log.error("System could not create new Account Entry");
 				}
 				break;
 			}
 			case 3:{
 				Account account = new Account(newAccountInitialBalance, "joint");
 				int accountID = accountDAO.addAccount(account);
 				if(accountID != 0) {
 					account.setID(accountID);
 					if(accountDAO.addUserAccount(user, account)) {
 						return true;
 					} else {
 						log.error("Account could not be assigned to Users");
 					}
 				} else {
 					log.error("System could not create new Account Entry");
 				}
 				break;
 			}
  		}
 	  	System.out.println("Account service failed to create a new account.");
 
 		return false;
 	}
 	public Account getByID(int ID) {
 		return accountDAO.GetByID(ID);
 	}
  	List<Account> getAllByID(int userID){
  		return accountDAO.getAllByID(userID);
  	}

// Admin only methods
 	public boolean closeAccount(Account account){  
		 return accountDAO.deleteAccount(account);
	 }
 	
  // Controller Use Methods
 	public String toString(Account account) {
 		String display = account.getType() + " account : "+ String.valueOf(account.getID())+": $"+String.valueOf(account.getBalance());
 		return display;
 	}
 	public List<Account> getUserAccounts(User user) {
 		return(accountDAO.getAllByID(user.getID()));
 	}
}
