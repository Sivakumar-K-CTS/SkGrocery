package com.skgroceries.service;

import java.util.List;
import java.util.stream.Collectors;

import com.skgroceries.dao.IProductDao;
import com.skgroceries.dao.ProductDaoImpl;
import com.skgroceries.exceptions.ProductNotFoundException;
import com.skgroceries.model.Product;

public class ProductServiceImpl implements IProductService {

	IProductDao productDao = new ProductDaoImpl();

	@Override
	public boolean addProduct(Product product) {
		return productDao.addProduct(product);
	}

	@Override
	public boolean deleteProduct(int id) {
		return productDao.deleteProduct(id);
	}

	@Override
	public boolean updateProduct(int id, double price) {
		return productDao.updateProduct(id, price);
	}

	@Override
	public Product getById(int id) throws ProductNotFoundException {
		Product product = productDao.findById(id);
		if (product == null) {
			throw new ProductNotFoundException("Product Not Found!!!");
		}
		return product;
		
	}

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
