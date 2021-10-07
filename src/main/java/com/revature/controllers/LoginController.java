package com.revature.controllers;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	public void menu() {
		
		System.out.println("<:Welcome to RevBank:> \n");
		
		User user = login();
		int type = userService.getType(user); 
		System.out.println(String.valueOf(type));
		switch(type){
			case 1 : AccountHolderController aHController = new AccountHolderController();
				aHController.menu(user);
				break ;
		}
				
	}
	
	
	public User login() {
		User user = new User();
		System.out.println("Do you have an existing account y/n?");
		String input = scan.nextLine().toLowerCase().trim();
		System.out.println(input+"!");
		if (input.equals("y")) {
			System.out.println("What is your e-mail address?");
			input = scan.nextLine().toLowerCase();
			System.out.println("What is your password?");
			user = loginService.login(input, scan.nextLine());
		}
		else if (input.equals("n")) {
			System.out.println("Let's create you a new account.");
			return loginService.createNewUser();
		}
		else {
			System.out.println("Invalid input. Returning Empty User");
		}
		return user;
	}
}
