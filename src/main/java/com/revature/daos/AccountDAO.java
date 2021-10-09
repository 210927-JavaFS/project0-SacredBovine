package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface AccountDAO {

	public List<Account> getAll();
	public Account findByAccountID(int id);
	public List<Account> findByUser(User user);
	public boolean updateAccount(Account account);
	public boolean addAccount(Account account, User user);
	
}
