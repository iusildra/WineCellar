package com.cookingchef.suggestions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.cookingchef.dao.AdminSuggestionDAO;
import com.cookingchef.dao.UserSuggestionDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Suggestion;
import com.cookingchef.model.SuggestionCategory;

public class DAOTests {

  final UserSuggestionDAO userDAO;
  final AdminSuggestionDAO adminDAO;

  public DAOTests() {
    ConnectionManager.openConnectionPool("postgres", "postgres", "postgres", 5432);
    var factory = new PostgresFactory();
    this.userDAO = factory.getUserSuggestionDAO();
    this.adminDAO = factory.getAdminSuggestionDAO();
  }

  @Test
  void createSuggestion() throws SQLException {
    var sugg = new Suggestion("abc", "abc", 0, "a", 0);
    userDAO.createSuggestionInDb(sugg);

    var newSugg = userDAO.getSuggestionById(sugg.getId().get());
    adminDAO.removeSuggestionFromDb(sugg);

    assert newSugg.get().equals(sugg);
  }

  @Test
  void updateSuggestion() throws SQLException {
    var sugg = new Suggestion("abc", "abc", 0, "a", 0);
    userDAO.createSuggestionInDb(sugg);

    sugg.setCategoryId(1);
    adminDAO.updateSuggestionInDb(sugg);

    var newSugg = userDAO.getSuggestionById(sugg.getId().get());
    adminDAO.removeSuggestionFromDb(sugg);

    assert newSugg.get().equals(sugg);
  }

  @Test
  void removeSuggestion() throws SQLException {
    var sugg = new Suggestion("abc", "abc", 0, "a", 0);
    userDAO.createSuggestionInDb(sugg);

    adminDAO.removeSuggestionFromDb(sugg);

    var newSugg = userDAO.getSuggestionById(sugg.getId().get());

    assert newSugg.isEmpty();
  }

  @Test
  void getSuggestionsByTitle() throws SQLException {
    var suggestions = new ArrayList<Suggestion>();
    suggestions.add(new Suggestion("abc", "abc", 0, "a", 0));
    suggestions.add(new Suggestion("abc", "abc", 0, "a", 0));
    suggestions.add(new Suggestion("abc", "abc", 0, "a", 0));
    suggestions.add(new Suggestion("def", "abc", 0, "a", 0));

    for (var sugg : suggestions) {
      userDAO.createSuggestionInDb(sugg);
    }

    var newSugg = adminDAO.getSuggestionsByTitle("abc");

    for (var sugg : suggestions) {
      adminDAO.removeSuggestionFromDb(sugg);
    }

    assert newSugg.size() == 3;
  }

  @Test
  void getAllSuggestions() throws SQLException {
    var suggestions = new ArrayList<Suggestion>();
    suggestions.add(new Suggestion("abc", "abc", 0, "a", 0));
    suggestions.add(new Suggestion("abc", "abc", 0, "a", 0));
    suggestions.add(new Suggestion("abc", "abc", 0, "a", 0));
    suggestions.add(new Suggestion("def", "abc", 0, "a", 0));

    for (var sugg : suggestions) {
      userDAO.createSuggestionInDb(sugg);
    }

    var newSugg = userDAO.getSuggestions();

    for (var sugg : suggestions) {
      adminDAO.removeSuggestionFromDb(sugg);
    }

    assert newSugg.size() == 4;
  }

  @Test
  void getAllCategories() throws SQLException {
    var categories = new ArrayList<SuggestionCategory>();
    categories.add(new SuggestionCategory("abc"));
    categories.add(new SuggestionCategory("abc"));
    categories.add(new SuggestionCategory("abc"));
    categories.add(new SuggestionCategory("def"));

    for (var cat : categories) {
      adminDAO.registerCategoryInDb(cat);
    }

    var newCat = userDAO.getCategories();

    for (var cat : categories) {
      adminDAO.removeCategoryFromDb(cat);
    }

    //TODO: fix this, there are already 3 categories from SQL
    assertEquals(7, newCat.size());
  }
}
