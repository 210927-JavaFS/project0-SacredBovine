package com.revature.daos;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface AccountDAO {

	public List<Account> getAll();
	public Account findByAccountID(int id);
	public List<Account> findByUser(User user);
	public boolean updateAccount(Account account);
	public int addAccount(Account account);
	
}
