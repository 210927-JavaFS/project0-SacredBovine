package com.revature.daos;

import java.util.List;

import com.revature.models.Request;

public interface RequestDAO {
	
	public List<Request> getAllRequests();
	public boolean closeRequest(Request request);
	public boolean addRequest(Request request);
	
}
