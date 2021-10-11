package com.revature.controllers;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.services.UserService;

public class AdminController extends TellerController {
		
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	private LoginService loginService = new LoginService();
	private UserService userService = new UserService();
	
	public void decisionMenu(User user) {
		if (user.getAccounts().size() > 0) {
			System.out.println("Would you like to login as an Employee or Conduct personal banking?");
			switch(scan.nextLine().toLowerCase()) {
				case "employee" :
					adminMenu(user);
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
	
	public void adminMenu(User user) {
		
	}
	
	public boolean setUserType() {
		return false;
	}
	public void closeAccount(User user, Account account) {}
	
		
		

/*methods CreateUser, ElevateUser, DeleteUser*/

	

}
