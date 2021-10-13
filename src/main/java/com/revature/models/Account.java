package com.revature.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import java.util.Objects;

public class Account {
	
	private int accountID;
	private double balance;
	private String type;
// Constructors
	public Account() { 
		super();
	}
	public Account(double initBalance, String accountType) { // Will not supply ID. Only occurs once update to DB has been made
		super();
		balance=initBalance;
		type=accountType;
	}

// Getters
	public double getBalance() {
		return balance;
	}
	public int getID() {
		return accountID;
	}
	public String getType() { // Used for access level tracking
		return type;
	}

// Setters
	public void setBalance(double newBalance) {
		this.balance = newBalance;
	}	
	public void setID(int ID) {
		this.accountID = ID;
	}
	public void setType(String newType) { // Used for access level tracking
		this.type = newType;
	}

// Overrides
	@Override
	public int hashCode() {
		return Objects.hash(accountID, balance, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountID == other.accountID
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(type, other.type);
	}
	public String toString() {
		return type + " account : "+ String.valueOf(accountID)+": $"+String.valueOf(balance);
	}
	
}
