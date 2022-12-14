package com.cookingchef.dao;

import com.cookingchef.model.User;

public class PostgresUserDAO extends UserDAO {

	private static volatile PostgresUserDAO instance;

	private PostgresUserDAO() {}
	public static PostgresUserDAO getPostgresUserDAO() {
		if (instance == null) {
			instance = new PostgresUserDAO();
		}
		return instance;
	}

	@Override
	public User  login() {
//		 TODO implement this method
		return null;
	}
}
