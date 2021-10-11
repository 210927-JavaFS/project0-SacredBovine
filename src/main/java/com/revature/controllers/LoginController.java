package com.revature.controllers;

import java.util.Scanner;

import java.security.NoSuchAlgorithmException;  
import java.security.MessageDigest;  
  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.models.User;

import com.revature.services.LoginService;
import com.revature.services.UserService;

/*Takes user login info, checks account type, and launches appropriate user controller
 * Utilizes LoginService for User information retrieval from DB*/
public class LoginController {
	
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	private LoginService loginService = new LoginService();
	private UserService userService = new UserService();
	private UserCreationController  userCreationController = new UserCreationController();
	
	public void loginMenu() {
		boolean shutdown = false;
		do {
			System.out.println("<:Welcome to RevBank:> \n");
		
		
			User user = login();
		
			// System.out.println(user.toString());
			int type = userService.getType(user); 
			if(type!=1 && type != 2 && type != 3 && type != 4 ) {
				System.out.println("Invalid User type. \n");
			} 
			else {
				switch(type){
					case 1 : 
						AccountHolderController accountHolderController = new AccountHolderController();
						System.out.println("Hello "+ userService.getName(user) +"\n");
						accountHolderController.accountHolderMenu(user);
						break ;
					case 2 :
						TellerController tellerController = new TellerController();
						System.out.println("Hello "+ userService.getName(user) +"\n");
						tellerController.decisionMenu(user);
						break;
					case 3 :
						AdminController adminController = new AdminController();
						System.out.println("Hello "+ userService.getName(user) +"\n");
						adminController.decisionMenu(user);
						break;
					case 4 :
						shutdown = true;
						break;
				}			
			}
		}while(shutdown == false);
	}
	
	public User login() {
		System.out.println("Do you have an existing account y/n?");
		String input = scan.nextLine().toLowerCase().trim();
		User user = new User();
		switch (input) {
			case "y" :
				System.out.println("What is your e-mail address?");
				input = scan.nextLine().toLowerCase();
				System.out.println("What is your password?");
				int userID = loginService.login(input, scan.nextLine());
				System.out.println(" Login method returned USER ID : "+ String.valueOf(userID));
				if (userID != 0) {
					user = userService.getUser(userID);
				}
				break;
			case "n" :
				System.out.println("Let's create you a new account.");
				user = userCreationController.createNewUser();
				break;
			case "shutdown" :
				user = userService.shutDownObject();
				break;				
			default :
				System.out.println(" eMail or password where incorrect. \n Please try again.");
				user = login();
			}
		
/* PASSWORD STUFF 
 * 		scan.getPassword();
 * 		
*/	

		return user;
	}
}
