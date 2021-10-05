package com.revature;

import com.revature.models.Account;
import com.revature.models.AccountHolder;
import com.revature.models.User;
import com.revature.services.AccountService;


public class Driver {
	
	public static void main(String[] args) {
		
		Account account = new Account(500, "checking");
		account.Deposit(115);
		account.GetBallance();
		account.Withdraw(15);
		account.GetBallance();
		account.Deposit(-1);
		
		AccountHolder ah = new AccountHolder(account);
		ah.GetAccounts();
		
		
		AccountService acctService = new AccountService();
		acctService.CreateNewUser(ah);
		acctService.DisplayUserInfo(ah);
		
	}
	
}
