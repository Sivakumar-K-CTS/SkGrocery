package com.skgroceries.model;

public class Cart {
	
	private int productId;
	private String productName;
	private int quantity;
	private double perPrice;
	private double price;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(int productId, String productName, int quantity, double perPrice, double price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.perPrice = perPrice;
		this.price = price;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPerPrice() {
		return perPrice;
	}
	public void setPerPrice(double perPrice) {
		this.perPrice = perPrice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Cart [productId=" + productId + ", productName=" + productName + ", quantity=" + quantity
				+ ", perPrice=" + perPrice + ", price=" + price + "]";
	}
	
	
}
