package com.revature.services;

import java.util.Scanner;

import com.revature.models.User;

public class UserService {


	private static Scanner scan = new Scanner(System.in);
	
	public User createNewUser() {
		User user = new User();
		String response;
		System.out.println("Please enter your first name: ");
		response = scan.nextLine();
		System.out.println("Please enter your last name: ");
		user.setName(response, scan.nextLine());
		System.out.println("Please enter your email address: ");
		response = scan.nextLine();
		user.seteMail(response);
		System.out.println("Please enter your mailing address: ");
		response = scan.nextLine();
		user.setAddress(response);
		System.out.println("Please enter your phone number: ");
		response = scan.nextLine();
		user.setPhone(response);
		user.setType(1);
		return user;
	}
	
	public User createNewUser(String eMail, String password) { // Junk for ease of menu entry during dev testing
		User user = new User();
		user.seteMail(eMail);
		user.setPass(password);
		user.setType(1);
		return user;
	}
	
	public void displayUserInfo(User acct) { // Not sure if will end up using, just was useful at one time
		System.out.println(acct.getName());
		System.out.println(acct.getAddress());
		System.out.println(acct.getPhone());
		System.out.println(acct.geteMail());
	}
	
	public User getUser(String eMail, String password) { // more junk different line
		User user = createNewUser(eMail, password);
		user.setType(1);
		return user;
	}
	
	public int getType(User user) {
		return user.getType();
	}
	
	
	/*
	void SetType(String input){  e.g. accountHolder->employee->admin, admin->employee->accountholder, admin->accountholder and should only be used in AdminController
	}
	*/
	
	void assignAccount( User user) { //This will be used by all employees to open approved accounts, account service exists in skeleton form
		
	}
	
	/*void JoinAccount( worry about this later)
	
	updating fields of User object passed in should be implemented. 
	
*/
}
