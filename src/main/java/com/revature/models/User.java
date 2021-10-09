package com.revature.models;

import java.util.ArrayList;
import java.util.Objects;

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
	public User(String pass, String firstName, String lastName, String eMail, String address, String phone,
			int employeeID, int type, ArrayList<Account> accounts) {
		super();
		this.pass = pass;
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
		this.address = address;
		this.phone = phone;
		this.employeeID = employeeID;
		this.type = type;
		this.accounts = accounts;
	}
	
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
	public int getID() {
		return userID;
	}
	
	// Setters
	public void setAddress(Address addressInput) {
		this.address = addressInput.toString();
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
	@Override
	public int hashCode() {
		return Objects.hash(accounts, address, eMail, employeeID, firstName, lastName, pass, phone, type, userID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(accounts, other.accounts) && Objects.equals(address, other.address)
				&& Objects.equals(eMail, other.eMail) && employeeID == other.employeeID
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(pass, other.pass) && Objects.equals(phone, other.phone) && type == other.type
				&& userID == other.userID;
	}	
	
	// foobars
	/*public void setAccounts(ArrayList<Account> tests) {
		this.accounts = tests;
	}
	public void addAccount(Account test) { // only for testing right now
		accounts.add(test);
	}
	public void removeAccount(Account test) { // only for testing right now
		accounts.remove(test);
	}*/
	
}
