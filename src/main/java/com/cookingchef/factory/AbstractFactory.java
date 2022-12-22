package com.cookingchef.factory;

import com.cookingchef.dao.UserDAO;

public interface AbstractFactory {
  public UserDAO getUserDAO();
}