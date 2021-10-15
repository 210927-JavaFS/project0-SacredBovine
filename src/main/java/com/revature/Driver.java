package com.revature;

import com.revature.controllers.LoginController;

public class Driver {
	
	public static void main(String[] args) {
		
		System.out.println(" \n");
		LoginController login = new LoginController(); 
		login.loginMenu();
	}

}