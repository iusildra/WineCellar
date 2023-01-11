package com.cookingchef.facade;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import com.cookingchef.dao.UserSuggestionDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Suggestion;

public class UserSuggestionFacade {
  private static AtomicReference<UserSuggestionFacade> instance = new AtomicReference<>();
  private final UserSuggestionDAO userSuggestionDAO;

  /**
   * Private constructor for the singleton pattern.
   */
  protected UserSuggestionFacade() {
    var factory = new PostgresFactory();
    this.userSuggestionDAO = factory.getUserSuggestionDAO();
  }

  /**
   * Get the singleton instance of this facade.
   *
   * @return the singleton instance of this facade
   */
  public static UserSuggestionFacade getUserSuggestionFacade() {
    instance.compareAndSet(null, new UserSuggestionFacade());
    return instance.get();
  }

  /**
   * Add a suggestion to the database.
   * @param suggestion
   * @return
   * @throws SQLException
   */
  public Optional<Suggestion> addSuggestion(Suggestion suggestion) throws SQLException {
    var newId = userSuggestionDAO.createSuggestionInDb(suggestion);

    if (newId.isPresent())
      return userSuggestionDAO.getSuggestionById(newId.get());

    return Optional.empty();
  }

  /**
   * Get a suggestion by its id.
   * @param id
   * @return
   * @throws SQLException
   */
  public Optional<Suggestion> getSuggestionById(int id) throws SQLException {
    return userSuggestionDAO.getSuggestionById(id);
  }

  /**
   * Get a suggestion by its name.
   * @param title
   * @return
   * @throws SQLException
   */
  public List<Suggestion> getSuggestionsByTitle(String title) throws SQLException {
    return userSuggestionDAO.getSuggestionsByTitle(title);
  }

  /**
   * Get all suggestions.
   * @return
   * @throws SQLException
   */
  public List<Suggestion> getAllSuggestions() throws SQLException {
    return userSuggestionDAO.getSuggestions();
  }
}
