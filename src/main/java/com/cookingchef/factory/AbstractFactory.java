package com.cookingchef.factory;

import com.cookingchef.dao.*;

public interface AbstractFactory {

  UserDAO getUserDAO();

  AdminSuggestionDAO getAdminSuggestionDAO();

  UserSuggestionDAO getUserSuggestionDAO();

  PartnerDAO getPartnerDAO();

  RecipeListDAO getRecipeListDAO();

  RecipeDAO getRecipeDAO();

  IngredientDAO getIngredientDAO();

  CategoryDAO getCategoryDAO();

  CartDAO getCartDAO();

  AdDAO getAdDAO();
}