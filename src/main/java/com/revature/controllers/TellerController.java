package com.revature.controllers;

import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.models.Account;
import com.revature.models.Request;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.RequestService;
import com.revature.services.UserService;

/* methods OpenAccount, CloseAccount, AccessAccount*/
public class TellerController extends AccountHolderController{

	private static Scanner scan = new Scanner(System.in);
	private RequestService requestService = new RequestService();
	private AccountService accountService = new AccountService();
	private UserService userService = new UserService();
	
	public void decisionMenu(User user) { // Let's an employee choose the employee or customer portal
		System.out.println("Would you like to login as: \n   1: Employee \n   2: Personal");
		switch(scan.nextLine().toLowerCase().trim()) {
			case "1" :
				TellerControllerMenu(user);
				break;
			case "2" : 
				accountHolderMenu(user);
				break;					
		}
	}
	
	public void TellerControllerMenu(User user) {
		int input = -1;
		do {
			System.out.println("Hello "+user.getName()+"\n Would you like to review current account requests? y/n");
				switch(String.valueOf(scan.nextLine()).toLowerCase().trim()) {
					case "y" :
						List<Request> requests = requestService.getRequests();
						for (int i = 0; i < requests.size(); i++ ) {
							System.out.println("New account request : " + String.valueOf(i+1) + requests.get(i).toString());
						}
						System.out.println("Input the request ID you would like to approve ('0' to exit) : ");
						input = scan.nextInt()-1;
						if (input >= 0) {
							Request request = requests.get(input);
							System.out.println(" You input :" + String.valueOf(input+1)+ " \n");
							System.out.println(requests.get(input).toString());
							User requestUser = userService.getByID(request.getRequestUserID());
							switch (request.getMessage()) {
								case "1" : { // create checking account and assign to user
									Account account = accountService.createAccount(requestUser, 1);
										requestService.closeRequest(request);
									break;
								}
								case "2": { // create savings account and assign to user
									Account account = accountService.createAccount(requestUser, 2);
									if(userService.assignAccount(requestUser, account)) {
										requestService.closeRequest(request);
									}
									break;
								}
								case "3": { // create joint account and assign to user
									Account account = accountService.createAccount(requestUser, 3);
									if(userService.assignAccount(requestUser, account)) {
										requestService.closeRequest(request);
									}
									break;
								}
							}
						}
						break;
					case "n":
						System.out.println("You will now be logged into your personal banking portal. \n ");
						accountHolderMenu(user);
						input = -1;
						break;
				}
		} while (input >= 0);
		
	}
}