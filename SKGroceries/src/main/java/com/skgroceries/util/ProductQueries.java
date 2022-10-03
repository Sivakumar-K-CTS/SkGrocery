package com.skgroceries.util;

public class ProductQueries {

	public static final String ADDQUERY = "insert into products(productName,brand, category, quantityInKgs, price, count) values (?,?,?,?,?,?)";
	public static final String DELETEQUERY = "delete from products where productId=?";
	public static final String UPDATEQUERY = "Update products set price=? where productId=?";
	public static final String QUERYBYID = "Select * from products where productId=?";
	public static final String QUERYGETALL = "Select * from products";
	public static final String QUERYBYCAT = "Select * from products where category=?";
	public static final String QUERYBYBRAND = "Select * from products where brand=?";
	public static final String QUERYBYNAME = "Select * from products where productName like ?";
	public static final String QUERYBYNAMEANDQUANT = "Select * from products where productName like ? and quantityInKgs=?";

}
