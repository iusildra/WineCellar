package com.cookingchef.factory;

import com.cookingchef.dao.PostgresUserDAO;
import com.cookingchef.dao.UserDAO;

public class PostgresFactory implements AbstractFactory {

  private static volatile PostgresFactory instance;

  private PostgresFactory() {
  }

  public static synchronized PostgresFactory getPostgresFactory() {
    if (instance == null) {
      instance = new PostgresFactory();
    }
    return instance;
  }
  
  @Override
  public UserDAO getUserDAO() {

    return PostgresUserDAO.getPostgresUserDAO();
  }
}
