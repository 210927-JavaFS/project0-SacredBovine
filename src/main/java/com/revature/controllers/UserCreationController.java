package com.revature.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.models.Address;
import com.revature.models.Name;
import com.revature.models.User;
import com.revature.services.PasswordService;
import com.revature.services.UserService;

public class UserCreationController {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	private Scanner scan = new Scanner(System.in);
	private UserService userService = new UserService();
	private PasswordService passwordService = new PasswordService();

// This method requires all sorts of input validation	
	public boolean createNewUser() {
		try {
		
			System.out.println("Please enter your email address: ");
			String eMail = scan.nextLine().toLowerCase();
		
// Password stuff. Know which hashing, just need to implement with service or utility.
			System.out.println("Please enter a password: ");
			String password = passwordService.toHexString(passwordService.getSHA(scan.nextLine().trim())); // yeah I know
		
		
			System.out.println("Please enter your first name: ");
			String firstName = scan.nextLine().trim();
			System.out.println("Please enter your last name: ");
			String lastName = scan.nextLine().trim();
			System.out.println("Please enter your house/unit #: ");
			String streetNumber = scan.nextLine().trim();
			System.out.println("Please enter your street: ");
			String streetName = scan.nextLine().trim();
			System.out.println("Please enter your city: ");
			String city = scan.nextLine().trim();
			System.out.println("Please enter your state/region: ");
			String region = scan.nextLine().trim();
			System.out.println("Please enter your zipcode: ");
			String zipcode = scan.nextLine().trim();
			System.out.println("Please enter your country: ");
			String country = scan.nextLine().trim();
			System.out.println("Please enter your phone number: ");
			String phoneNumber = scan.nextLine().trim();
			return userService.createNewUser(eMail, password, firstName, lastName, streetNumber, streetName, city, region, zipcode, country, phoneNumber);
		}
		catch ( NoSuchAlgorithmException e)
		{
			System.out.println("Could not hash pass.");
			log.error(" Could not Hash password. "+e.getStackTrace().toString());
		}
		return false;
	}
}
