package com.revature.services;

import com.revature.models.Account;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountService {

 	
// AccountHolder and Admin User access use Methods.
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
 	
 	public void transer(ArrayList<Account> accounts, double amount){
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
 		// finalize accounts in database
 	}
 	
 	public String toString(Account account) {
 		String display = new String();
 		display.concat(String.valueOf(account.getID())).concat(" $"+String.valueOf(account.getBalance()));
 		return display;
 	}
}
