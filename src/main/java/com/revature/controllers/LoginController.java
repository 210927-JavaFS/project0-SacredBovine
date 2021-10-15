package com.revature.controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import java.security.NoSuchAlgorithmException;  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.services.PasswordService;
import com.revature.services.UserService;

public class LoginController {
	
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	private LoginService loginService = new LoginService();
	private UserService userService = new UserService();
	private UserCreationController  userCreationController = new UserCreationController();
	private PasswordService passwordService = new PasswordService();
	
	public void loginMenu() {
		try
		{
			boolean shutdown = false;
			do {
				System.out.println("<:Welcome to RevBank:> \n");
				User user = login();
				int type = userService.getType(user); 
				if(type==1 | type == 2 || type == 3 || type == 4 ) {
					switch(type){
						case 1 : 
							AccountHolderController accountHolderController = new AccountHolderController();
							System.out.println(" Hello "+ userService.getName(user) +"\n");
							accountHolderController.accountHolderMenu(user);
							break ;
						case 2 :
							TellerController tellerController = new TellerController();
							System.out.println(" Hello "+ userService.getName(user) +"\n");
							tellerController.decisionMenu(user);
							break;
						case 3 :
							AdminController adminController = new AdminController();
							System.out.println(" Hello "+ userService.getName(user) +"\n");
							adminController.decisionMenu(user);
							break;
						case 4 :
							shutdown = true;
							break;
					}			
				}
				log.debug(" Retrieved user type: "+String.valueOf(type));
			} while(shutdown == false);
		}
		catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			scan.next();
			loginMenu();
		}
	}
	
	public User login() {
		try
		{
			System.out.println(" Do you have an existing account y/n? \n");
			String input = scan.nextLine().toLowerCase().trim();
			switch (input) {
				case "y" :
					System.out.println(" What is your e-mail address?");
					input = scan.nextLine().toLowerCase().trim();
					System.out.println(" What is your password?");
					String pass = passwordService.toHexString(passwordService.getSHA(scan.nextLine().trim()));
					User user = loginService.login(input, pass);
					if (user != null) return user;
					else {
						System.out.println(" Incorrect eMail or password. \n Please try again. \n ");
						log.debug(" Incorrect Email or password input");
						return login();
					}
				case "n" :
					System.out.println(" Let's create you a new account.");
					if(userCreationController.createNewUser()) {
						System.out.println(" Please login to your new account. \n");
					} else {
						System.out.println(" Account creation failed. Please try again. \n");
						log.warn(" User creation failed");
					}
					return login();
				case "shutdown" :
					user = userService.shutDownObject();
					log.debug(" System shutdown object created.");
					return user;		
				default :
					System.out.println(" Incorrect eMail or password. \n Please try again. \n");
					log.debug(" Incorrect Email or password input");
					return login();
				}
		}
		catch ( NoSuchAlgorithmException e)
		{
			System.out.println("Could not hash pass.");
			log.error(" Could not Hash password. "+e.getStackTrace().toString());
		}
		catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			scan.next();
			return login();
		}
		catch (NullPointerException e) 
		{
			log.error(e.getStackTrace().toString());
			System.out.println(" There was an error connecting to the Database. Please try again. \n");
		}
		return userService.getDummy();
	}

}
