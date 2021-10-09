package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Address;
import com.revature.utils.ConnectionUtil;

public class AddressDAOImpl implements AddressDAO{

	@Override
	public List<Address> getAll() {
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT * FROM addresses;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Home> list = new ArrayList<>();
			
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used 
			//with a while loop to iterate through all the data. 
			
			while(result.next()) {
				Address address = new Address();
				address.setName(result.getInteger("address_id"));
				address.setStreetNumber(result.getString("street_number"));
				address.setStreetName(result.getString("street_name"));
				address.setCity(result.getString("city"));
				address.setRegion(result.getString("region"));
				address.setZip(result.getString("zipcode"));
				address.setCountry(result.getString("country"));
				list.add(address);
			}
			
			return list;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Address> getAll() {
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT * FROM names;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Address> addresses = new ArrayList<>();
						
			while(result.next()) {
				Address address = new Address();
				address.setAddressID(result.getInt("address_id"));
				address.setStreetNumber(result.getString("street_number"));
				address.setStreetName(result.getString("street_name"));
				address.setCity(result.getString("city"));
				address.setRegion(result.getString("region"));
				address.setZipcode(result.getString("zipcode"));
				address.setCountry(result.getString("country"));
				addresses.add(address);
			}
			return addresses;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Address findByID(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT street_number, street_name, city, region, zipcode, country FROM addresses WHERE address_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, String.valueOf(id));
			ResultSet result = statement.executeQuery();
			Address address = new Address();
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used 
			//with a while loop to iterate through all the data. 
			
			while(result.next()) {
				
				address.setStreetNumber(result.getString("street_number"));
				address.setStreetName(result.getString("street_name"));
				address.setCity(result.getString("city"));
				address.setRegion(result.getString("region"));
				address.setZipcode(result.getString("zipcode"));
				address.setCountry(result.getString("country"));
			}
			return address;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateAddress(Address address) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAddress(Address address) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO addresses (street_number, street_name, city, region, zipcode, country) "
					+ "VALUES (?,?,?,?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count=0;
			statement.setString(++count, address.getStreetNumber());
			statement.setString(++count, address.getStreetName());
			statement.setString(++count, address.getCity());
			statement.setString(++count, address.getRegion());
			statement.setString(++count, address.getZipcode());
			statement.setString(++count, address.getCountry());
			
			statement.execute();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}