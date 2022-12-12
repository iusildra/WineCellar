package com.cookingchef.factory;

import com.cookingchef.dao.PostgresUserDAO;
import com.cookingchef.dao.UserDAO;

public class PostgresFactory extends AbstractFactory {

	@Override
	public UserDAO getUserDAO() {
		return new PostgresUserDAO();
	}

	public AbstractFactory createInstance() {
		return new PostgresFactory();
	}

}