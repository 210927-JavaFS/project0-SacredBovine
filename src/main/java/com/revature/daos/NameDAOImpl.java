package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Name;

import com.revature.utils.ConnectionUtil;

public class NameDAOImpl implements NameDAO{

	@Override
	public List<Name> getAll() {
		try(Connection conn = ConnectionUtil.getConnection()){ 
			String sql = "SELECT * FROM names;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			List<Name> names = new ArrayList<>();
			while(result.next())
			{
				Name name = new Name();
				name.setId(result.getInt("name_id"));
				name.setFirstName(result.getString("first_name"));
				name.setLastName(result.getString("last_name"));
				names.add(name);
			}
			return names;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Name findById(int id) {
		try(Connection conn = ConnectionUtil.getConnection())
		{ 
			String sql = "SELECT * FROM names WHERE name_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,String.valueOf(id));
			ResultSet result = statement.executeQuery();
			
			Name name = new Name();		
			while(result.next()) 
			{
				name.setId(result.getInt("name_id"));
				name.setFirstName(result.getString("first_name"));
				name.setLastName(result.getString("last_name"));	
			}
			return name;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addName(Name name) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO names (first_name, last_name "
					+ "VALUES (?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			int count=0;
			statement.setString(++count, name.getFirstName());
			statement.setString(++count, name.getLastName());
			statement.execute();
			
			return true;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateName(Name name) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE names SET first_name = ?, last_name = ? where name_id = ?; "
			+ "VALUES (?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setString(++count, name.getFirstName());
			statement.setString(++count, name.getLastName());
			statement.setString(++count, String.valueOf(name.getID()));
			statement.execute();
			
			return true;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
}
