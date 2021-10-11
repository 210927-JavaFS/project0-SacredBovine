package com.revature.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.models.Account;
import com.revature.models.Address;
import com.revature.models.Name;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;
import com.sun.tools.javac.util.List;
import com.revature.daos.NameDAO;
import com.revature.daos.NameDAOImpl;
import com.revature.daos.RequestDAO;
import com.revature.daos.RequestDAOImpl;
import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.daos.AddressDAO;
import com.revature.daos.AddressDAOImpl;


public class UserService {
	
	private final int defaultUserType = 1; // default User creation gives AccountHolder access
	private NameDAO nameDAO = new NameDAOImpl();
	private AddressDAO addressDAO  = new AddressDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();
	private RequestDAO requestDAO = new RequestDAOImpl();
	
	public User createNewUser(String eMail, String pass, String firstName, String lastName, String streetNumber, String streetName, 
			String city, String region, String zipcode, String country, String phone) { 
		Name name = new Name(firstName, lastName);
		name.setId(nameDAO.addName(name));
		Address address = new Address(streetNumber, streetName, city, region, zipcode, country);
		address.setAddressID(addressDAO.addAddress(address));
		User user = new User(eMail, pass, name, address, phone, defaultUserType);
		user.setUserID(userDAO.addUser(user));
		return user;
	}
	
	public User getByID(int userID) {
		return userDAO.getByID(userID);
	}
// For admin use only	
	public void displayUserInfo(User user) { // Not sure if will end up using, just was useful at one time
		user.toString();
	}
	
	public User getUser(int userID) {
		return userDAO.getByID(userID);
	}
	public int getType(User user) {
		return user.getType();
	}
	
	void SetType(User user, int type){ 
		user.setType(type);
		userDAO.updateUser(user);
	}
	
	public boolean assignAccount(User user, Account account) { //This will be used by all employees to open approved accounts, account service exists in skeleton form
		
		try(Connection conn = ConnectionUtil.getConnection()){
			switch (account.getType()) {
				case "checking" :
					if (user.getCheckingAccount().getID() > 10000000) {
						System.out.println("Customer already has a checking account. \n No new account will be opened.");
					}
					else {
						user.setCheckingAccount(account);
						System.out.println(" In userService assign checking account and account info is \n" + account.toString()  + " \n To User : " + String.valueOf(user.getID()));
						return userDAO.updateUserAccount(account, user);
					}
					break;
				case "savings" :
					if (user.getSavingsAccount().getID() > 10000000) {
						System.out.println("Customer already has a savings account. \n No new account will be opened.");
					}
					else {
						user.setSavingsAccount(account);
						System.out.println(" In userService assign savings account and account info is \n" + account.toString() + " \n To User : " + String.valueOf(user.getID()));
						return userDAO.updateUserAccount(account, user);
					}
					break;
				case "joint" :
					if (user.getJointAccount().getID() > 10000000) {
						System.out.println("Customer already has a Joint account. \n No new account will be opened.");
					}
					else {
						user.setJointAccount(account);
						System.out.println(" In userService assign joint account and account \n " + account.toString() + " \n To User : " + String.valueOf(user.getID()));
						return userDAO.updateUserAccount(account, user);
					}
					break;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getName(User user) {
		return user.getName().toString();
	}
	public User shutDownObject() {
		User user = new User();
		user.setType(4);
		return user;
	}
	//void JoinAccount( worry about this later)
}
