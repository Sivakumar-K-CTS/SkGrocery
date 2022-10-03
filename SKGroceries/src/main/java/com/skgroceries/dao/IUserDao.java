package com.skgroceries.dao;

import java.util.List;

import com.skgroceries.model.Cart;
import com.skgroceries.model.Product;
import com.skgroceries.model.User;

public interface IUserDao {
	public boolean addUser(User user);
	public int loginValidation(String userName, String Password);
	public Product getPurchaseProduct(int productId,int quantity);
	public void updatePurchase(List<Cart> products);
}
