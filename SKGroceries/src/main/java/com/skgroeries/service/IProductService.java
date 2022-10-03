package com.skgroeries.service;

import java.util.List;

import com.skgroceries.exceptions.ProductNotFoundException;
import com.skgroceries.model.Product;

public interface IProductService {
	
	boolean addProduct(Product product);

	boolean deleteProduct(int id);

	boolean updateProduct(int id, double price);

	Product getById(int id) throws ProductNotFoundException;

	List<Product> getAllProducts() throws ProductNotFoundException;

	List<Product> getByCategory(String category) throws ProductNotFoundException;

	List<Product> getByBrand(String brand) throws ProductNotFoundException;

	List<Product> getByNameContaining(String productName) throws ProductNotFoundException;

	List<Product> getByNameAndQuantity(String productName, int quantity) throws ProductNotFoundException;

	
}
