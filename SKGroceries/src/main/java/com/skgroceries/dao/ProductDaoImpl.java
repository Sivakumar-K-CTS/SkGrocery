package com.skgroceries.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skgroceries.model.Product;
import com.skgroceries.util.DbConnection;
import com.skgroceries.util.IProductMapper;
import com.skgroceries.util.ProductMapperImpl;
import com.skgroceries.util.ProductQueries;

public class ProductDaoImpl implements IProductDao {
	
	IProductMapper productmapRowper = new ProductMapperImpl();

	/**
	 * @param product
	 * @return A boolean value of the execution of prepareStatement
	 * 
	 * Action: This method will add a new product to the products table
	 */
	@Override
	public boolean addProduct(Product product) {
		PreparedStatement statement = null;
		Connection connection = DbConnection.openConnection();
		boolean result = false;
		try {
			statement = connection.prepareStatement(ProductQueries.ADDQUERY);

			statement.setString(1, product.getProductName());
			statement.setString(2, product.getBrand());
			statement.setString(3, product.getCategory());
			statement.setInt(4, product.getQuantityInKgs());
			statement.setDouble(5, product.getPrice());
			statement.setInt(6, product.getCount());

			int queryResult = statement.executeUpdate();

			if (queryResult == 1) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}

		return result;
	}

	/**
	 * @param id
	 * @return A boolean value of the execution of prepareStatement
	 * 
	 * Action: This method will delete a product in the products table
	 */
	@Override
	public boolean deleteProduct(int id) {
		PreparedStatement statement = null;
		Connection connection = DbConnection.openConnection();
		boolean result = false;
		try {
			statement = connection.prepareStatement(ProductQueries.DELETEQUERY);
			statement.setInt(1, id);
			int queryResult = statement.executeUpdate();
			if (queryResult == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}

		return result;
	}

	/**
	 * @param id
	 * @param price
	 * @return A boolean value of the execution of prepareStatement
	 * 
	 * Action: This method will Update the price of a product in the products table
	 */
	@Override
	public boolean updateProduct(int id, double price) {
		PreparedStatement statement = null;
		Connection connection = DbConnection.openConnection();
		boolean result = false;
		try {
			statement = connection.prepareStatement(ProductQueries.UPDATEQUERY);
			statement.setDouble(1, price);
			statement.setInt(2, id);
			int queryResult = statement.executeUpdate();
			if (queryResult == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}

		return result;
	}

	/**
	 * @param id
	 * @return A Product object 
	 * 
	 * Action: This will retrieve a product record from products table based on the productID provided by the user
	 */
	@Override
	public Product findById(int id) {
		PreparedStatement statement = null;
		Connection connection = DbConnection.openConnection();
		Product product = new Product();
		ResultSet resultSet=null;
		try {
			statement = connection.prepareStatement(ProductQueries.QUERYBYID);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				int id1 = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String brand = resultSet.getString(3);
				String category = resultSet.getString(4);
				int quantity = resultSet.getInt(5);
				double price = resultSet.getDouble(6);
				int count = resultSet.getInt(7);

				product.setProductId(id1);
				product.setBrand(brand);
				product.setCategory(category);
				product.setCount(count);
				product.setPrice(price);
				product.setProductName(name);
				product.setQuantityInKgs(quantity);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) 
					resultSet.close();
				if(resultSet != null)
					statement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}

		return product;
	}

	/**
	 * @return A List of Product objects 
	 * 
	 * Action: This will retrieve all the products from the products table
	 */
	@Override
	public List<Product> findAllProducts() {
		PreparedStatement statement = null;
		Connection connection = DbConnection.openConnection();
		List<Product> productList = new ArrayList<>();
		ResultSet resultSet=null;

		try {
			statement = connection.prepareStatement(ProductQueries.QUERYGETALL);
			resultSet = statement.executeQuery();
			
				productList = productmapRowper.mapRow(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) 
					resultSet.close();
				if(resultSet != null)
					statement.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return productList;
	}

	/**
	 * @param category
	 * @return A List of Product objects 
	 * 
	 * Action: This will retrieve all the products from the products table based on the Category provided by the user
	 */
	@Override
	public List<Product> findByCategory(String category) {
		PreparedStatement statement = null;
		Connection connection = DbConnection.openConnection();
		List<Product> producetList = new ArrayList<>();
		ResultSet resultSet=null;

		try {
			statement = connection.prepareStatement(ProductQueries.QUERYBYCAT);
			statement.setString(1, category);
			resultSet = statement.executeQuery();
			producetList = productmapRowper.mapRow(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) 
					resultSet.close();
				if(resultSet != null)
					statement.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return producetList;
	}

	/**
	 * @param brand
	 * @return A List of Product objects 
	 * 
	 * Action: This will retrieve all the products from the products table based on the Brand provided by the user
	 */
	@Override
	public List<Product> findByBrand(String brand) {
		PreparedStatement statement = null;
		Connection connection = DbConnection.openConnection();
		List<Product> productList = new ArrayList<>();
		ResultSet resultSet=null;

		try {
			statement = connection.prepareStatement(ProductQueries.QUERYBYBRAND);
			statement.setString(1, brand);
			resultSet = statement.executeQuery();
			productList = productmapRowper.mapRow(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) 
					resultSet.close();
				if(resultSet != null)
					statement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return productList;
	}

	/**
	 * @param productName
	 * @return A List of Product objects 
	 * 
	 * Action: This will retrieve all the products from the products table based on part of product name given by the user 
	 */
	@Override
	public List<Product> findByNameContaining(String productName) {
		PreparedStatement statement = null;
		Connection connection = DbConnection.openConnection();
		List<Product> productList = new ArrayList<>();
		ResultSet resultSet=null;

		try {
			statement = connection.prepareStatement(ProductQueries.QUERYBYNAME);
			statement.setString(1, "%" + productName + "%");
			resultSet = statement.executeQuery();
			productList = productmapRowper.mapRow(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) 
					resultSet.close();
				if(resultSet != null)
					statement.close();
				
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return productList;
	}

	/**
	 * @param productName
	 * @param quantity
	 * @return A List of Product objects
	 * 
	 * Action: This will retrieve all the products from the products table based on productName and quantity provided by the user
	 */
	@Override
	public List<Product> findByNameAndQuantity(String productName, int quantity) {
		PreparedStatement statement = null;
		Connection connection = DbConnection.openConnection();
		List<Product> productList = new ArrayList<>();
		ResultSet resultSet=null;

		try {
			statement = connection.prepareStatement(ProductQueries.QUERYBYNAMEANDQUANT);
			statement.setString(1, "%" + productName + "%");
			statement.setInt(2, quantity);
			resultSet = statement.executeQuery();
			productList = productmapRowper.mapRow(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) 
					resultSet.close();
				if(resultSet != null)
					statement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return productList;
	}

}
