package com.skgroceries.dao;

import com.skgroceries.model.User;

public interface IUserDao {
	public boolean addUser(User user);
	public int loginValidation(String userName, String Password);
	
}
