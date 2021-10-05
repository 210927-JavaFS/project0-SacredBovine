package com.revature.services;

import java.util.Scanner;

import com.revature.models.User;

public class UserService {


	private static Scanner scan = new Scanner(System.in);
	
	public void CreateNewUser(User acct) {
		String response;
		System.out.println("Please enter your first name: ");
		response = scan.nextLine();
		System.out.println("Please enter your last name: ");
		acct.setName(response, scan.nextLine());
		System.out.println("Please enter your email address: ");
		response = scan.nextLine();
		acct.seteMail(response);
		System.out.println("Please enter your mailing address: ");
		response = scan.nextLine();
		acct.setAddress(response);
		System.out.println("Please enter your phone number: ");
		response = scan.nextLine();
		acct.setPhone(response);
	}
	
	public void DisplayUserInfo(User acct) {
		System.out.println(acct.getName());
		System.out.println(acct.getAddress());
		System.out.println(acct.getPhone());
		System.out.println(acct.geteMail());
	}

}
