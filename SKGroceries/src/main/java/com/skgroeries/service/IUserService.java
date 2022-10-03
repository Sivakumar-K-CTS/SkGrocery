package com.skgroeries.service;

import java.util.List;

import com.skgroceries.exceptions.UserNotFoundException;
import com.skgroceries.model.Cart;
import com.skgroceries.model.Product;
import com.skgroceries.model.User;

public interface IUserService {
	
	public int loginValidation(String userName, String Password) throws UserNotFoundException;
	public boolean register(User user);
	public Product productAvailablity(int productId, int quantity);
	public void updatePurchase(List<Cart> products);
}
