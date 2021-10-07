package com.revature.models;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.models.Account;

public class User {
	private String pass;
	private String firstName;
	private String lastName;
	private String eMail;
	private String address;
	private String phone;
	private int employeeID;
	private int userID;
	protected int type;
	private ArrayList<Account> accounts;
	
	// Constructors
	public User() {}
	
	// Getters
	public ArrayList<Account> getAccounts(){
		return accounts;
	}
	public String getName() {
		String fullName = firstName+" "+lastName;
		return fullName;
	}
	public String getAddress() {
		return address;
	}
	public String geteMail() {
		return eMail;
	}
	public String getPhone() {
		return phone;
	}
	public int getType() {
		return type;
	}
	public int getUserID() {
		return userID;
	}
	
	// Setters
	public void setAddress(String addressInput) {
		this.address = addressInput;
	}
	public void seteMail(String eMailInput) {
		this.eMail = eMailInput;
	}
	public void setName(String first, String last)
	{
		this.firstName = first;
		this.lastName = last;
	}
	public void setPass(String password) {
		this.pass = password;
	}
	public void setPhone(String phoneInput) {
		this.phone = phoneInput;
	}
	public void setType(int typeInput) {
		this.type = typeInput ;
	}
	public void setUserID(int tempInput)
	{
		this.userID = tempInput;
	}	
	
	// foobars
	public void setAccounts(ArrayList<Account> tests) {
		this.accounts = tests;
	}
	public void addAccount(Account test) { // only for testing right now
		accounts.add(test);
	}
	public void removeAccount(Account test) { // only for testing right now
		accounts.remove(test);
	}
	
}
