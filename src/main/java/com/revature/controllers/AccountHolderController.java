package com.revature.controllers;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;
import com.revature.models.User;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Account;
import com.revature.services.AccountService;
import com.revature.services.RequestService;
import com.revature.services.VerificationService;
import com.revature.throwables.InvalidMoneyRuntimeException;

public class AccountHolderController {

	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(AccountHolderController.class);
	private static AccountService accountService = new AccountService();
	private static RequestService requestService = new RequestService();
	private static VerificationService verificationService = new VerificationService();	
	
	public void accountHolderMenu(User user) {
		int input = 0;
		do {
			try {
				//while (scan.hasNext()) {scan.nextLine();}
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
			}
			catch (InputMismatchException e){
				log.error(e.getStackTrace().toString());
				System.out.println(" Invalid input. Please try again. \n");
				scan.next();
			}
		} while (input != 6);
	}

	void displayAccounts(User user) {
		List<Account> accounts = accountService.getUserAccounts(user);
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts != null) System.out.println("   "+String.valueOf(i+1)+": " + accounts.get(i).toString());
		}
	}
	
	boolean withdrawFunds(User user) {
		try {
			List<Account> accounts = accountService.getUserAccounts(user);
			System.out.println(" Which account would you like to withdraw from? \n\n");
			for(int i = 0; i < accounts.size(); i++) {
				if(accounts.get(i).getID() > 0 && accounts.get(i).getID() != 0) {
					System.out.println("   " + String.valueOf(i+1) + ": " + accountService.toString(accounts.get(i)));
				}
			}					
			int index = scan.nextInt();
			System.out.println(" How much would you like to withdraw? \n");
		
			@Positive(message = "Input a positive Value")
			@Digits(fraction = 2, integer = 10, message = "Input a valid amount")
			double amount = scan.nextDouble();
			verificationService.verifyMoney(amount);
			if(index < accounts.size() && index >= 0) {
					return accountService.withdraw(accounts.get(index),amount);
			} else System.out.println(" Invalid input. Please try again. \n");
		}
		catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			return false;
		}
		catch(InvalidMoneyRuntimeException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");	
			return false;
		}
		return false;	
	}
	boolean depositFunds(User user) {
		try {
			List<Account> accounts = accountService.getUserAccounts(user);
			System.out.println(" Which account would you like to deposit to? \n\n");
			for(int i = 0; i < accounts.size(); i++) {
				System.out.println("   " + String.valueOf(i+1) +": " + accountService.toString(accounts.get(i)));
			}	
			int index = scan.nextInt();
			System.out.println(" How much would you like to deposit? \n");
		
			@Positive(message = "Input a positive Value")
			@Digits(fraction = 2, integer = 10, message = "Input a valid amount")
			double amount = scan.nextDouble();
			verificationService.verifyMoney(amount);
			if(index < accounts.size() && index >= 0) {
				return accountService.deposit(accounts.get(index),amount);
			} else System.out.println(" Invalid input. Please try again. \n");
		}
		catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			return false;
		}
		catch(InvalidMoneyRuntimeException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			return false;
		}
		return false;	
	}
	boolean transferFunds(User user) {
		try {
			List<Account> accounts = accountService.getUserAccounts(user);
			if(accounts.size()<2) {
				System.out.println(" You can only transfer between your own accounts at this time and requires at least 2. \n"
						+ " Please open another account. \n");
				return false;
			}
			System.out.println(" Which account would you like to transfer the funds from? \n");
			for(int i = 0; i < accounts.size(); i++) {
				System.out.println("   " + String.valueOf(i+1) + ": " + accountService.toString(accounts.get(i)));
			}
			int input = scan.nextInt()-1;
			Account source = accounts.get(input);
			accounts.remove(input);
			System.out.println(" How much would you like to transfer?\n");

			@Positive(message = "Input a positive Value")
			@Digits(fraction = 2, integer = 10, message = "Input a valid amount")
			double amount = scan.nextDouble();					
			verificationService.verifyMoney(amount);
			System.out.println(" Which account would you like to transfer the funds to? \n");
			for(int i = 0; i < accounts.size(); i++) {
				System.out.println("   " + String.valueOf(i+1) + ": " + accountService.toString(accounts.get(i)));
			}			
			input = scan.nextInt()-1;	
			Account destination = accounts.get(input);
			return accountService.transfer(source, destination, amount);
		}
		catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			return false;
		}
		catch(InvalidMoneyRuntimeException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			return false;
		}
	}

	boolean requestAccount(User user) {
		try {
			System.out.println(" What type of account would you like to request? \n\n"
					+ "   1: checking \n"
					+ "   2: savings \n");
			int foo = scan.nextInt();
			String message = String.valueOf(foo);
			if( foo == 1 || foo == 2) {	
				return requestService.createRequest(1, message, user);
			}
		}
		catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
		}
		return false;
	}

}