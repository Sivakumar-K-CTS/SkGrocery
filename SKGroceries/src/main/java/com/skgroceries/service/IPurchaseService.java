package com.skgroceries.service;

import java.util.List;

import com.skgroceries.exceptions.ProductNotFoundException;
import com.skgroceries.model.Cart;
import com.skgroceries.model.Product;

public interface IPurchaseService {
	
	public Product productPurchase(int productId, int quantity) throws ProductNotFoundException;
	public void updatePurchase(List<Cart> products);
}
