package com.cookingchef.factory;

import com.cookingchef.dao.*;
import com.cookingchef.dao.Postgres.*;

public class PostgresFactory implements AbstractFactory {

  /**
   *
   * @return L'user DAO
   */
  @Override
  public UserDAO getUserDAO() {
    return PostgresUserDAO.getPostgresUserDAO();
  }

  /**
   *
   * @return L'admin suggestion DAO
   */
  @Override
  public AdminSuggestionDAO getAdminSuggestionDAO() {
    return PostgresAdminSuggestionDAO.getPostgresAdminSuggestionDAO();
  }

  /**
   *
   * @return L'user suggestion DAO
   */
  @Override
  public UserSuggestionDAO getUserSuggestionDAO() {
    return PostgresUserSuggestionDAO.getPostgresUserSuggestionDAO();
  }

  /**
   *
   * @return Le partner DAO
   */
  @Override
  public PartnerDAO getPartnerDAO() {
    return PostgresPartnerDAO.getPostgresPartnerDAO();
  }

  /**
   *
   * @return La recipe list DAO
   */
  @Override
  public RecipeListDAO getRecipeListDAO() {
    return PostgresRecipeListDAO.getPostgresRecipeListDAO();
  }

  /**
   *
   * @return La recipe DAO
   */
  @Override
  public RecipeDAO getRecipeDAO() {
    return PostgresRecipeDAO.getPostgresRecipeDAO();
  }

  /**
   *
   * @return L'ingredient DAO
   */
  @Override
  public IngredientDAO getIngredientDAO() {
    return PostgresIngredientDAO.getPostgresIngredientDAO();
  }

  /**
   *
   * @return La category DAO
   */
  @Override
  public CategoryDAO getCategoryDAO() {
    return PostgresCategoryDAO.getPostgresCategoryDAO();
  }

  /**
   *
   * @return Le cart item DAO
   */
  @Override
  public CartDAO getCartDAO() {
    return PostgresCartDAO.getPostgresCartDAO();
  }

  /**
   *
   * @return La publicité DAO
   */
  @Override
  public AdDAO getAdDAO() {
    return PostgresAdDAO.getPostgresAdDAO();
  }

  @Override
  public CalendarDAO getCalendarDAO() {
        return PostgresCalendarDAO.getPostgresCalendarDAO();
    }

  @Override
  public MealCategoryDAO getMealCategoryDAO() {
        return PostgresMealCategoryDAO.getPostgresMealCategoryDAO();
    }

  @Override
  public UnitDAO getUnitDAO() {
    return PostgresUnitDAO.getPostgresUnitDAO();
  }
}
