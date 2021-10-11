package com.revature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.controllers.LoginController;

public class Driver {
	
	public static void main(String[] args) {
		LoginController login = new LoginController(); 
		login.loginMenu(); // launch first menu
	}
}