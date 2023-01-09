package com.cookingchef.factory;

import com.cookingchef.dao.*;

public interface AbstractFactory {

  public UserDAO getUserDAO();

  public AdminSuggestionDAO getAdminSuggestionDAO();

  public UserSuggestionDAO getUserSuggestionDAO();

  public PartnerDAO getPartnerDAO();

  public RecipeListDAO getRecipeListDAO();
}