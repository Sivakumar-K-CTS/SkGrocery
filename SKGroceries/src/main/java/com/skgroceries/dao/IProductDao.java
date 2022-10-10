package com.skgroceries.dao;

import java.util.List;

import com.skgroceries.model.Product;

/**
 * @author SivakumarK
 * 
 * Action: This is an interface contains all the abstract  methods related to the grocery products, which will connect to the DB to perform the CRUD operations.
 * Methods for Admin role:
 * 		addProduct(Product product) - Adds new product to the products table.
 * 		deleteProduct(int id) - Delete a product requested by the admin in the Products table.
 *  	updateProduct(int id, double price) - Update the price of a product in the Products table.
 *  	findById(int id) - This will retrieve a Product by ID from the Products table.
 *  Methods for User role:
 *  	findAllProducts() - Will retrieve all the products from the Products table.
 *  	findByCategory(String category) - Will retrieve all the products from the Products table base on the category provided by the user.
 *  	findByNameContaining(String productName) - Will retrieve all the products from the Products table base on a part of a name provided by the user.
 *  
 *
 */
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
