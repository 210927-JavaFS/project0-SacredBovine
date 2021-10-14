package com.revature.services;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.models.Account;
import com.revature.models.Address;
import com.revature.models.Name;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;
import com.revature.daos.NameDAO;
import com.revature.daos.NameDAOImpl;
import com.revature.daos.RequestDAO;
import com.revature.daos.RequestDAOImpl;
import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;
import com.revature.daos.AddressDAO;
import com.revature.daos.AddressDAOImpl;


public class UserService {
	
	private final int defaultUserType = 1; // default User creation gives AccountHolder access
	private NameDAO nameDAO = new NameDAOImpl();
	private AddressDAO addressDAO  = new AddressDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();
	private RequestDAO requestDAO = new RequestDAOImpl();
	private AccountDAO accountDAO = new AccountDAOImpl();
	
	
	public boolean createNewUser(String eMail, String pass, String firstName, String lastName, String streetNumber, String streetName, 
			String city, String region, String zipcode, String country, String phone) { 
		Name name = new Name(firstName, lastName);
		name.setId(nameDAO.addName(name));
		Address address = new Address(streetNumber, streetName, city, region, zipcode, country);
		address.setAddressID(addressDAO.addAddress(address));
		User user = new User(eMail, pass, name, address, phone, defaultUserType);
		return userDAO.addUser(user);
	}
	
	public User getByID(int userID) {
		return userDAO.getByID(userID);
	}
	public User getByEMail(String eMail) {
		return userDAO.getByEMail(eMail);
	}
// For admin use only	
	public String displayUserInfo(User user) { // Not sure if will end up using, just was useful at one time
		String output = user.toString();
		List<Account> accounts = accountDAO.getAllByID(user.getID());
		for (int i=0;i<accounts.size(); i++) {
			output += accounts.get(i).toString();
		}
		return output;
	}
	
	public int getType(User user) {
		return user.getType();
	}
	
	
	public boolean assignAccount(User user, Account account) { //This will be used by all employees to open approved accounts, account service exists in skeleton for
			return accountDAO.addUserAccount(user,account);
	}	
	public String getName(User user) {
		return user.getName().toString();
	}

// admin use only	
	public void SetPhone(User user, String phone) {
		user.setPhone(phone);
		userDAO.updateUser(user);
	}
	public void setType(User user, int type){ 
		user.setType(type);
		userDAO.updateUser(user);
	}
	public User shutDownObject() { // takes input of "shutdown" at login menu and returns a user object that escapes application from login loop
		User user = new User();
		user.setType(4);
		return user;
	}
	public User getDummy() {
		User user = new User();
		user.setType(5);
		return user;
	}
	//void JoinAccount( worry about this later)
}
