package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.models.Account;
import com.revature.models.Address;
import com.revature.models.Name;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO {
	
	public List<User> getAll() {
		try(Connection conn = ConnectionUtil.getConnection()){ 
			NameDAO nameDAO = new NameDAOImpl();
			AddressDAO addressDAO = new AddressDAOImpl();
			
			String sql = "SELECT * FROM users;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<User> users = new ArrayList<>();
			
			while(result.next())
			{
				User user = new User();
				user.setUserID(result.getInt("user_id"));
				user.setName(nameDAO.findById(result.getInt("name_id")));
				user.setPass(result.getString("password_key"));
				user.setEMail(result.getString("email_address"));
				user.setAddress(addressDAO.findByID(result.getInt("address_id")));
				user.setPhone(result.getString("phone_number"));
				users.add(user);
			}
			return users;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("getAll userDAO returing NULL");
		return null;
	}
	
	public int addUser(User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO users (name_id, password_key, email_address, address_id, phone_number, user_type, new_user_flag)"
					+ "VALUES (?,?,?,?,?,?,TRUE);";
			PreparedStatement statement = conn.prepareStatement(sql);
			int count=0;
			statement.setInt(++count, user.getName().getID());
			statement.setString(++count, user.getPass());
			statement.setString(++count, user.getEMail());
			statement.setInt(++count, user.getAddress().getAddressID());
			statement.setString(++count, user.getPhone());
			statement.setInt(++count, user.getType());
			statement.execute();
			
			sql = "SELECT user_id FROM users WHERE new_user_flag = TRUE AND email_address= ? AND password_key = ?;";
			statement = conn.prepareStatement(sql);
			count = 0;
			statement.setString(++count, user.getEMail());
			statement.setString(++count, user.getPass());
			ResultSet result = statement.executeQuery();
			
			int userID = -1;
			if(result.next()) {
				userID = result.getInt(1);
			}
			else System.out.println("Could not fetch user id"); 
			
			if (userID >= 0) {
				sql = "UPDATE users SET new_user_flag = FALSE WHERE user_id = ?;";
				statement = conn.prepareStatement(sql);
				statement.setInt(1, userID);
				statement.execute();
			} 
			else System.out.println("User id < 0");
						
			return userID;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public User getByID(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			NameDAO nameDAO = new NameDAOImpl();
			AddressDAO addressDAO = new AddressDAOImpl();
			AccountDAO accountDAO = new AccountDAOImpl();
			String sql = "SELECT * FROM users WHERE user_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			User user = new User();
			while(result.next())
			{
				user.setUserID(result.getInt("user_id"));
				user.setName(nameDAO.findById(result.getInt("name_id")));
				user.setPass(result.getString("password_key"));
				user.setEMail(result.getString("email_address"));
				user.setAddress(addressDAO.findByID(result.getInt("address_id")));
				user.setPhone(result.getString("phone_number"));
				user.setType(result.getInt("user_type"));
				int checking = result.getInt("checking_account");
				int savings = result.getInt("savings_account");
				int joint = result.getInt("joint_account");
				System.out.println("checking : " + String.valueOf(checking) + "\n "
						+ "savings : " + String.valueOf(savings)+ "\n "
						+ "checking : " + String.valueOf(joint)+ "\n ");
				user.setCheckingAccount(accountDAO.findByAccountID(checking));
				user.setSavingsAccount(accountDAO.findByAccountID(result.getInt("savings_account")));
				user.setJointAccount(accountDAO.findByAccountID(result.getInt("joint_account")));
			}
			return user;
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateUser(User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			NameDAO nameDAO = new NameDAOImpl();
			AddressDAO addressDAO = new AddressDAOImpl();
			
			// Update users table
			String sql = "UPDATE users SET password_key = ?, email_address = ?, where user_id = ? ;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setString(++count, user.getPass());
			statement.setString(++count, user.getEMail());
			/*if(user.getCheckingAccount().getID() > 10000000) statement.setInt(++count, user.getCheckingAccount().getID());
			else statement.setboolean(++count, 0);
			if(user.getSavingsAccount().getID() > 10000000) statement.setInt(++count, user.getSavingsAccount().getID());
			else statement.setInt(++count, 0);
			if(user.getJointAccount().getID() > 10000000) statement.setInt(++count, user.getJointAccount().getID());
			else statement.setInt(++count, 0);*/
			statement.setInt(++count, user.getID());
			statement.execute();
			
			// Update names table
			Name name = user.getName();
			nameDAO.updateName(name);
			Address address = user.getAddress();
			addressDAO.updateAddress(address);
			return true;
		}
		catch(SQLException e) {
			System.out.println("Didn't get connection \n");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateUserAccount(Account account, User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			switch (account.getType()) {
				case "checking" : {
					String sql = "UPDATE users SET checking_account = ? WHERE user_id = ? ;";
					PreparedStatement statement = conn.prepareStatement(sql);
					int count = 0;
					statement.setInt(++count, account.getID());
					statement.setInt(++count, user.getID());
					statement.execute();
					return true;
				}
				case "savings" : {
					String sql = "UPDATE users SET savings_account = ? WHERE user_id = ? ;";
					PreparedStatement statement = conn.prepareStatement(sql);
					int count = 0;
					statement.setInt(++count, account.getID());
					statement.setInt(++count, user.getID());
					statement.execute();
					return true;
				}
				case "joint" : {
					String sql = "UPDATE users SET joint_account = ? WHERE user_id = ? ;";
					PreparedStatement statement = conn.prepareStatement(sql);
					int count = 0;
					statement.setInt(++count, account.getID());
					statement.setInt(++count, user.getID());
					statement.execute();
					return true;
					}
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return false; 
		}
}
