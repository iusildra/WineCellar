package com.cookingchef.factory;

import com.cookingchef.dao.PostgresUserDAO;
import com.cookingchef.dao.UserDAO;

public class PostgresFactory implements AbstractFactory {

  @Override
  public UserDAO getUserDAO() {

    return PostgresUserDAO.getPostgresUserDAO();
  }
}
