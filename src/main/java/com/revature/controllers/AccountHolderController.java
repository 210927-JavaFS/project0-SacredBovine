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
	private static String input;
	private static AccountService accountService = new AccountService();
	private static RequestService requestService = new RequestService();
		
	public void accountHolderMenu(User user) {
		do {
			System.out.println(" Please choose from the following options. \n\n "
					+ "   1: Check account balances \n "
					+ "   2: Make a withdrawl \n "
					+ "   3: Make a deposit \n "
					+ "   4: Transfer funds to another account \n "
					+ "   5: Request to open a new account \n "
					+ "   6: Logout ");
			input = scan.nextLine().trim();
			switch (input) {
				case "1" : // Display Account Ballances
				{
					List<Account> accounts = user.getAccounts();
					
					for(int i = 0; i < accounts.size(); i++) {
						if(accounts.get(i).getID() != 0) System.out.println(" "+String.valueOf(i+1)+" :" + accountService.toString(accounts.get(i)));
					}
					break;
				}
				case "2" : // Withdraw funds
				{
					List<Account> accounts = user.getAccounts();
					System.out.println("Which account would you like to withdraw from? ");
					for(int i = 0; i < accounts.size(); i++) {
						if(accounts.get(i).getID() > 0 && accounts.get(i).getID() != 0) System.out.println(" " + String.valueOf(i+1) +" : " + accountService.toString(accounts.get(i)));
					}					
					int index = Integer.valueOf(scan.nextLine())-1;
					System.out.println("How much would you like to withdraw?");
					double amount = Double.valueOf(scan.nextLine());
				
					accountService.withdraw(accounts.get(index),amount);				
					break;
				}
				case "3" : // Deposit funds
				{
					List<Account> accounts = user.getAccounts();
					System.out.println("Which account would you like to deposit to?");
					for(int i = 0; i < accounts.size(); i++) {
						if(accounts.get(i).getID() > 0 && accounts.get(i).getID() != 0)
						{
							System.out.println(" " + String.valueOf(i+1) +" : " + accountService.toString(accounts.get(i)));
						}
					}
					int index = Integer.valueOf(scan.nextLine())-1;
					
					System.out.println("How much would you like to deposit?");
					double amount = Double.valueOf(scan.nextLine());
				
					accountService.deposit(accounts.get(index),amount);				
					break;
				}
				case "4" : // Transfer funds
				{
					List<Account> accounts = user.getAccounts();
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
					
					accountService.transfer(transfers, amount);
					break;
				}
				case "5" : // Request a new bank account
				{
					System.out.println("Which type of account would you like to request? \n 1: checking \n 2: savings");
					String message = scan.nextLine();
					if( requestService.createRequest(1, message, user)) {
						System.out.println("Your request has been submitted. /n A bank employee must approve your request. This can take some time. ");
					}
					else {
						System.out.println("Failed to submit request. Please resubmit.");
					}
					break;
				}
				case "6" : // Sign out user
				{
					System.out.println("Thank you for banking with us today. Goodbye.");
					break;
				}
				default :
					System.out.println("Improper selection. Please try again");
					break;
			}
		} while (!input.equals("6"));
	}
}