/**
 * 
 */
package com.skgroceries.util;
import java.util.*;
import com.skgroceries.model.User;

/**
 * @author SivakumarK
 *
 */
public class RegistrationUtil {

	public User userRegistration(){
		Scanner sc = new Scanner(System.in);
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
		sc.close();
		return user;
	}

}
