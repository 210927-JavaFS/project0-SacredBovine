package com.revature.models;

import java.util.ArrayList;
import com.revature.models.Account;

public class AccountHolder extends User {
	
	ArrayList<Account> accounts = new ArrayList<Account>();
	
	
	public AccountHolder() {
		super();
		//accounts = new ArrayList<Account>();
		this.SetType("accountholder");
	}

	public AccountHolder(Account account) {
		accounts.add(account);
	}
	
	public void AddAccount() {
		accounts.add(new Account());
	}
	
	public void AddAccount(Account account) {
		accounts.add(account);
	}
	
/*	public void GetAccounts() {
		for (int i=0;i<accounts.size();i++) System.out.println(accounts.get(i).DisplayAccount());	
	}*/
	
	public ArrayList<Account> GetAccounts(){
		return accounts;
	}
}
