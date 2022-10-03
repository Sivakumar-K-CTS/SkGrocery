package com.skgroceries.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skgroceries.exceptions.ProductNotFoundException;
import com.skgroceries.exceptions.UserNotFoundException;
import com.skgroceries.model.Cart;
import com.skgroceries.model.Category;
import com.skgroceries.model.Product;
import com.skgroceries.model.User;
import com.skgroeries.service.IProductService;
import com.skgroeries.service.ProductServiceImpl;
import com.skgroeries.service.IUserService;
import com.skgroeries.service.UserServiceImpl;

public class Client {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		IUserService userService = new UserServiceImpl();
		System.out.println("Welcome to SK-Groceries!!!");
		System.out.println("Press 1 for New User (OR) Press 2 for Existing User");
		int userType = sc.nextInt();
		sc.nextLine();
		if (userType == 1) {
			System.out.println("Enter Name:");
			String name = sc.nextLine();
			System.out.println("Enter Username for login:");
			String username = sc.nextLine();
			System.out.println("Enter Password:");
			String password = sc.nextLine();
			System.out.println("Enter Location:");
			String location = sc.nextLine();
			System.out.println("Enter Mobile Number:");
			long mobileNumber = sc.nextLong();

			User user = new User(name, username, password, location, mobileNumber);

			boolean result = userService.register(user);

			if (result) {
				System.out.println("User Added successfully!!!");
			} else {
				System.out.println("Please Try again**");
			}
		} else if (userType == 2) {
			System.out.println("Enter User Name:");
			String userName = sc.nextLine();
			System.out.println("Enter Password:");
			String password = sc.nextLine();
			int loginWithRole = 2;
			try {
				loginWithRole = userService.loginValidation(userName, password);

			} catch (UserNotFoundException e1) {
				System.out.println(e1.getMessage());
			}

			if (loginWithRole == 1) {
				System.out.println(
						"Choose the acton number to be performed:\n1.Add a product\n2.Delete a product\n3.Update price of a product\n4.Get a product by Id");
				int action = sc.nextInt();
				sc.nextLine();
				IProductService service = new ProductServiceImpl();

				switch (action) {

				case 1:
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

					Product product = new Product(name, brand, category, quantityByKgs, price, count);
					boolean addResult = service.addProduct(product);
					if (addResult) {
						System.out.println("Product Added Successfully!!!");
					} else {
						System.out.println("Product is not added***");
					}

					break;
				case 2:
					System.out.println("Enter product ID to delete:");
					int id = sc.nextInt();
					boolean deleteResult = service.deleteProduct(id);
					if (deleteResult) {
						System.out.println("Product Deleted Successfully!!!");
					} else {
						System.out.println("Product is not Deleted***");
					}
					break;
				case 3:
					System.out.println("Enter the Product ID to modify:");
					int prodId = sc.nextInt();
					System.out.println("Enter the new price:");
					double newPrice = sc.nextDouble();
					boolean updateResult = service.updateProduct(prodId, newPrice);
					if (updateResult) {
						System.out.println("Product Records Updated Successfully!!!");
					} else {
						System.out.println("Product is not updated***");
					}
					break;
				case 4:
					System.out.println("Enter the Product ID:");
					int productId = sc.nextInt();
					try {
						System.out.println(service.getById(productId));
					} catch (ProductNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
				default:
					System.out.println("Invalid input!!!");
					break;
				}

			} else if (loginWithRole == 0) {

				ArrayList<Cart> productsInCart = new ArrayList<>();
				int loop = 1;
				while (loop != 0) {
					System.out.println(
							"Choose the acton number to be performed:\n1.Get All products\n2.Get products by category\n3.Get products by brand\n4.Get a product by Name\n5.Get a product by Name and Quantity\n6.Purchase");
					int action = sc.nextInt();
					sc.nextLine();
					IProductService service = new ProductServiceImpl();
					switch (action) {
					case 1:
						System.out.println("The List of Products we have:");
						List<Product> productList = new ArrayList<>();
						try {
							productList = service.getAllProducts();
						} catch (ProductNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for (Product product : productList) {
							System.out.println(product);
						}

						break;
					case 2:
						System.out.println("Choose Category number(0=Grains,1=Masala,2=Fruits,3=Vegetables,4=Oil):");
						int option = sc.nextInt();
						Category[] categoryArr = Category.values();
						String category = categoryArr[option].name();

						List<Product> productsByCategory = new ArrayList<>();
						try {
							productsByCategory = service.getByCategory(category);
						} catch (ProductNotFoundException e) {
							e.printStackTrace();
						}
						for (Product product : productsByCategory) {
							System.out.println(product);
						}

						break;
					case 3:
						System.out.println("Enter the brand Name:");
						String productBrand = sc.nextLine();
						List<Product> productsByBrand = new ArrayList<>();
						try {
							productsByBrand = service.getByBrand(productBrand);
						} catch (ProductNotFoundException e) {
							System.out.println(e.getMessage());
						}
						for (Product product : productsByBrand) {
							System.out.println(product);
						}
						break;
					case 4:
						System.out.println("Enter full(or)a part of the Product Name:");
						String name = sc.nextLine();
						List<Product> productsByName = new ArrayList<>();
						try {
							productsByName = service.getByNameContaining(name);
						} catch (ProductNotFoundException e) {
							System.out.println(e.getMessage());
						}
						for (Product product : productsByName) {
							System.out.println(product);
						}
						break;
					case 5:
						System.out.println("Enter full(or)a part of the Product Name:");
						String prodName = sc.nextLine();
						System.out.println("Enter Quantity in Kg:");
						int quantity = sc.nextInt();
						List<Product> productsByNameAndQuant = new ArrayList<>();
						try {
							productsByNameAndQuant = service.getByNameAndQuantity(prodName, quantity);
						} catch (ProductNotFoundException e) {
							System.out.println(e.getMessage());
						}
						for (Product product : productsByNameAndQuant) {
							System.out.println(product);
						}
						break;
					case 6:
						int buyLoop = 1;

						while (buyLoop != 0) {
							System.out.println("Enter Product Id:");
							int productID = sc.nextInt();
							System.out.println("Enter Quantity:");
							int quant = sc.nextInt();

							Product product = userService.productAvailablity(productID, quant);
							if (product.getProductId() != null) {
								Cart cart = new Cart();
								double finalPrice = quant * product.getPrice();
								cart.setPrice(finalPrice);
								cart.setProductId(product.getProductId());
								cart.setPerPrice(product.getPrice());
								cart.setProductName(product.getProductName());
								cart.setQuantity(quant);
								productsInCart.add(cart);
								System.out.println(cart + "\nYour product is added to the cart successfully!!!");

							} else {
								System.out.println(
										"Incorrect product ID or Quantity is more than the reserve. Try again!!");
							}
							// System.out.println(cart);

							System.out.println("Enter 0 to terminate or 1 to continue");
							int loopBreaker = sc.nextInt();
							if (loopBreaker == 0) {
								buyLoop = loopBreaker;
							}
						}
					default:
						System.out.println("Invalid input!!!");
						break;
					}
					System.out.println("Press 0 to terminate to Billing or 1 to main menu");
					int userResponce = sc.nextInt();
					if (userResponce == 0) {
						loop = 0;
					}

				}
				if (productsInCart.isEmpty()) {
					System.out.println("Thankyou!! Have a nice Day!!");
				} else {
					System.out.println("!!---Your Bill---!!");
					double totalAmount = 0;
					for (Cart product : productsInCart) {
						System.out.println(product);
						totalAmount += product.getPrice();
					}
					userService.updatePurchase(productsInCart);
					System.out.println(
							"The total amount you need to pay is " + totalAmount + "Rs.\nThankyou!! Have a nice Day!!");
				}
			} else {
				System.out.println("!!!Invalid Input!!!");
			}
			sc.close();

		} else {
			System.out.println("Invalid User!!!");
		}
	}

}
