package com.revature.daos;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface UserDAO {

	public List<User> getAll();
	public int addUser(User user);
	//public boolean deleteUser(User user); //must close and delete any Accounts for Consistency
	public boolean updateUser(User user); // must re-enter user password to rehash to account for changes made. 
	public User getByID(int userID); 
	public boolean updateUserAccount(Account account, User user);
}
