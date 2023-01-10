package com.cookingchef.facade;

import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import com.cookingchef.dao.AdminSuggestionDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Suggestion;

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
}
