package com.cookingchef.Factory;

import com.cookingchef.DAO.Abstract.UserDAO;
import com.cookingchef.DAO.Postgres.PostgresUserDAO;

public class PostgresFactory extends AbstractFactory {

	@Override
	public UserDAO getUserDAO() {
		return new PostgresUserDAO();
	}

	@Override
	AbstractFactory createInstance() {
		return new PostgresFactory();
	}

}