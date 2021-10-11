package com.revature.daos;

import java.util.List;

import com.revature.models.Request;
import com.revature.models.User;

public interface RequestDAO {
	
	public List<Request> getAllRequests();
	public boolean closeRequest(Request request);
	//public boolean addRequest(Request request, String message, int userID);
	public boolean addRequest(Request request);
	
}
