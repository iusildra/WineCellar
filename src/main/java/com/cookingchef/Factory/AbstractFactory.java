package com.cookingchef.Factory;

import com.cookingchef.DAO.Abstract.UserDAO;

import java.lang.reflect.Type;

public abstract class AbstractFactory {
	private static volatile AbstractFactory instance;

	public abstract UserDAO getUserDAO();

	public static AbstractFactory getInstance(Type type) {
		if (instance == null) {
			if(type == PostgresFactory.class) {
				instance = (AbstractFactory) new PostgresFactory();
			}
		}
		return instance;
	}
}
