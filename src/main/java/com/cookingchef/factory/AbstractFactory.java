package com.cookingchef.factory;

import com.cookingchef.dao.AdminSuggestionDAO;
import com.cookingchef.dao.UserDAO;
import com.cookingchef.dao.UserSuggestionDAO;

public interface AbstractFactory {
  
  public UserDAO getUserDAO();
  
  public AdminSuggestionDAO getAdminSuggestionDAO();

  public UserSuggestionDAO getUserSuggestionDAO();
}