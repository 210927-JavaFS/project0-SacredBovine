package com.revature.models;

public class Account {
	
	private int accountID;
	private double ballance;
	private String type;

	public Account() {
	}
	
	public Account(double initBallance, String acctType) {
		String tempType = acctType.toLowerCase();
		if(tempType == "savings" || tempType == "checking") {
			this.Deposit(initBallance);
			type = tempType;
			System.out.println("You have opened a new "+type+" account with a ballance of $"+ ballance);
		}
	}
		
	public void Deposit(double deposit) {
		if (deposit >0) {
			ballance += deposit;
			//System.out.println("You have deposited $"+deposit+" into your account.");
			//System.out.println("The new ballance of this account is $"+ballance);
		}
		else System.out.println("Improper deposit ammount.");
	}
	
	public void Withdraw(double withdrawl) {
		if (withdrawl>0 && ballance-withdrawl >0) ballance-=withdrawl;
		else System.out.println("Could not complete transaction.");
	}
	
	public void GetBallance() {
		System.out.println("This account's current ballance is: $"+ballance);
	}
	
	public String DisplayAccount() {
		String acct = "Account # "+accountID+" with a ballance of $"+ballance+".";
		return acct;
	}
}
