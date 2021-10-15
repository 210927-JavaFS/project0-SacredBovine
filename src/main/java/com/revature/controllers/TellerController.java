package com.revature.controllers;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.constraints.Email;

import com.revature.models.Request;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.RequestService;
import com.revature.services.UserService;

public class TellerController extends AccountHolderController{

	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(TellerController.class);
	private RequestService requestService = new RequestService();
	private AccountService accountService = new AccountService();
	private UserService userService = new UserService();
	
	public void decisionMenu(User user) { 
		try {
			System.out.println(" Which menu would you like to access? \n\n"
					+ "   1: Employee Menu \n"
					+ "   2: Personal Banking Menu");
			int input = scan.nextInt();
			switch(input) {
				case 1 :
					tellerControllerMenu(user);
					break;
				case 2 : 
					accountHolderMenu(user);
					break;
				default :
					System.out.println(" Invalid input. Please try again. \n");
					decisionMenu(user);
					break;
			}
		}
		catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
		}
	}
	public void tellerControllerMenu(User user) {
		try {	
			int input = -1;
			do {
				System.out.println("Hello "+user.getName()+"\n What would you like to do? \n"
						+ "   1: View open account requests \n"
						+ "   2: View user information \n"
						+ "   3: Switch to your personal banking menu \n"
						+ "   4: Logout ");
				input = scan.nextInt();
				switch(input) {
						case 1 :
							viewRequests();
							break;
						case 2:
							viewUserInfo();
							break;
						case 3:
							System.out.println(" You will now be logged into your personal banking portal. \n");
							accountHolderMenu(user);
							input = 4;
							break;
						case 4:
							System.out.println(" Have a nice day!");
							break;
						default :
							System.out.println(" Invalid input. Please try again. \n");
							input = 0;
							break;
					}
			//scan.nextLine(); //I DON'T KNOW WHY THIS NEEDS TO BE HERE!
			} while (input != 4);
		}
		catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			scan.next();
			tellerControllerMenu(user);
		}
	}
	
	public void viewRequests() {
		try {
			List<Request> requests = requestService.getRequests();
			for (int i = 0; i < requests.size(); i++ ) {
				System.out.println(" New account request : " + String.valueOf(i+1) + " \n" + requests.get(i).toString());
			}
			
			System.out.println(" Input the request # you would like to inspect ('0' to exit) : \n");
			int input = scan.nextInt()-1;
			if (input >= 0) {
				System.out.println(" What would you like to do? \n"
						+ "   1: View user info \n"
						+ "   2: Approve account\n"
						+ "   3: Decline account");
				int nextInput = scan.nextInt();
				switch (nextInput) {
					case 1 : // View user info
						System.out.println(userService.displayUserInfo(userService.getByID(requests.get(input).getRequestUserID())));
						break;
					case 2 : // Approve Account
						Request request = requests.get(input);
						User requestUser = userService.getByID(request.getRequestUserID());
						switch (request.getMessage()) {
							case "1" :  // create checking account and assign to user
								if(accountService.createAccount(requestUser, 1)) {
									requestService.closeRequest(request);
									log.info("Request :"+String.valueOf(requests.get(input).getRequestID())+" Approved.");
								}
								break;
							case "2":  // create savings account and assign to user
								if(accountService.createAccount(requestUser, 2)) {
									requestService.closeRequest(request);
									log.info("Request :"+String.valueOf(requests.get(input).getRequestID())+" Approved.");
								}
								break;
							case "3":  // create joint account and assign to user
								if(accountService.createAccount(requestUser, 3)) {
									requestService.closeRequest(request);
									log.info("Request :"+String.valueOf(requests.get(input).getRequestID())+" Approved.");
								}
								break;
							default : 
								System.out.println( String.valueOf(input) );
						}
					case 3 : // Decline Account
						requestService.closeRequest(requests.get(input));
						log.info("Request :"+String.valueOf(requests.get(input).getRequestID())+" Declined.");
						break;
					default : 
						System.out.println(" Invalid input. Please try again. \n");
				}	
			}
		}
		catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			scan.next();
			viewRequests();
		}
	}
	public void viewUserInfo() {
		System.out.println(" Input the email address of the user you would like to see: \n");
		@Email
		String eMail = scan.next().toLowerCase().trim();
		User user = userService.getByEMail(eMail);
		System.out.println(userService.displayUserInfo(user)+" \n\n ");
	}

}