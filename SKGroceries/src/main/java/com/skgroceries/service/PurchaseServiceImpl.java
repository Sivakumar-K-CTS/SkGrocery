/**
 * 
 */
package com.skgroceries.service;

import java.util.List;

import com.skgroceries.dao.IPurchaseDao;
import com.skgroceries.dao.PurchaseDaoImp;
import com.skgroceries.model.Cart;
import com.skgroceries.model.Product;

/**
 * @author SivakumarK
 *
 */
public class PurchaseServiceImpl implements IPurchaseService{

	IPurchaseDao purchaseDao = new PurchaseDaoImp();
	
	/**
	 * @param productId
	 * @param quantity
	 * @return
	 */
	@Override
	public Product productAvailablity(int productId, int quantity) {
		return purchaseDao.getPurchaseProduct(productId,quantity);
	}

	
	/**
	 * @param products
	 */
	@Override
	public void updatePurchase(List<Cart> products) {
		purchaseDao.updatePurchase(products);
	}
	
}
