package com.revature.controllers;

import com.revature.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.models.Account;
import com.revature.services.AccountService;
import com.revature.services.RequestService;


/* Want to deposit, withdraw, ballance transfer, ballance retrieve, request new account.
 Utilizes UserService and AccountService for appropriate logic
 */
public class AccountHolderController {

	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(AccountHolderController.class);
	private static AccountService accountService = new AccountService();
	private static RequestService requestService = new RequestService();
		
	public void accountHolderMenu(User user) {
		int input;
		do {
			System.out.println(" Please choose from the following options. \n\n "
					+ "   1: Check account balances \n "
					+ "   2: Make a withdrawl \n "
					+ "   3: Make a deposit \n "
					+ "   4: Transfer funds to another account \n "
					+ "   5: Request to open a new account \n "
					+ "   6: Logout \n");
			input = scan.nextInt();
			switch (input) {
				case 1 : // Display Account Ballances
					displayAccounts(user);
					break;
				case 2 : // Withdraw funds
					withdrawFunds(user);				
					break;
				case 3 : // Deposit funds
					depositFunds(user);			
					break;
				case 4 : // Transfer funds
					transferFunds(user);
					break;			
				case 5 : // Request a new bank account
					requestAccount(user);
					break;
				case 6 : // Sign out user
					System.out.println(" Thank you for banking with us today. Goodbye.");
					break;
				default :
					System.out.println(" Improper selection. Please try again");
					break;
			}
		} while (input != 6);
	}

	void displayAccounts(User user) {
		List<Account> accounts = user.getAccounts();
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts != null) System.out.println("   "+String.valueOf(i+1)+": " + accounts.get(i).toString());
		}
	}
	
	boolean withdrawFunds(User user) {
		List<Account> accounts = user.getAccounts();
		System.out.println(" Which account would you like to withdraw from? \n");
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getID() > 0 && accounts.get(i).getID() != 0) {
				System.out.println("   " + String.valueOf(i+1) + ": " + accountService.toString(accounts.get(i)));
			}
		}					
		int index = scan.nextInt()-1;
		System.out.println(" How much would you like to withdraw? \n");
		double amount = Double.valueOf(scan.nextLine());
	
		return accountService.withdraw(accounts.get(index),amount);
	}
	
	boolean depositFunds(User user) {
		List<Account> accounts = user.getAccounts();
		System.out.println(" Which account would you like to deposit to? \n");
		for(int i = 0; i < accounts.size(); i++) {
			System.out.println("   " + String.valueOf(i+1) +": " + accountService.toString(accounts.get(i)));
		}
		int index = scan.nextInt()-1;
		
		System.out.println(" How much would you like to deposit? \n");
		double amount = scan.nextDouble();
	
		return accountService.deposit(accounts.get(index),amount);	
	}
		
	boolean transferFunds(User user) {
		List<Account> accounts = user.getAccounts();
		if(accounts.size()<2) {
			System.out.println(" Can only transfer between your own account at this time and requires at least 2. \n Please open another account. \n ");
			return false;
		}
		System.out.println(" Which account would you like to transfer the funds from? \n");
		for(int i = 0; i < accounts.size(); i++) {
			System.out.println("   " + String.valueOf(i+1) + ": " + accountService.toString(accounts.get(i)));
		}
//verify int input	
		int input = scan.nextInt()-1;
		Account source = accounts.get(input);
		accounts.remove(input);
		System.out.println(" How much would you like to transfer?");
//verify double input
		double amount = scan.nextDouble();					
		System.out.println(" Which account would you like to transfer the funds to? \n");
		for(int i = 0; i < accounts.size(); i++) {
			System.out.println("   " + String.valueOf(i+1) + ": " + accountService.toString(accounts.get(i)));
		}			
//verify int input	
		input = scan.nextInt();	
		Account destination = accounts.get(input);
		return accountService.transfer(source, destination, amount);
	}

// it is possible to make junk accounts due to request approval logic. make sure they only enter 1 or 2
	boolean requestAccount(User user) {
		System.out.println(" What type of account would you like to request? \n   1: checking \n   2: savings \n "); // 3: joint (available soon) \n ");
		int foo = scan.nextInt();
		String message = String.valueOf(foo);
		if( foo == 1 || foo == 2) {	
			return requestService.createRequest(1, message, user);
		}
		return false;
	}
}