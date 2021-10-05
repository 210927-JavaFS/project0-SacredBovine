package com.revature.User;

import java.util.ArrayDeque;

public class User {
	String login;
	String pass;
	String firstName;
	String lastName;
	String eMail;
	String address;
	int phone;
	static int userID;
	
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
	
	public int getPhone() {
		return phone;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void seteMail(String eMailInput) {
		eMail = eMailInput;
	}
	
	public void setAddress(String addressInput) {
		address = addressInput;
	}
	
	public void setPhone(int phoneInput) {
		phone = phoneInput;
	}
	
	public void setUserID(int tempInput)
	{
		userID = tempInput;
	}
}
