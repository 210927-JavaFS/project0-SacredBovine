package com.revature.services;

import java.util.List;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class LoginService {
	
	UserService userservice = new UserService();
	UserDAO userDAO = new UserDAOImpl();
	
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