package com.cookingchef.dao;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.Suggestion;

public class PostgresAdminSuggestionDAO extends PostgresUserSuggestionDAO implements AdminSuggestionDAO {

  private static AtomicReference<PostgresAdminSuggestionDAO> instance = new AtomicReference<>();

  private PostgresAdminSuggestionDAO() {
  }

  public static PostgresAdminSuggestionDAO getPostgresAdminSuggestionDAO() {
    instance.compareAndSet(null, new PostgresAdminSuggestionDAO());
    return instance.get();
  }

  @Override
  public void updateSuggestionInDb(Suggestion suggestion) throws SQLException {
    var query = "UPDATE suggestion SET title = ?, description = ?, category = ? WHERE id = ?";
    var conn = ConnectionManager.getConnection();

    try (var stmt = conn.prepareStatement(query)) {
      stmt.setString(1, suggestion.getTitle());
      stmt.setString(2, suggestion.getDescription());
      stmt.setInt(3, suggestion.getCategoryId());
      stmt.setInt(4, suggestion.getId().get());
      stmt.executeUpdate();
    }
  }

  @Override
  public void removeSuggestionFromDb(Suggestion suggestion) throws SQLException {
    var query = "DELETE FROM suggestion WHERE id = ?";
    var conn = ConnectionManager.getConnection();

    try (var stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, suggestion.getId().get());
      stmt.executeUpdate();
    }

  }

}
