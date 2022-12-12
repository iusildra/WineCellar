package com.cookingchef.factory;

import java.lang.reflect.Type;

import com.cookingchef.dao.UserDAO;

public abstract class AbstractFactory {
	private static volatile AbstractFactory instance;

	public abstract UserDAO getUserDAO();

	public static AbstractFactory getInstance(Type type) {
		if (instance == null && type == PostgresFactory.class) {
				instance = (AbstractFactory) new PostgresFactory();
		}
		return instance;
	}
}
