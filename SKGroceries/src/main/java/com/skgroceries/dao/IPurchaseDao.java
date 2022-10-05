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
 */
public interface IPurchaseDao {
	public Product getPurchaseProduct(int productId,int quantity);
	public void updatePurchase(List<Cart> products);
}
