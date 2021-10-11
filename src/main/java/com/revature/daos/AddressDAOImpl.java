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

import com.revature.models.Address;
import com.revature.utils.ConnectionUtil;

public class AddressDAOImpl implements AddressDAO{

	@Override
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
		try(Connection conn = ConnectionUtil.getConnection()){  
			
			/*String sql = "SELECT * FROM names WHERE name_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1,id);
			ResultSet result = statement.executeQuery();*/
			
			
			
			
			String sql = "SELECT * FROM addresses WHERE address_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Address address = new Address();
			while(result.next()) {
				address.setAddressID(result.getInt("address_id"));
				address.setStreetNumber(result.getString("street_number"));
				address.setStreetName(result.getString("street_name"));
				address.setCity(result.getString("city"));
				address.setRegion(result.getString("region"));
				address.setZipcode(result.getString("zipcode"));
				address.setCountry(result.getString("country"));
			}
			return address;
			//System.out.println("find address by ID : "+ String.valueOf(id) +" : "+ address.toString());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		Address address = new Address();
		return address;
	}

	@Override
	public boolean updateAddress(Address address) {
		try(Connection conn = ConnectionUtil.getConnection()){
			//NameDAO nameDAO = new NameDAOImpl();
			AddressDAO addressDAO = new AddressDAOImpl();
			//			
			Address current = addressDAO.findByID(address.getAddressID());
			if (current == address) {
				return true ;
			}
			else if(current.getAddressID() == address.getAddressID())
			{
				
			}
			else { 
				// Update address table
				String sql = "UPDATE address SET street_number = ?, street_name = ? where city = ?, region = ?, zipcode = ?, country = ?; "
				+"WHERE address_id = ? ;";
				PreparedStatement statement = conn.prepareStatement(sql);
				int count = 0;
				statement.setString(++count, address.getStreetNumber());
				statement.setString(++count, address.getStreetName());
				statement.setString(++count, address.getCity());
				statement.setString(++count, address.getRegion());
				statement.setString(++count, address.getZipcode());
				statement.setString(++count, address.getCountry());
				statement.setString(++count, String.valueOf(address.getAddressID()));
				statement.execute();
				return true;
			}
		}
			catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int addAddress(Address address) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO addresses (street_number, street_name, city, region, zipcode, country, new_address_flag) "
					+ "VALUES (?,?,?,?,?,?,TRUE);";
			PreparedStatement statement = conn.prepareStatement(sql);		
			int count=0;
			statement.setString(++count, address.getStreetNumber());
			statement.setString(++count, address.getStreetName());
			statement.setString(++count, address.getCity());
			statement.setString(++count, address.getRegion());
			statement.setString(++count, address.getZipcode());
			statement.setString(++count, address.getCountry());
			statement.execute();
			
			sql = "SELECT address_id FROM addresses WHERE new_address_flag = TRUE AND street_number = ? AND street_name = ? "
					+"AND city = ? AND region = ? AND zipcode = ? AND country = ?;";
			statement = conn.prepareStatement(sql);
			count=0;
			statement.setString(++count, address.getStreetNumber());
			statement.setString(++count, address.getStreetName());
			statement.setString(++count, address.getCity());
			statement.setString(++count, address.getRegion());
			statement.setString(++count, address.getZipcode());
			statement.setString(++count, address.getCountry());
			ResultSet result = statement.executeQuery();
			count=0;
			int addressID = -1;
			if(result.next()) {
				addressID = result.getInt(1);
			}
			else System.out.println("Could not fetch address id"); 
			
			if (addressID >= 0) {
				sql = "UPDATE addresses SET new_address_flag = FALSE WHERE address_id = ?;";
				statement = conn.prepareStatement(sql);
				statement.setInt(1,addressID);
				statement.execute();
			} 
			else System.out.println("Address id < 0");
											
			return addressID;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}