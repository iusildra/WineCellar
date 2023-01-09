package com.cookingchef.dao;

import java.sql.SQLException;
import java.util.Optional;

import com.cookingchef.model.Suggestion;
import com.cookingchef.model.SuggestionCategory;

public interface AdminSuggestionDAO extends UserSuggestionDAO {
  
  /**
   * Update the suggestion in the database.
   * 
   * @param suggestion The suggestion object that you want to update in the database.
   */
  public void updateSuggestionInDb(Suggestion suggestion) throws SQLException;

  /**
   * Removes a suggestion from the database
   * 
   * @param suggestion The suggestion to remove from the database.
   */
  public void removeSuggestionFromDb(Suggestion suggestion) throws SQLException;

  /**
   * Register a suggestion category in the database.
   * 
   * @param category The category to register in the database.
   * @return An optional integer.
   */
  public Optional<Integer> registerCategoryInDb(SuggestionCategory category) throws SQLException;

  /**
   * Remove a category from the database.
   * 
   * @param category
   * @throws SQLException
   */
  public void removeCategoryFromDb(SuggestionCategory category) throws SQLException;

  /**
   * Update a category in the database.
   * 
   * @param category
   * @throws SQLException
   */
  public void updateCategoryInDb(SuggestionCategory category) throws SQLException;
}
