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
	PreparedStatement statement = null;
	IProductMapper productmapRowper = new ProductMapperImpl();

	@Override
	public boolean addProduct(Product product) {
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

	@Override
	public boolean deleteProduct(int id) {
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

	@Override
	public boolean updateProduct(int id, double price) {
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

	@Override
	public Product findById(int id) {
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

	@Override
	public List<Product> findAllProducts() {
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

	@Override
	public List<Product> findByCategory(String category) {
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

	@Override
	public List<Product> findByBrand(String brand) {
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

	@Override
	public List<Product> findByNameContaining(String productName) {
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

	@Override
	public List<Product> findByNameAndQuantity(String productName, int quantity) {
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
