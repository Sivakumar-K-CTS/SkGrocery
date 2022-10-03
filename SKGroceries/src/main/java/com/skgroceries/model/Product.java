package com.skgroceries.model;

public class Product {
	private Integer productId;
	private String productName;
	private String brand;
	private String category;
	private int quantityInKgs;
	private double price;
	private int count;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Integer productId, String productName, String brand, String category, int quantityInKgs,
			double price, int count) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.brand = brand;
		this.category = category;
		this.quantityInKgs = quantityInKgs;
		this.price = price;
		this.count = count;
	}

	public Product(String productName, String brand, String category, int quantityInKgs, double price, int count) {
		super();
		this.productName = productName;
		this.brand = brand;
		this.category = category;
		this.quantityInKgs = quantityInKgs;
		this.price = price;
		this.count = count;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQuantityInKgs() {
		return quantityInKgs;
	}

	public void setQuantityInKgs(int quantityInKgs) {
		this.quantityInKgs = quantityInKgs;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", brand=" + brand + ", category="
				+ category + ", quantityInKgs=" + quantityInKgs + ", price=" + price + ", count=" + count + "]";
	}

}
