package com.revature.daos;

import java.util.List;

import com.revature.models.Name;

public interface NameDAO {
	
	public List<Name> getAll();
	public Name findById(int id);
	public int addName(Name name);
	public boolean updateName(Name name);

}
