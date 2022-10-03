package com.skgroceries.dao;

import java.util.List;

import com.skgroceries.model.Product;

public interface IProductDao {

	boolean addProduct(Product product);

	boolean deleteProduct(int id);

	boolean updateProduct(int id, double price);

	Product findById(int id);

	List<Product> findAllProducts();

	List<Product> findByCategory(String category);

	List<Product> findByBrand(String brand);

	List<Product> findByNameContaining(String productName);

	List<Product> findByNameAndQuantity(String productName, int quantity);

}
