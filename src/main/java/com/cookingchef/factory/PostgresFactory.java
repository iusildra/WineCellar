package com.cookingchef.factory;

import com.cookingchef.dao.*;
import com.cookingchef.dao.Postgres.*;

public class PostgresFactory implements AbstractFactory {

  @Override
  public UserDAO getUserDAO() {
    return PostgresUserDAO.getPostgresUserDAO();
  }

  @Override
  public AdminSuggestionDAO getAdminSuggestionDAO() {
    return PostgresAdminSuggestionDAO.getPostgresAdminSuggestionDAO();
  }

  @Override
  public UserSuggestionDAO getUserSuggestionDAO() {
    return PostgresUserSuggestionDAO.getPostgresUserSuggestionDAO();
  }

  @Override
  public PartnerDAO getPartnerDAO() {
    return PostgresPartnerDAO.getPostgresPartnerDAO();
  }

  @Override
  public RecipeDAO getRecipeDAO() {
    return PostgresRecipeDAO.getPostgresRecipeDAO();
  }

  @Override
  public IngredientDAO getIngredientDAO() {
    return PostgresIngredientDAO.getPostgresIngredientDAO();
  }

  @Override
  public CategoryDAO getCategoryDAO() {
    return PostgresCategoryDAO.getPostgresCategoryDAO();
  }

  @Override
  public CartDAO getCartDAO(){return PostgresCartDAO.getPostgresCartDAO();}
}
