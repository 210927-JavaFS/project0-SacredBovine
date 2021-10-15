package com.revature.daos;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface UserDAO {

	public List<User> getAll();
	public boolean addUser(User user);
	public boolean updateUser(User user);  
	public User getByID(int userID); 
	public User getByEMail(String eMail); 

}