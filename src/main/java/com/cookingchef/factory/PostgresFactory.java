package com.cookingchef.factory;

import java.util.concurrent.atomic.AtomicReference;

import com.cookingchef.dao.PostgresUserDAO;
import com.cookingchef.dao.UserDAO;

public class PostgresFactory implements AbstractFactory {

  private static AtomicReference<PostgresFactory> instance = new AtomicReference<>();

  private PostgresFactory() {
  }

  public static PostgresFactory getPostgresFactory() {
    instance.compareAndSet(null, new PostgresFactory());
    return instance.get();
  }
  
  @Override
  public UserDAO getUserDAO() {

    return PostgresUserDAO.getPostgresUserDAO();
  }
}
