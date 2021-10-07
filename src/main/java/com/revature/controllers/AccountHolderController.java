package com.revature.controllers;

import com.revature.models.User;

import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Account;
import com.revature.services.AccountService;


/* Want to deposit, withdraw, ballance transfer, ballance retrieve, request new account.
 Utilizes UserService and AccountService for appropriate logic
 */
public class AccountHolderController {

	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(AccountHolderController.class);
	private static String input;
	private static AccountService accountService = new AccountService();
	
	public void menu(User user) {
		
		ArrayList<Account> accounts = user.getAccounts(); // This should retrieve updated account info from DB each time method is run
	
		System.out.println("Hello "+user.getName()+" Please choose from the following options. \n 1: Check account balances \n 2: Make a withdrawl \n 3: Make a deposit ");
		System.out.println("4: Transfer funds to another account \n 5: Logout ");
		input = scan.nextLine();
		
		switch(input) {
			case "1" : // Display account balances
			
				for(int i = 0; i < accounts.size(); i++) System.out.println(accountService.toString(accounts.get(i)));
				break ;
			case "2" : // Withdraw funds from an account
			{
				System.out.println("Which account would you like to withdraw from? ");
				for(int i = 0; i < accounts.size(); i++) System.out.println(accountService.toString(accounts.get(i)));
				int index = Integer.valueOf(scan.nextLine());
				
				System.out.println("How much would you like to withdraw?");
				double amount = Double.valueOf(scan.nextLine());
				
				accountService.withdraw(accounts.get(index),amount);				
			
				menu(user);
				break;
			}
			case "3" : // Deposit funds into an account
			{	
				System.out.println("Which account would you like to deposit to?");
				for(int i = 0; i < accounts.size(); i++) System.out.println(accountService.toString(accounts.get(i)));
				int index = Integer.valueOf(scan.nextLine());
				
				System.out.println("How much would you like to withdraw?");
				double amount = Double.valueOf(scan.nextLine());
			
				accountService.deposit(accounts.get(index),amount);				
				
				menu(user);
				break;
			}
			case "4" : // Transfer funds from one account to another
			{
				ArrayList<Account> transfers = new ArrayList<>();
				
				System.out.println("Which account would you like to transfer the funds from?");
				for(int i = 0; i < accounts.size(); i++) System.out.println(accountService.toString(accounts.get(i)));
				int accountFrom = Integer.valueOf(scan.nextLine());
				transfers.add(accounts.get(accountFrom));
				
				System.out.println("How much would you like to transfer?");
				double amount = Double.valueOf(scan.nextLine());
				
				System.out.println("Which account would you like to transfer the funds to?");
				for(int i = 0; i < accounts.size(); i++) System.out.println(accountService.toString(accounts.get(i)));
				int accountTo = Integer.valueOf(scan.nextLine());
				transfers.add(accounts.get(accountTo));
				
				accountService.transfer(transfers,amount);				
					
				menu(user);
				break;
			}
			case "5" :
				System.out.println("Thank you for banking with RevBank. \n Goodbye");
				break ;
			
		}
			
	}
		
		
		
		
		
		
}
	//public 

