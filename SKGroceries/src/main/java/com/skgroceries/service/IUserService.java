package com.skgroceries.service;

import com.skgroceries.exceptions.UserNotFoundException;
import com.skgroceries.model.User;

public interface IUserService {
	
	public int loginValidation(String userName, String Password) throws UserNotFoundException;
	public boolean register(User user);
}
