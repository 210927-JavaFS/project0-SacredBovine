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

import com.revature.models.Address;
import com.revature.models.Name;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO {
	
	private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	public boolean addUser(User user) {
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
			return true;
		}
		catch(SQLException e) {
			log.error(e.getStackTrace().toString());
		}
		return false;
	}
	
	public User getByID(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			NameDAO nameDAO = new NameDAOImpl();
			AddressDAO addressDAO = new AddressDAOImpl();
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
			}
			return user;
		}catch (SQLException e) {
			log.error(e.getStackTrace().toString());
		}
		return null;
	}
	public User getByEMail(String eMail) {
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			NameDAO nameDAO = new NameDAOImpl();
			AddressDAO addressDAO = new AddressDAOImpl();
			String sql = "SELECT * FROM users WHERE email_address = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, eMail);
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
			}
			return user;
		}catch (SQLException e) {
			log.error(e.getStackTrace().toString());
		}
		return null;
	}
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
				user.setType(result.getInt("user_type"));
				user.setPhone(result.getString("phone_number"));
				
				users.add(user);
			}
			return users;
		}
		catch (SQLException e) {
			log.error(e.getStackTrace().toString());
		}
		System.out.println("getAll userDAO returing NULL");
		return null;
	}
	
	public boolean updateUser(User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			NameDAO nameDAO = new NameDAOImpl();
			AddressDAO addressDAO = new AddressDAOImpl();
			
			// Update users table
			String sql = "UPDATE users SET password_key = ?, email_address = ?, user_type = ? WHERE user_id = ? ;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setString(++count, user.getPass());
			statement.setString(++count, user.getEMail());
			statement.setInt(++count, user.getType());
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
			log.error(e.getStackTrace().toString());
		}
		return false;
	}
		
}
