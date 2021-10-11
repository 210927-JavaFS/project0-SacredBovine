package com.revature.daos;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {
	
	@Override
	public int addAccount(Account account) { // Return is the generated account number; 0 if failed.
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO accounts (account_type, balance, new_account_flag) "
			+ "VALUES (?,?,TRUE);";
			PreparedStatement statement = conn.prepareStatement(sql);
			int count = 0;
			statement.setString(++count, String.valueOf(account.getType()));
			statement.setDouble(++count, account.getBalance());
			statement.execute();
			
			sql = "SELECT account_number FROM accounts WHERE new_account_flag = TRUE AND balance = ? AND account_type = ?;";
			statement = conn.prepareStatement(sql);
			count = 0;
			statement.setDouble(++count,  account.getBalance());
			statement.setString(++count, String.valueOf(account.getType()));
			ResultSet result = statement.executeQuery();
			
			count=0;
			int accountID = -1;
			if(result.next()) {
				accountID = result.getInt(1);
			}
			else System.out.println("Could not fetch account id"); 
			if(accountID > 10000000) {
				sql = "UPDATE accounts SET new_account_flag = FALSE WHERE account_number = ?;";
				statement = conn.prepareStatement(sql);
				statement.setInt(1, accountID);
				statement.execute();
			}
			return accountID;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public List<Account> getAll() {
		try(Connection conn = ConnectionUtil.getConnection()) { 
			String sql = "SELECT * FROM accounts;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			List<Account> accounts = new ArrayList<>();
			while(result.next()) {
				Account account = new Account();
				account.setID(result.getInt("account_id"));
				account.setType(result.getString("account_type"));
				account.setBalance(result.getDouble("balance"));
				accounts.add(account);
			}
		
			return accounts;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Account findByAccountID(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM accounts WHERE account_number = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			Account account = new Account();
			while(result.next()) {
				account.setID(result.getInt("account_number"));
				account.setType(result.getString("account_type"));
				account.setBalance(result.getDouble("balance"));
			}
			return account;
		}
		catch (SQLException e) {
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
			String sql = "UPDATE accounts SET balance = ?, account_type = ? where account_number = ?; ";
			PreparedStatement statement = conn.prepareStatement(sql);
			int count = 0;
			statement.setDouble(++count, account.getBalance());
			statement.setString(++count, account.getType());
			statement.setInt(++count, account.getID());
			statement.execute();
			
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}