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
/*
	@Override
	public boolean updateHome(Home home) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addHome(Home home) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO homes (home_name, home_number, home_street, home_city, home_region, home_zip, home_country, residents) "
					+ "VALUES (?,?,?,?,?,?,?,?);";
			
			int count = 0;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++count, home.getName());
			statement.setString(++count, home.getStreetNumber());
			statement.setString(++count, home.getStreetName());
			statement.setString(++count, home.getCity());
			statement.setString(++count, home.getRegion());
			statement.setString(++count, home.getZip());
			statement.setString(++count, home.getCountry());
			statement.setInt(++count, home.getResidents());
			
			statement.execute();
			
			return true;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
*/
}
