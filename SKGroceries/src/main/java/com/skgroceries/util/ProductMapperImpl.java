package com.skgroceries.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skgroceries.model.Product;

public class ProductMapperImpl implements IProductMapper {

	@Override
	public List<Product> mapRow(ResultSet resultSet) throws SQLException {
		List<Product> productList = new ArrayList<>();
		while(resultSet.next()) {
			Product product = new Product();
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			String brand = resultSet.getString(3);
			String category = resultSet.getString(4);
			int quantity = resultSet.getInt(5);
			double price = resultSet.getDouble(6);
			int count = resultSet.getInt(7);
			product.setBrand(brand);
			product.setCategory(category);
			product.setCount(count);
			product.setPrice(price);
			product.setProductId(id);
			product.setProductName(name);
			product.setQuantityInKgs(quantity);

			productList.add(product);
	
		}
		return productList;
	}

}
