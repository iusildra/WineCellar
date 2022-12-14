package com.cookingchef.facade;

import com.cookingchef.dao.PostgresUserDAO;
import com.cookingchef.dao.UserDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.User;

public class UserFacade {
	private static volatile UserFacade instance;

	private UserFacade() {
	}

	public static synchronized UserFacade getUserFacade() {
		if (instance == null) {
			instance = new UserFacade();
		}
		return instance;
	}

	public User login() {
		PostgresFactory factory = PostgresFactory.getPostgresFactory();
		PostgresUserDAO userDAO = factory.getUserDAO();
		return userDAO.getUserbyIdPwd();
	}
}
