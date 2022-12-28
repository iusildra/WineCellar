package com.cookingchef.factory;

import com.cookingchef.dao.UserDAO;

public interface AbstractFactory {

  /**
   *
   * @return
   */
  public static AbstractFactory getInstance() {
    return PostgresFactory.getPostgresFactory();
  }
  public UserDAO getUserDAO();
}