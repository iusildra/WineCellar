package com.cookingchef.dao;

import com.cookingchef.model.User;

import java.sql.SQLException;

public abstract class UserDAO {

	//	TODO implement this method
	public abstract User getUserByEmailPwd(String email, String password) throws SQLException;
}
