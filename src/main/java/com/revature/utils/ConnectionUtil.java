package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://javafs-mydb.cxais73emmx9.us-east-2.rds.amazonaws.com:5432/javafs210927";
		String username = "postgres"; //It is possible to use env variables to hide this information
		String password = "password"; //you would access them with System.getenv("var-name");
		
		return DriverManager.getConnection(url, username, password);
	}
}

//will leave as is for now