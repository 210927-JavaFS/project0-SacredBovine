package com.revature.daos;

import java.util.List;

import com.revature.models.Request;

public interface RequestDAO {
	
	public List<Request> getAllRequests();
	public boolean deleteRequest();
	public boolean addRequest(int type, String message);
	
}
