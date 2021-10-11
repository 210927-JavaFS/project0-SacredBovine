package com.revature.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;
import com.revature.services.UserService;

public class LoginService {
	
	UserService userservice = new UserService();
	UserDAO userDAO = new UserDAOImpl();
	
	public int login(String eMail, String password) {
		List<User> users = userDAO.getAll();
		System.out.println( "Users size :" + String.valueOf(users.size()));
		for (int i = 0; i < users.size();i++) {
			System.out.println(users.get(i).getEMail() + " " + eMail + " " +users.get(i).getPass() + " " + password);
			if (users.get(i).getEMail().equals(eMail)) {
				if( users.get(i).getPass().equals(password)) {
					return users.get(i).getID();
				} 
			} 
			
		}
		System.out.println("Email or Password were incorrect. \n Please try again.");
		return 0;
	}
}
