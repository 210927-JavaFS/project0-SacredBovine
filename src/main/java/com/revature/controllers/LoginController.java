package com.revature.controllers;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.models.User;
import com.revature.services.LoginService;

/*Takes user login info, checks account type, and launches appropriate user controller
 * Utilizes LoginService for verification logic*/
public class LoginController {
	
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	private LoginService service = new LoginService();
	
	public User GetUser() {
		System.out.println("Do you have an existing account y/n?");
		String input = scan.nextLine().toLowerCase();
		if(input == "y") {
			System.out.println("What is the e-mail address associated with your account?");
			input = scan.nextLine();
			//verify stored User exists
			System.out.println("What is your password?");
			
		}else if(input == "n"){
			User user = new User();
			System.out.println("Let's create you a login./n"+"What is your email address?");
			input = scan.nextLine();
			//verify input is unique
			user.seteMail(input);
			System.out.println("What is your first name?");
			input = scan.nextLine();
			System.out.println("last name?");
			user.setName(input, scan.nextLine());
		
			System.out.println("phone?");
			user.setPhone(input);
			System.out.println("address?");
			user.setAddress(input);
			
		}
	}
	
	
	
	
	
}
