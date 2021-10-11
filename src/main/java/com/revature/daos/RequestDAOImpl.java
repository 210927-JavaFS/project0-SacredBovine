package com.revature.daos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Request;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class RequestDAOImpl implements RequestDAO{
		
	public List<Request> getAllRequests() {
		try(Connection conn = ConnectionUtil.getConnection()){ 
				String sql = "SELECT * FROM requests;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Request> requests = new ArrayList<>();
			while(result.next())
			{
				Request request = new Request();
				request.setRequestID(result.getInt("request_id"));
				request.setType(result.getInt("request_type"));
				request.setMessage(result.getString("request_message"));
				request.setRequestUserID(result.getInt("request_user_id"));;
				requests.add(request);
			}
			return requests;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
/*	public boolean addRequest(Request request, String message, int userID) { // For use with requests with message 
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO requests (request_type, request_message, user_id) "
					+ "VALUES (?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			int count=0;
			statement.setInt(++count, request.getType());
			statement.setString(++count, request.getMessage());
			statement.setInt(++count, userID);
			statement.execute();
			
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}*/
	public boolean addRequest(Request request) { 
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO requests (request_type, request_message, request_user_id) "
					+ "VALUES (?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			int count=0;
			statement.setInt(++count, request.getType());
			statement.setString(++count, request.getMessage());
			statement.setInt(++count, request.getRequestUserID());
			statement.execute();
			
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean closeRequest(Request request) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM requests WHERE request_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			int count=0;
			statement.setInt(++count, request.getRequestID());
			statement.execute();
			
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}	
}