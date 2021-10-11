package com.revature.controllers;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.models.Account;
import com.revature.models.User;


public class AccountCreationController {
	
	public Account createNewAccount() {
		Account account = new Account();
		return account;
	}
	
}
