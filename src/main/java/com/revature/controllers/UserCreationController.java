package com.revature.controllers;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.models.Address;
import com.revature.models.Name;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserCreationController {

	private Scanner scan = new Scanner(System.in);
	private UserService userService = new UserService();

// This method requires all sorts of input validation	
	public boolean createNewUser() {
		System.out.println("Please enter your email address: ");
		String eMail = scan.nextLine().toLowerCase();
		
// Password stuff. Know which hashing, just need to implement with service or utility.
		System.out.println("Please enter a password: ");
		String password = scan.nextLine(); // yeah I know
		
		
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
}