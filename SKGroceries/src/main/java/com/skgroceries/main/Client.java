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
import com.skgroceries.service.IProductService;
import com.skgroceries.service.IPurchaseService;
import com.skgroceries.service.IUserService;
import com.skgroceries.service.ProductServiceImpl;
import com.skgroceries.service.PurchaseServiceImpl;
import com.skgroceries.service.UserServiceImpl;
import com.skgroceries.util.AddProductUtil;
import com.skgroceries.util.RegistrationUtil;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		IUserService userService = new UserServiceImpl();
		IPurchaseService purchaseService = new PurchaseServiceImpl();
		System.out.println("Welcome to SK-Groceries!!!");
		System.out.println("Press 1 for New User (OR) Press 2 for Existing User (OR)Press 3 if you Forget Password");
		int userType = sc.nextInt();
		sc.nextLine();
		if (userType == 1) { 
			RegistrationUtil register = new RegistrationUtil();
			User newUser = register.userRegistration();
			boolean result = userService.register(newUser);
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
					AddProductUtil addProduct = new AddProductUtil();
					Product newProduct = addProduct.productAdder();
					boolean addResult = service.addProduct(newProduct);
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

							Product product = purchaseService.productAvailablity(productID, quant);
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
							
							System.out.println("Enter 0 to terminate or 1 to continue");
							int loopBreaker = sc.nextInt();
							if (loopBreaker == 0) {
								buyLoop = loopBreaker;
							}
						}
						break;
					default:
						System.out.println("Invalid input!!!");
						break;
					}
					System.out.println("Press 0 to terminate to Checkout or 1 to main menu");
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
					purchaseService.updatePurchase(productsInCart);
					System.out.println(
							"The total amount you need to pay is " + totalAmount + "Rs.\nThankyou!! Have a nice Day!!");
				}
			} else {
				System.out.println("!!!Invalid Input!!!");
			}
			sc.close();

		}else if(userType == 3) {
			System.out.println("Enter the Mobile Number:");
			long phone = sc.nextLong();
			sc.nextLine();
			System.out.println("Enter the New Password:");
			String password = sc.nextLine();
			try {
				boolean result = userService.changePassword(phone, password);
				if(result) {
					System.out.println("Password has been Successfully!!!");
				}
			} catch (UserNotFoundException e) {
				System.out.println(e.getMessage());
			}
			
			
		}else {
			System.out.println("Invalid User!!!");
		}
	}

}
