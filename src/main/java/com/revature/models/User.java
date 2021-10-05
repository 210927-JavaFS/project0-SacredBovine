package com.revature.models;

import java.util.ArrayDeque;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class User {
	private String login;
	private String pass;
	private String firstName;
	private String lastName;
	private String eMail;
	private String address;
	private String phone;
	private int userID;
	private AccountType type;
	
	public User() {
	}
		
	public User(String userName) {
		login = userName;
	}
	
	public String getName() {
		String fullName = firstName+" "+lastName;
		return fullName;
	}
	
	public String geteMail() {
		return eMail;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setName(String first, String last)
	{
		this.firstName = first;
		this.lastName = last;
	}
	
	public void seteMail(String eMailInput) {
		this.eMail = eMailInput;
	}
	
	public void setAddress(String addressInput) {
		this.address = addressInput;
	}
	
	public void setPhone(String phoneInput) {
		this.phone = phoneInput;
	}
	
	public void setUserID(int tempInput)
	{
		this.userID = tempInput;
	}
}
