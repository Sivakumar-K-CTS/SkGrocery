package com.skgroceries.util;

import java.util.*;

import com.skgroceries.model.Category;
import com.skgroceries.model.Product;

public class AddProductUtil {

	public Product productAdder() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter product name:");
		String name = sc.nextLine();
		System.out.println("Enter brand:");
		String brand = sc.nextLine();
		System.out.println("Choose Category(0=Grains,1=Masala,2=Fruits,3=Vegetables,4=Oil):");
		int option = sc.nextInt();
		Category[] categoryArr = Category.values();
		String category = categoryArr[option].name();
		System.out.println("Enter the Quantity (as per standard Kg's / Litters):");
		int quantityByKgs = sc.nextInt();
		System.out.println("Enter Price:");
		double price = sc.nextDouble();
		System.out.println("Enter the count of that product:");
		int count = sc.nextInt();

		Product newProduct = new Product(name, brand, category, quantityByKgs, price, count);
		sc.close();
		return newProduct;
	}

}
