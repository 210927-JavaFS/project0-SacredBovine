package com.revature.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class User {

	private int userID;
	private String pass;
	private Name name;
	private String eMail;
	private Address address;
	private String phone;	
	protected int type;
	private Account checkingAccount;
	private Account savingsAccount;
	private Account jointAccount;
	private List<Account> accounts;
// Constructors
	public User() {}
	public User(String eMailInput, String passInput, Name nameInput, Address addressInput, String phoneInput, // Without an account
			int type) {
		super();
		this.pass = passInput;
		this.name = nameInput;
		this.eMail = eMailInput;
		this.address = addressInput;
		this.phone = phoneInput;
		this.type = type;
	}
	public User(String eMailInput, String passInput, Name nameInput, Address addressInput, String phoneInput, // With an List of Accounts. Probably won't use
			int type, ArrayList<Account> accountsInput) {
		super();
		this.pass = passInput;
		this.name = nameInput;
		this.eMail = eMailInput;
		this.address = addressInput;
		this.phone = phoneInput;
		this.type = type;
		this.accounts = accountsInput;
	}
	
// Getters
	public Name getName() {
		return name;
	}
	public Address getAddress() {
		return address;
	}
	public String getEMail() {
		return eMail;
	}
	public String getPass() {
		return pass;
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
	public Account getCheckingAccount() {
		return checkingAccount;
	}
	public Account getSavingsAccount() {
		return savingsAccount;
	}
	public Account getJointAccount() {
		return jointAccount;
	}
	public List<Account> getAccounts(){
		return accounts;
	}

// Setters
	public void setAddress(Address addressInput) {
		this.address = addressInput;
	}
	public void setEMail(String eMailInput) {
		this.eMail = eMailInput;
	}
	public void setName(Name nameInput)
	{
		this.name = nameInput;
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
	public void setUserID(int idInput)
	{
		this.userID = idInput;
	}
	public void setCheckingAccount(Account checkingAccount) {
		this.checkingAccount = checkingAccount;
	}
	public void setSavingsAccount(Account savingsAccount) {
		this.savingsAccount = savingsAccount;
	}
	public void setJointAccount(Account jointAccount) {
		this.jointAccount = jointAccount;
	}
	public void setAccounts(List<Account> accountsInput) {
		this.accounts = accountsInput;
	}

// Overrides	
@Override
	public int hashCode() {
		return Objects.hash(address, checkingAccount, eMail, jointAccount, name, pass, phone, savingsAccount, type,
				userID);
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
		return Objects.equals(address, other.address) && Objects.equals(checkingAccount, other.checkingAccount)
				&& Objects.equals(eMail, other.eMail) && Objects.equals(jointAccount, other.jointAccount)
				&& Objects.equals(name, other.name) && Objects.equals(pass, other.pass)
				&& Objects.equals(phone, other.phone) && Objects.equals(savingsAccount, other.savingsAccount)
				&& type == other.type && userID == other.userID;
	}
	@Override
	public String toString() {
		String output = new String(" User Type: ");
		switch(this.type) {
			case 1 :
				output+="Customer\n";
				break;
			case 2 :
				output+="Employee\n";
				break;
			case 3 :
				output+="Bank Administrator\n";
				break;
		}
		output += " User Name: " + this.getName() + "\n User Email Address: " + this.getEMail() + "\n User Phone Number: "+this.getPhone() 
			+ "\n User Address: \n" + this.getAddress().toString() + "\n";
		return output;
	}
	
}	