package com.revature.services;

import com.revature.models.User;
import com.revature.services.UserService;

public class LoginService {
	
	UserService userservice = new UserService();
	
	public User createNewUser() {
		return userservice.createNewUser();
	}
	public User login(String eMail, String password) {
		return userservice.createNewUser(eMail, password);
	}
		
		
}
