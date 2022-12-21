package com.cookingchef.facade;

import com.cookingchef.dao.UserDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.User;

import java.sql.SQLException;

public class UserFacade {
	private static volatile UserFacade instance;
	private UserDAO userDAO;

	private UserFacade() {
		PostgresFactory factory = PostgresFactory.getPostgresFactory();
		this.userDAO = factory.getUserDAO();
	}

	public static synchronized UserFacade getUserFacade() {
		if (instance == null) {
			instance = new UserFacade();

		}
		return instance;
	}

	public User login(String email,String password) throws SQLException {


		return this.userDAO.getUserByEmailPwd(email,password);
	}
}
