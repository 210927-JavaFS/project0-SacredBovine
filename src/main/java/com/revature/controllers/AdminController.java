package com.revature.controllers;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.services.UserService;

public class AdminController extends TellerController {
		
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	private LoginService loginService = new LoginService();
	private UserService userService = new UserService();
	
	public void closeAccount(User user, Account account) {}
	
		
		

/*methods CreateUser, ElevateUser, DeleteUser*/

	

}
