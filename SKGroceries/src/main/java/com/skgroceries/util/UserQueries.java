package com.skgroceries.util;

public class UserQueries {
	public static final String QUERYFORLOGIN = "select * from users where Username=? and Password = ?";
	public static final String QUERYFORROLE = "select LoginType from users where Username = ?";
	public static final String QUERYFORADDUSER = "insert into users (Name,Username,Password,Location,MobileNumber,LoginType) values(?,?,?,?,?,?)";
	public static final String QUERYFORPRODUCT = "select * from products where productId=? and count >= ?";
	public static final String QUERYFORCOUNT = "Select count from products where productId = ?";
	public static final String QUERYFORUPDATE = "Update products set count = (?-?) where productId=?";
}
