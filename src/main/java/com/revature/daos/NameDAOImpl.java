package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
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
			statement.setInt(1,id);
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
	public int addName(Name name) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO names (first_name, last_name, new_name_flag) "
					+ "VALUES (?,?,TRUE);";
			PreparedStatement statement = conn.prepareStatement(sql);
			int count=0;
			statement.setString(++count, name.getFirstName());
			statement.setString(++count, name.getLastName());
			statement.execute();
			
			sql = "SELECT name_id FROM names WHERE new_name_flag = TRUE AND first_name = ? AND last_name = ?;";
			statement = conn.prepareStatement(sql);
			count = 0;
			statement.setString(++count,  String.valueOf(name.getFirstName()));
			statement.setString(++count, String.valueOf(name.getLastName()));
			ResultSet result = statement.executeQuery();
			
			int nameID = -1;
			if(result.next()) {
				nameID = result.getInt(1);
			}
			else System.out.println("Could not fetch name id"); 
			
			if (nameID >= 0) {
				sql = "UPDATE names SET new_name_flag = FALSE WHERE name_id = ?;";
				statement = conn.prepareStatement(sql);
				//statement.setString(1, String.valueOf(nameID));
				statement.setInt(1, nameID);
				statement.execute();
			} else System.out.println("Name id < 0");
			
			return nameID;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
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
