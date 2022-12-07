package com.cookingchef.Factory;

import com.cookingchef.DAO.Abstract.UserDAO;

public abstract class AbstractFactory {

	public abstract UserDAO getUserDAO();
	abstract AbstractFactory createInstance();
}
