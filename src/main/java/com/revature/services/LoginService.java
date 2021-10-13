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
	
/*	public int login(String eMail, String password) {
		List<User> users = userDAO.getAll();
		for (int i = 0; i < users.size();i++) {
			if (users.get(i).getEMail().equals(eMail)) {
				if( users.get(i).getPass().equals(password)) {
					return users.get(i).getID();
				} 
			} 
			
		}
		System.out.println("Email or Password were incorrect. \n Please try again.");
		return 0;
	}*/
	
	public User login(String eMail, String password) {
		List<User> users = userDAO.getAll();
		for (int i = 0; i < users.size();i++) {
			if (users.get(i).getEMail().equals(eMail)) {
				if( users.get(i).getPass().equals(password)) {
					return users.get(i);
				} 
			} 			
		}
		return null;
	}
}
