package com.revature.daos;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface AccountDAO {

	public int addAccount(Account account);
	public List<Account> getAll();
	public List<Account> getAllByID(int userAccountsID);
	public Account GetByID(int id);
	public boolean updateAccount(Account account);
	public boolean addUserAccount(User user, Account account);
	public boolean deleteUserAccount(User user, Account account);
	
	
}
