package com.revature.daos;

import java.util.List;

import com.revature.models.Address;

public interface AddressDAO {

	public List<Address> getAll();
	public Address findByID(int id);
	public boolean updateAddress(Address address);
	public boolean addAddress(Address address);
	
}
