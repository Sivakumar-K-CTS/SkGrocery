package com.skgroceries.model;

public enum Category{
	GRAINS("Grain Poducts"), MASALA("Masala Products"), FRUITS("Fruits Products"), VEGETABLES("Vegetables Products"),
	OIL("Oil Products");

	public String ctype;

	private Category(String ctype) {
		this.ctype = ctype;
	}

}
