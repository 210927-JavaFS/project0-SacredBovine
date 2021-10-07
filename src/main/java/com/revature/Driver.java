package com.revature;

import com.revature.controllers.LoginController;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.notusing.AccountHolder;
import com.revature.services.AccountService;


public class Driver {
	
	public static void main(String[] args) {
		
	//	Account account = new Account(500.00, "checking");
	/*	account.Deposit(115);
		account.GetBallance();
		account.Withdraw(15);
		account.GetBallance();
		account.Deposit(-1);*/
		
		User ah = new User();
		ah.getAccounts();
		LoginController login = new LoginController();
		
		login.menu();
	
		
	}
	
}
