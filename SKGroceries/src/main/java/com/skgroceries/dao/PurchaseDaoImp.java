package com.skgroceries.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skgroceries.model.Cart;
import com.skgroceries.model.Product;
import com.skgroceries.util.DbConnection;
import com.skgroceries.util.UserQueries;

public class PurchaseDaoImp implements IPurchaseDao {

	/**
	 * @param productId
	 * @param quantity
	 * @return A Product object
	 * 
	 * 
	 */
	@Override
	public Product getPurchaseProduct(int productId, int quantity) {
		PreparedStatement statement = null;
		Product product = new Product();
		ResultSet resultSet=null;
		Connection connection = DbConnection.openConnection();
		try {
			
			statement = connection.prepareStatement(UserQueries.QUERYFORPRODUCT);
			statement.setInt(1, productId);
			statement.setInt(2, quantity);
			
			resultSet = statement.executeQuery();
			while (resultSet.next()) {

				product.setProductId(resultSet.getInt(1));
				product.setProductName(resultSet.getString(2));
				product.setBrand(resultSet.getString(3));
				product.setCategory(resultSet.getString(4));
				product.setQuantityInKgs(resultSet.getInt(5));
				product.setPrice(resultSet.getDouble(6));
				product.setCount(resultSet.getInt(7));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (statement != null)
					statement.close();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return product;
	}

	/**
	 * @param products
	 */
	@Override
	public void updatePurchase(List<Cart> products) {
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		Connection connection = DbConnection.openConnection();
		ResultSet resultSet=null;
		try {
		for (Cart product : products) {
			int count=0;
			statement1 =  connection.prepareStatement(UserQueries.QUERYFORCOUNT);
			statement1.setInt(1,product.getProductId());
			resultSet =  statement1.executeQuery();
			while(resultSet.next()) {
				count = resultSet.getInt(1);
			}
			statement2 = connection.prepareStatement(UserQueries.QUERYFORUPDATE);
			statement2.setInt(1, count);
			statement2.setInt(2, product.getQuantity());
			statement2.setInt(3, product.getProductId());
			
			statement2.execute();
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (statement1 != null)
					statement1.close();
				if (statement2 != null)
					statement2.close();
				if(resultSet != null)
					resultSet.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
	}
		

}

	


