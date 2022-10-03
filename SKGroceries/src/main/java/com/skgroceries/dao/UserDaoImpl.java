package com.skgroceries.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skgroceries.model.Cart;
import com.skgroceries.model.Product;
import com.skgroceries.model.User;
import com.skgroceries.util.DbConnection;
import com.skgroceries.util.UserQueries;

public class UserDaoImpl implements IUserDao {

	PreparedStatement statement = null;
	
	@Override
	public boolean addUser(User user) {
		Connection connection = DbConnection.openConnection();
		int result =0;
		try {
			statement = connection.prepareStatement(UserQueries.QUERYFORADDUSER);
			statement.setString(1, user.getName());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getLocation());
			statement.setLong(5, user.getMobileNumber());
			statement.setInt(6, 0);
			
			result = statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (statement != null) {
					statement.close();
				}
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		if(result == 1) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public int loginValidation(String userName, String password) {
		int result = 0;
		ResultSet resultSet=null;
		Connection connection = DbConnection.openConnection();
		try {
			
			statement = connection.prepareStatement(UserQueries.QUERYFORLOGIN);
			statement.setString(1, userName);
			statement.setString(2, password);
			
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getInt(7);
			}else {
				result = 2  ;    //If user not found in the DB
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (statement != null)
					statement.close();
				if(resultSet != null)
					statement.close();			

			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return result;
	}

	@Override
	public Product getPurchaseProduct(int productId, int quantity) {
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

	@Override
	public void updatePurchase(List<Cart> products) {
		Connection connection = DbConnection.openConnection();
		ResultSet resultSet=null;
		try {
		for (Cart product : products) {
			int count = 0;
			statement =  connection.prepareStatement(UserQueries.QUERYFORCOUNT);
			statement.setInt(1,product.getProductId());
			resultSet =  statement.executeQuery();
			while(resultSet.next()) {
				count = resultSet.getInt(1);
			}
			statement = connection.prepareStatement(UserQueries.QUERYFORUPDATE);
			statement.setInt(1, count);
			statement.setInt(2, product.getQuantity());
			statement.setInt(3, product.getProductId());
			
			statement.execute();
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (statement != null)
					statement.close();
				if(resultSet != null)
					statement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
	}
		

}

	


