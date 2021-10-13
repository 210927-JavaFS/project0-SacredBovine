package com.revature.services;

import com.revature.Driver;
import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;
import com.revature.models.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
	//MDC.put("AccountService:");
	private static Logger log = LoggerFactory.getLogger(AccountService.class);
	private final double newAccountInitialBalance = 0.00;
 	private AccountDAO accountDAO = new AccountDAOImpl();

// AccountHolder and Admin use Methods.
	public boolean deposit(Account account, double amount){
 		double balance = account.getBalance();
 		if(amount > 0) {
 			balance += amount;
 			account.setBalance(balance);
 			return accountDAO.updateAccount(account);
 		}
 		return false;
 	} 
	
  	public boolean withdraw(Account account, double amount){
 		double balance = account.getBalance();
 		if(amount > 0) {
 			if (balance-amount > 0) {
 				balance -= amount;
 		 		account.setBalance(balance);		 		
 		 		System.out.println(toString(account));
 		 		return accountDAO.updateAccount(account);
 	 		}
 		}
 		return false;
  	}
  	
 	public boolean transfer(Account source, Account destination, double amount) {
 		if(withdraw(source, amount)) {
 			if(deposit(destination, amount)) {
 				return true;
 			}
 			deposit(source, amount);
 		} 
 		return false;
 	}
 	
// Teller and Admin use methods
  	public Account createAccount(User user, int type){ // Should create a new Bank account with proper type checking or savings with balance $0.00 and assign to user
  		switch (type) {
 			case 1: {
 				Account account = new Account(newAccountInitialBalance, "checking");
 				int accountID = accountDAO.addAccount(account);
 				if(accountID != 0) {
 					account.setID(accountID);
 					if(accountDAO.addUserAccount(user, account)) {
 						return account;
 					} else {
 						System.out.println(" ERROR: Account could not be assigned to Users");
 					}
 				} else {
 					System.out.println(" ERROR: System could not create new Account Entry");
 				}
 				break;
 			}
 			case 2: {
 				Account account = new Account(newAccountInitialBalance, "savings");
 				int accountID = accountDAO.addAccount(account);
 				if(accountID != 0) {
 					account.setID(accountID);
 					if(accountDAO.addUserAccount(user, account)) {
 						return account;
 					} else {
 						System.out.println(" ERROR: Account could not be assigned to Users");
 					}
 				} else {
 					System.out.println(" ERROR: System could not create new Account Entry");
 				}
 				break;
 			}
 			case 3:{
 				Account account = new Account(newAccountInitialBalance, "joint");
 				int accountID = accountDAO.addAccount(account);
 				if(accountID != 0) {
 					account.setID(accountID);
 					if(accountDAO.addUserAccount(user, account)) {
 						return account;
 					} else {
 						System.out.println(" ERROR: Account could not be assigned to Users");
 					}
 				} else {
 					System.out.println(" ERROR: System could not create new Account Entry");
 				}
 				break;
 			}
  		}
 	  	System.out.println("Account service failed to create a new account.");
  		Account account = new Account() ;
 		return null;
 	}
 	
// Admin only methods
 	
 	public void closeAccount(Account account, Account destination){  // Transfer all funds to destination and close account.
 		 transfer(account,destination, account.getBalance());
 		 if(accountDAO.updateAccount(destination)) {
 		 // delete account
 		 // update DB
 		 }
 	}
 	
 	public void closeAccount(Account account){  // Withdraw all funds to destination and close account.
		 
 		withdraw(account, account.getBalance());
		 
		 // update DB
	 }
 	
 	// Stretch goal methods
 	
 	// public void setPrimaryAccountHolder(Account account, User user){} Should set primary account holder (change of hands or joint account implementation) maybe employee 
 	// public void setSecondaryAccountHolder(Account account, User user){} "  "
 	
  // Controller Use Methods
 	public String toString(Account account) {
 		String display = account.getType() + " account : "+ String.valueOf(account.getID())+": $"+String.valueOf(account.getBalance());
 		return display;
 	}
 	 
 	public List<Account> getUserAccounts(User user) {
 		return(accountDAO.getAllByID(user.getID()));
 	}
}
