package com.revature.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.daos.RequestDAO;
import com.revature.daos.RequestDAOImpl;
import com.revature.models.Request;
import com.revature.models.User;

public class RequestService {
	private static RequestDAO requestDAO = new RequestDAOImpl();
	
	public List<Request> getRequests() {
		return requestDAO.getAllRequests();
	}
	
	public boolean createRequest(int type, String message, User user) {
		Request request = new Request(type, message, user.getID());
		return requestDAO.addRequest(request);
	}
	
	public boolean closeRequest(Request request) {
		return requestDAO.closeRequest(request);
	}
	
/*	public boolean exists(int ID) {
		List<Request> requests = getRequests();
		for ( int i = 0; i < requests.size(), i++)
		{
			
		}
	}
/*	public boolean createAccountRequest(int type, String message User user) {
		Request request = new Request(type, user.getID());
		return requestDAO.addRequest(request, userID);
	}*/
}

