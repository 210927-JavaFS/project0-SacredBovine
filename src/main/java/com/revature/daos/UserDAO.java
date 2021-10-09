package com.revature.daos;

import com.revature.models.User;

public interface UserDAO {

	public boolean createUser(User user);
	public boolean deleteUser(User user); //must close and delete any Accounts for Consistency
	public boolean updateUser(User user);
}
