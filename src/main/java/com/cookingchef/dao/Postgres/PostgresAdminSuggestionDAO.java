package com.cookingchef.dao.Postgres;

import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import com.cookingchef.dao.AdminSuggestionDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.Suggestion;
import com.cookingchef.model.SuggestionCategory;
import com.cookingchef.model.SuggestionCategoryDbFields;

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

  @Override
  public Optional<Integer> registerCategoryInDb(SuggestionCategory category) throws SQLException {
    var query = "INSERT INTO suggestion_category (name) VALUES (?) RETURNING id";
    var conn = ConnectionManager.getConnection();

    try (var stmt = conn.prepareStatement(query)) {
      stmt.setString(1, category.getName());
      var rs = stmt.executeQuery();

      if (rs.next()) {
        var newId = Optional.of(rs.getInt(SuggestionCategoryDbFields.ID.value));
        category.setId(newId);
        return newId;
      }

      return Optional.empty();
    }
  }

  @Override
  public void removeCategoryFromDb(SuggestionCategory category) throws SQLException {
    var query = "DELETE FROM suggestion_category WHERE id = ?";
    var conn = ConnectionManager.getConnection();

    try (var stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, category.getId().get());
      stmt.executeUpdate();
    }
  }

  @Override
  public void updateCategoryInDb(SuggestionCategory category) throws SQLException {
    var query = "UPDATE suggestion_category SET name = ? WHERE id = ?";
    var conn = ConnectionManager.getConnection();

    try (var stmt = conn.prepareStatement(query)) {
      stmt.setString(1, category.getName());
      stmt.setInt(2, category.getId().get());
      stmt.executeUpdate();
    }
  }
}
