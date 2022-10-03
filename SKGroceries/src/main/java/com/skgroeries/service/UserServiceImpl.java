package com.skgroeries.service;

import java.util.List;

import com.skgroceries.dao.IUserDao;
import com.skgroceries.dao.UserDaoImpl;
import com.skgroceries.exceptions.UserNotFoundException;
import com.skgroceries.model.Cart;
import com.skgroceries.model.Product;
import com.skgroceries.model.User;

public class UserServiceImpl implements IUserService {

	IUserDao userDao = new UserDaoImpl();
	@Override
	public int loginValidation(String username, String password) throws UserNotFoundException {
		int loginResult = userDao.loginValidation(username, password);
		if(loginResult !=1 && loginResult !=0) {
			throw new UserNotFoundException("INVALD CREDENTIALS***");
		}
		return loginResult;
	}
	
	@Override
	public boolean register(User user) {
		return userDao.addUser(user);
	}

	@Override
	public Product productAvailablity(int productId, int quantity) {
		return userDao.getPurchaseProduct(productId,quantity);
	}

	@Override
	public void updatePurchase(List<Cart> products) {
		userDao.updatePurchase(products);
		
	}

}
