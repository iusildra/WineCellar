package com.cookingchef.dao;

import java.sql.SQLException;

import com.cookingchef.model.Suggestion;

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
}
