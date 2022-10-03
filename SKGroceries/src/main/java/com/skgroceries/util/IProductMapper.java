package com.skgroceries.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skgroceries.model.Product;

public interface IProductMapper {
	public List<Product> mapRow(ResultSet resultSet) throws SQLException; 

}
