package com.revature.models;

import java.util.ArrayList;




public class AccountHolder extends User{
	
	private ArrayList<Account> accounts = new ArrayList<>();
	
	public AccountHolder() {
		super();
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
	
	public ArrayList GetAccounts(){
		return accounts;
	}
}
