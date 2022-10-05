package com.skgroceries.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.skgroceries.model.User;
import com.skgroceries.util.DbConnection;
import com.skgroceries.util.UserQueries;

public class UserDaoImpl implements IUserDao {


	
	@Override
	public boolean addUser(User user) {
		PreparedStatement statement = null;
		Connection connection = DbConnection.openConnection();
		int result =0;
		try {
			statement = connection.prepareStatement(UserQueries.QUERYFORADDUSER);
			statement.setString(1, user.getName());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getLocation());
			statement.setLong(5, user.getMobileNumber());
			statement.setInt(6, 0);
			
			result = statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (statement != null) {
					statement.close();
				}
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		if(result == 1) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public int loginValidation(String userName, String password) {
		PreparedStatement statement = null;
		int result = 0;
		ResultSet resultSet=null;
		Connection connection = DbConnection.openConnection();
		try {
			
			statement = connection.prepareStatement(UserQueries.QUERYFORLOGIN);
			statement.setString(1, userName);
			statement.setString(2, password);
			
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getInt(7);
			}else {
				result = 2  ;    //If user not found in the DB
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (statement != null)
					statement.close();
				if(resultSet != null)
					statement.close();			

			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return result;
	}

}

	


