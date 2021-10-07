package com.revature.controllers;

import java.util.Scanner;

import com.revature.models.Request;
import com.revature.models.User;

/* methods OpenAccount, CloseAccount, AccessAccount*/
public class TellerController extends AccountHolderController{

	private static Scanner scan = new Scanner(System.in);
	
	public void decisionMenu(User user) {
		if (user.getAccounts().size() > 0) {
			System.out.println("Would you like to login as an Employee or Conduct personal banking?");
			switch(scan.nextLine().toLowerCase()) {
				case "employee" :
					employeeMenu(user);
					break;
				case "personal" : 
					accountHolderMenu(user);
					break;
				case "personal banking" : 
					accountHolderMenu(user);
					break;
			}					
		}
	}
	
	public void employeeMenu(User user) {
		System.out.println("Hello "+user.getName()+"\n Would you like to review current account requests? y/n");
		switch(String.valueOf(scan.nextLine()).toLowerCase()) {
			case "y" :
				// Implement RequestService Method to retrieve open requests into an ArrayList<Request> for review and approval.
				break;
			case "n":
				System.out.println("There is absolutely no reason for you to be logged into the Employee portal due to limited functionality. \n "+
				"I'm affraid you will be recieving the boot.\n Goodbye :)");
				break;
		}
	}
	
	// maybe pass just the Request?
	public void openAccount(Request request) {
		
	}
	
	
	
}
