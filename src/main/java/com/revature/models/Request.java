package com.revature.models;

public class Request {
	/*Hold UserID , request type, and be able to be stored in DB for employees/admins to access/approve*/
	private int type; // type 1 or 2. check if 0 => bad request -> log and delete request
	private String userID; // fetched User type should match appropriate request type
}
