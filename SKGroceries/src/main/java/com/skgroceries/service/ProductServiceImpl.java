package com.skgroceries.service;

import java.util.List;
import java.util.stream.Collectors;

import com.skgroceries.dao.IProductDao;
import com.skgroceries.dao.ProductDaoImpl;
import com.skgroceries.exceptions.ProductNotFoundException;
import com.skgroceries.model.Product;

public class ProductServiceImpl implements IProductService {

	IProductDao productDao = new ProductDaoImpl();

	/**
	 * @param product
	 * @return A boolean value from DAO
	 * 
	 * Action: Will provide the response of the DAO layer(method - addProduct()) to the main method 
	 */
	@Override
	public boolean addProduct(Product product) {
		return productDao.addProduct(product);
	}

	/**
	 * @param id
	 * @return  A boolean value from DAO
	 * 
	 * Action: Will provide the response of the DAO layer(method - deleteProduct()) to the main method
	 */
	@Override
	public boolean deleteProduct(int id) {
		return productDao.deleteProduct(id);
	}

	/**
	 * @param id
	 * @param price
	 * @return  A boolean value from DAO
	 * 
	 * Action: Will provide the response of the DAO layer(method - updateProduct()) to the main method
	 */
	@Override
	public boolean updateProduct(int id, double price) {
		return productDao.updateProduct(id, price);
	}

	/**
	 * @param id
	 * @return  A boolean value from DAO
	 * @throws ProductNotFoundException
	 * 
	 * Action: Will provide the response of the DAO layer(method - findById()) to the main method. If the response is null, will throw the exception
	 * 
	 */
	@Override
	public Product getById(int id) throws ProductNotFoundException {
		Product product = productDao.findById(id);
		if (product.getProductId()==null) {
			throw new ProductNotFoundException("Product Not Found!!!");
		}
		return product;
		
	}

	/**
	 * @return A List of Product objects
	 * @throws ProductNotFoundException
	 * 
	 * Action: Will provide the response of the DAO layer(method - getAllProducts()) to the main method.  If the response is null, will throw the exception.
	 */
	@Override
	public List<Product> getAllProducts() throws ProductNotFoundException {
		List<Product> products = productDao.findAllProducts();
		if (products.isEmpty()) {
			throw new ProductNotFoundException("Product Not Found!!!");
		}
		return products.stream().sorted((Product p1, Product p2) -> {
			return p1.getProductName().compareTo(p2.getProductName());
		}).collect(Collectors.toList());
		
	}

	/**
	 * @param category
	 * @return
	 * @throws ProductNotFoundException
	 * 
	 * Action:  Will provide the response of the DAO layer(method - findByCategory()) to the main method. If the response is null,  will throw the exception.
	 */
	@Override
	public List<Product> getByCategory(String category) throws ProductNotFoundException {

		List<Product> products = productDao.findByCategory(category);

		products = products.stream().sorted((Product p1, Product p2) -> {
			return p1.getProductName().compareTo(p2.getProductName());
		}).collect(Collectors.toList());
		if (products.isEmpty()) {
			throw new ProductNotFoundException("Product Not Found!!!");
		} else {
			return products;
		}
	}

	/**
	 * @param brand
	 * @return A List of Product objects
	 * @throws ProductNotFoundException
	 * 
	 * Action: Will provide the response of the DAO layer(method - findByBrand()) to the main method. If the response is null, will throw the exception
	 */
	@Override
	public List<Product> getByBrand(String brand) throws ProductNotFoundException {
		List<Product> products = productDao.findByBrand(brand);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("Product Not Found!!!");
		} else {
			products = products.stream().sorted((Product p1, Product p2) -> {
				return p1.getProductName().compareTo(p2.getProductName());
			}).collect(Collectors.toList());
			return products;
		}
	}

	/**
	 * @param productName
	 * @return A List of Product objects
	 * @throws ProductNotFoundException
	 * 
	 * Action: Will provide the response of the DAO layer(method - findByNameContaining()) to the main method. If the response is null, will throw the exception
	 */
	@Override
	public List<Product> getByNameContaining(String productName) throws ProductNotFoundException {
		List<Product> products = productDao.findByNameContaining(productName);

		products = products.stream().sorted((Product p1, Product p2) -> {
			return p1.getProductName().compareTo(p2.getProductName());
		}).collect(Collectors.toList());
		if (products.isEmpty()) {
			throw new ProductNotFoundException("Product Not Found!!!");
		} else {
			return products;
		}
	}

	/**
	 * @param productName
	 * @param quantity
	 * @return A List of Product objects
	 * @throws ProductNotFoundException
	 * 
	 * Action: Will provide the response of the DAO layer(method - findByNameAndQuantity()) to the main method. If the response is null, will throw the exception
	 */
	@Override
	public List<Product> getByNameAndQuantity(String productName, int quantity) throws ProductNotFoundException {
		List<Product> products = productDao.findByNameAndQuantity(productName, quantity);

		products = products.stream().sorted((Product p1, Product p2) -> {
			return p1.getProductName().compareTo(p2.getProductName());
		}).collect(Collectors.toList());
		if (products.isEmpty()) {
			throw new ProductNotFoundException("Product Not Found!!!");
		} else {
			return products;
		}
	}

}
