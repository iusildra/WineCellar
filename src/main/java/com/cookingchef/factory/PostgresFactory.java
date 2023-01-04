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


  public PartnerDAO getPartnerDAO() {
    return PostgresPartnerDAO.getPostgresPartnerDAO();
  }

  @Override
  public IngredientDAO getIngredientDAO() {
    return PostgresIngredientDAO.getPostgresIngredientDAO();
  }
}
