package com.revature.models;

public class Account {
	
	private int accountID;
	private double balance;
	private String type;

	public Account() {
	}
	
	public Account(double initBalance, String acctType) {
		String tempType = acctType.toLowerCase();
		if(tempType == "savings" || tempType == "checking") {
			this.setBalance(initBalance);
			type = tempType;
			System.out.println("You have opened a new "+type+" account with a balance of $"+ balance);
		}
		else {
			System.out.println("Improper account type. Please indicate checking or savings.");
		}
	}
		
	public String getType() {
		return type;
	}
	
	public void setType(String newType) {
		this.type = newType;
	}
	
	public void displayBalance() {
		System.out.println("This account's current balance is: $"+balance);
	}
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double newBalance) {
		this.balance = newBalance;
	}
	
	public String displayAccount() {
		String acct = "Account # "+accountID+" with a balance of $"+balance+".";
		return acct;
	}
	public int getID() {
		return accountID;
	}
	
}
