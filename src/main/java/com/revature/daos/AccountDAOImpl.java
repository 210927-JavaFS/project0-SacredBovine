package com.revature.daos;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {
	
	@Override
	public boolean addAccount(Account account, User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO accounts (account_type, balance) "
			+ "VALUES (?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setString(++count, String.valueOf(account.getType()));
			statement.setString(++count, String.valueOf(account.getBalance()));
			
			statement.execute();
			return true;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	@Override
	public List<Account> getAll() {
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT * FROM accounts;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			List<Account> accounts = new ArrayList<>();
			while(result.next())
			{
				Account account = new Account();
				account.setID(result.getInt("account_id"));
				account.setType(result.getString("account_type"));
				account.setBalance(result.getDouble("balance"));
				accounts.add(account);
			}
		
		return accounts;
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Account findByAccountID(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT * FROM accounts WHERE account_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, String.valueOf(id));
			ResultSet result = statement.executeQuery();
			
			Account account = new Account();
			while(result.next())
			{
				account.setID(result.getInt("account_id"));
				account.setType(result.getString("account_type"));
				account.setBalance(result.getDouble("balance"));
			}
			return account;
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Account> findByUser(User user) {
		/*try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT * FROM users WHERE user_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, String.valueOf(user.getID()));
			ResultSet result = statement.executeQuery();
			
			//User get Accounts method required
			
			List<Account> accounts = new ArrayList<>();
			while(result.next())
			{
				Account account = new Account();
				account.setID(result.getInt("account_id"));
				account.setType(result.getString("account_type"));
				account.setBalance(result.getDouble("balance"));
				accounts.add(account);
			}
			return accounts;
			}catch (SQLException e) {
				e.printStackTrace();
			}*/
		return null;
	}

	@Override
	public boolean updateAccount(Account account) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE accounts SET balance = ?, account_type = ? where account_number = ?; "
			+ "VALUES (?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setString(++count, String.valueOf(account.getBalance()));
			statement.setString(++count, account.getType());
			statement.setString(++count, String.valueOf(account.getID()));
			statement.execute();
			
			return true;
			
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
	
		return false;
	}

}