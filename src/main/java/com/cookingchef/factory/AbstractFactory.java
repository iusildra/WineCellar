package com.cookingchef.factory;

import com.cookingchef.dao.*;

public interface AbstractFactory {

  /**
   *
   * @return L'user DAO
   */
  UserDAO getUserDAO();

  /**
   *
   * @return L'admin suggestion DAO
   */
  AdminSuggestionDAO getAdminSuggestionDAO();

  /**
   *
   * @return L'user suggestion DAO
   */
  UserSuggestionDAO getUserSuggestionDAO();

  /**
   *
   * @return Le partner DAO
   */
  PartnerDAO getPartnerDAO();

  /**
   *
   * @return La recipe list DAO
   */
  RecipeListDAO getRecipeListDAO();

  /**
   *
   * @return La recipe DAO
   */
  RecipeDAO getRecipeDAO();

  /**
   *
   * @return L'ingredient DAO
   */
  IngredientDAO getIngredientDAO();

  /**
   *
   * @return La category DAO
   */
  CategoryDAO getCategoryDAO();

  /**
   *
   * @return Le cart item DAO
   */
  CartDAO getCartDAO();

  /**
   *
   * @return La publicité DAO
   */
  AdDAO getAdDAO();

  CalendarDAO getCalendarDAO();

  MealCategoryDAO getMealCategoryDAO();
}