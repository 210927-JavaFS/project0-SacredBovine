package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Name;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAOImpl {
	
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
		return null;
	}
	public User getByID(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			NameDAO nameDAO = new NameDAOImpl();
			AddressDAO addressDAO = new AddressDAOImpl();
			String sql = "SELECT * FROM users WHERE user_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, String.valueOf(id));
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
			String sql = "UPDATE users SET password_key = ?, email_address = ? where user_id = ?; "
			+ "VALUES (?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setString(++count, String.valueOf(user.getPass()));
			statement.setString(++count, user.getEMail());
			statement.setString(++count, String.valueOf(user.getID()));
			statement.execute();
			
			// Update names table
			Name name = new Name(user.getFirstName(), user.getLastName());
			nameDAO.updateName(name);
			// Update addresses table
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
