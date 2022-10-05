package com.skgroceries.service;

import java.util.List;

import com.skgroceries.model.Cart;
import com.skgroceries.model.Product;

public interface IPurchaseService {
	
	public Product productAvailablity(int productId, int quantity);
	public void updatePurchase(List<Cart> products);
}
