package com.revature.services;

import com.revature.Driver;
import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import java.util.ArrayList;

public class AccountService {
	private static Logger log = LoggerFactory.getLogger(AccountService.class);
	//MDC.put("AccountService:");
	private final double newAccountInitialBalance = 0.00;
 	private AccountDAO accountDAO = new AccountDAOImpl();

 // AccountHolder and Admin use Methods.
	public boolean deposit(Account account, double amount){
 		double balance = account.getBalance();
 		if(amount > 0) {
 			balance += amount;
 			account.setBalance(balance);
 			if(accountDAO.updateAccount(account)) {
 				return true;
 			}
 		}
 		return false;
 	} 
  	public boolean withdraw(Account account, double amount){
 		double balance = account.getBalance();
 		if(amount > 0) {
 			if (balance-amount > 0) {
 				balance -= amount;
 		 		account.setBalance(balance);
 		 		
 		 		//finalize account transaction in DB
 		 		System.out.println(toString(account));
 		 		if(accountDAO.updateAccount(account)) {
 	 				return true;
 		 		}
 			}
 		}
 		return false;
  	}
 	public void transfer( ArrayList<Account> accounts, double amount) {
 		Account source = accounts.get(0);
 		Account destination = accounts.get(1);
 		Double initSourceBalance = source.getBalance();
 		Double initDestinationBalance = destination.getBalance();
 		Double newSourceBalance = initSourceBalance-amount;
 		Double newDestinationBalance = initDestinationBalance+amount;
 		source.setBalance(newSourceBalance);
 		destination.setBalance(newDestinationBalance);
 		accounts.set(0, source);
 		accounts.set(1,  destination);
 		
 		// Update DB
 	}
 	
// Teller and Admin use methods
  	public Account createAccount(int type){ // Should create a new Bank account with proper type checking or savings with balance $0.00 and assign to user
  		switch (type) {
 			case 1: {
 				Account account = new Account(newAccountInitialBalance, "checking");
 				account.setID(accountDAO.addAccount(account));
 				return account;
 			}
 			case 2: {
 				Account account = new Account(newAccountInitialBalance, "savings");
 	  			account.setID(accountDAO.addAccount(account));
 	  			return account;
 			}
 			case 3:{
 				Account account = new Account(newAccountInitialBalance, "joint");
 	  			account.setID(accountDAO.addAccount(account));
 	  			return account;
 			}
  		}
 	  	System.out.println("Account service failed to creat a new account.");
  		Account account = new Account() ;
 		return account;
 	}
 	
// Admin only methods
 	
 	public void closeAccount(Account account, Account destination){  // Transfer all funds to destination and close account.
 		 ArrayList<Account> transfers = new ArrayList<>();
 		 transfers.add(account);
 		 transfers.add(destination);
 		 transfer(transfers,account.getBalance());
 		 // update DB
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
 	
 /*	public Account getTestAccount(int accountID, double balance, String type) { // junk for testing
 		Account account = new Account(balance, type);
 		account.setID(accountID);
 		return account;
 	}*/
 	
}
