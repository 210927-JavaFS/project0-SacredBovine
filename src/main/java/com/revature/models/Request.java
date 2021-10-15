package com.revature.models;

public class Request {
	/*Hold UserID , request type, and be able to be stored in DB for employees/admins to access/approve*/
	private int requestID;
	private int type; // type 1 or 2. check if 0 => bad request -> log and delete request
	private int requestUserID;
	private String requestMessage;
	
	public Request() {
		super();
	}
	public Request(int typeInput, int userID) {
		this.type = typeInput;
		this.requestUserID = userID;
	}
	public Request(int typeInput, String message, int userID) {
		this.type = typeInput;
		this.requestMessage = message;
		this.requestUserID = userID;
	}
// Getters
	public int getRequestID() {
		return requestID;
	}
	public String getMessage() {
		return requestMessage;
	}
	public int getRequestUserID() {
		return requestUserID;
	}
	public int getType() {
		return type;
	}
	
// Setters
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public void setMessage(String message) {
		this.requestMessage = message;
	}
	public void setRequestUserID(int requestUserID) {
			this.requestUserID = requestUserID;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String toString() {
		return " User : " + String.valueOf(this.requestUserID) +" \n Request type: "
				+ this.requestMessage +" \n";
	}

}