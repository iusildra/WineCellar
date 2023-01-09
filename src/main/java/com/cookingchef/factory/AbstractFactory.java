package com.cookingchef.factory;

import com.cookingchef.dao.*;

public interface AbstractFactory {

  UserDAO getUserDAO();

  AdminSuggestionDAO getAdminSuggestionDAO();

  UserSuggestionDAO getUserSuggestionDAO();

  PartnerDAO getPartnerDAO();

  IngredientDAO getIngredientDAO();

  CategoryDAO getCategoryDAO();
}