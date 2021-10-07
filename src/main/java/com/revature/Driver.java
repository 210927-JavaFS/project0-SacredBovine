package com.revature;

import com.revature.controllers.LoginController;

public class Driver {
	
	public static void main(String[] args) {
		
	//	Account account = new Account(500.00, "checking");
	/*	account.Deposit(115);
		account.GetBallance();
		account.Withdraw(15);
		account.GetBallance();
		account.Deposit(-1);*/
		
	
		LoginController login = new LoginController(); 
		
		login.loginMenu(); // launch first menu
	
		
	}
	
}
