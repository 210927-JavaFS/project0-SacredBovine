package com.revature.services;

import com.revature.models.Account;

import java.util.ArrayList;

public class AccountService {

	private final double newAccountInitialBalance = 0.00;
 	
// AccountHolder and Admin use Methods.
	
	public void deposit(Account account, double amount){
 		double balance = account.getBalance();
 		if(amount > 0) {
 				balance += amount;
 		 		account.setBalance(balance);
 		 		//finalize account transaction in DB
 		} 
 		else {
 			System.out.println("Invalid deposit. Canceling transaction.");
 		}
 	} 
 	
  	public void withdraw(Account account, double amount){
 		double balance = account.getBalance();
 		if(amount > 0) {
 			if (balance-amount > 0) {
 				balance -= amount;
 		 		account.setBalance(balance);
 		 		//finalize account transaction in DB
 		 		System.out.println(toString(account));
 			}
 			else {
 				System.out.println("Not enough funds to complete transaction.");
 			} 
 		}
 		else {
 			System.out.println("Invalid amount. Canceling transaction.");
 		}
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
 	
 	 public Account createAccount(String type){ // Should create a new Bank account with proper type checking or savings with balance $0.00 and assign to user
 		 Account account = new Account(newAccountInitialBalance, type);
 		 // account.setID(generateAccountID()); use another account service method to generate a new accountID and verify unique with DB
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
 		String display = String.valueOf(account.getID())+" $"+String.valueOf(account.getBalance());
 		return display;
 	}
 	
 	public Account getTestAccount(int accountID, double balance, String type) { // junk for testing
 		Account account = new Account(balance, type);
 		account.setID(accountID);
 		return account;
 	}
 	
}
