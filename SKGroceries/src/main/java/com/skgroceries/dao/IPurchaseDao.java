/**
 * 
 */
package com.skgroceries.dao;

import java.util.List;

import com.skgroceries.model.Cart;
import com.skgroceries.model.Product;

/**
 * @author SivakumarK
 * 
 * Action: This is an Interface contains 2 abstract methods. 
 * getPurchaseProduct(int productId,int quantity) - This method will connect to DB and retrieve a Product object of the productId provided by the user and as well as validate if the input quantity is lesser than and equal to the quantity present in the table already.  
 * updatePurchase(List<Cart> products) - This method will just updated the products table with the value of quantity given by the user subtracted with already present value.
 *
 */
public interface IPurchaseDao {
	public Product getPurchaseProduct(int productId,int quantity);
	public void updatePurchase(List<Cart> products);
}
