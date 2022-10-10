/**
 * 
 */
package com.skgroceries.service;

import java.util.List;

import com.skgroceries.dao.IPurchaseDao;
import com.skgroceries.dao.PurchaseDaoImp;
import com.skgroceries.exceptions.ProductNotFoundException;
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
	 * @return A Product Object
	 * @throws ProductNotFoundException 
	 * 
	 * Action:  Will provide the response of the DAO layer(method - getPurchaseProduct()) to the main method.  If the response is null will throw the exception.
	 * 
	 */
	@Override
	public Product productPurchase(int productId, int quantity) throws ProductNotFoundException {
		Product product= purchaseDao.getPurchaseProduct(productId,quantity);
		if(product==null) {
			throw new ProductNotFoundException("Invalid product ID or Quantity greater than reserve!!");
		}else {
			return product;
		}
	}


	/**
	 * @param products
	 * 
	 * Action: Will call the updatePurchase method which is in the DAO Implementation class.
	 */
	@Override
	public void updatePurchase(List<Cart> products) {
		purchaseDao.updatePurchase(products);
	}
	
}
