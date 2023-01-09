package com.cookingchef.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.cookingchef.model.Suggestion;
import com.cookingchef.model.SuggestionCategory;

public interface UserSuggestionDAO {
  /**
   * Creates a suggestion in the database
   * 
   * @param suggestion The suggestion object that you want to create in the
   *                   database.
   * @return An optional integer.
   */
  public Optional<Integer> createSuggestionInDb(Suggestion suggestion) throws SQLException;

  /**
   * Fetch a suggestion from the database by its id.
   * 
   * @param id The id of the suggestion you want to get.
   * @return An Optional object that contains a Suggestion object.
   */
  public Optional<Suggestion> getSuggestionById(int id) throws SQLException;

  /**
   * Returns a list of suggestions that have the given title.
   * 
   * @param title The title of the suggestion.
   * @return A list of suggestions that have the same title as the title passed
   *         in.
   */
  public List<Suggestion> getSuggestionsByTitle(String title) throws SQLException;

  /**
   * Returns the list of suggestions from the database.
   * 
   * @return The list of suggestions.
   */
  public List<Suggestion> getSuggestions() throws SQLException;

  /**
   * Returns the list of categories from the database.
   * 
   * @return The list of categories.
   */
  public List<SuggestionCategory> getCategories() throws SQLException;
}
