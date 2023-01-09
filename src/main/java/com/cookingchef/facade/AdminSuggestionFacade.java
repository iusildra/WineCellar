package com.cookingchef.facade;

import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import com.cookingchef.dao.AdminSuggestionDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Suggestion;
import com.cookingchef.model.SuggestionCategory;

public class AdminSuggestionFacade extends UserSuggestionFacade {

  private static AtomicReference<AdminSuggestionFacade> instance = new AtomicReference<>();
  private final AdminSuggestionDAO adminSgSuggestionDAO;

  protected AdminSuggestionFacade() {
    var factory = new PostgresFactory();
    this.adminSgSuggestionDAO = factory.getAdminSuggestionDAO();
  }

  public static AdminSuggestionFacade getAdminSuggestionFacade() {
    instance.compareAndSet(null, new AdminSuggestionFacade());
    return instance.get();
  }

  public void deleteSuggestion(Suggestion suggestion) throws SQLException {
    adminSgSuggestionDAO.removeSuggestionFromDb(suggestion);
  }

  public void updateSuggestion(Suggestion suggestion) throws SQLException {
    adminSgSuggestionDAO.updateSuggestionInDb(suggestion);
  }

  public Optional<Integer> addSuggestionCategory(SuggestionCategory category) throws SQLException {
    return adminSgSuggestionDAO.registerCategoryInDb(category);
  }

  public void deleteSuggestionCategory(SuggestionCategory category) throws SQLException {
    adminSgSuggestionDAO.removeCategoryFromDb(category);
  }

  public void updateSuggestionCategory(SuggestionCategory category) throws SQLException {
    adminSgSuggestionDAO.updateCategoryInDb(category);
  }

}
