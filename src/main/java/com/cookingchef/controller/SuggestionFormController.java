package com.cookingchef.controller;

import com.cookingchef.dao.Postgres.PostgresUserSuggestionDAO;
import com.cookingchef.facade.AdminSuggestionFacade;
import com.cookingchef.model.Suggestion;
import com.cookingchef.model.SuggestionCategory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class SuggestionFormController implements Initializable {
  @FXML
  private Label welcomeText;

  @FXML
  private TextField formTitle;

  @FXML
  private TextField formDescription;

  @FXML
  private ComboBox<SuggestionCategory> formCategory;

  @FXML
  private Button closeButton;

  private Optional<Integer> userId = Optional.of(0);//TODO: patch to use logged user
  private Optional<Integer> suggestionId = Optional.empty();
  private ObservableList<SuggestionCategory> categories;

  private Optional<Runnable> callback = Optional.empty();

  @FXML
  protected void onClickValidateButton() {
    if (!Middlewares.mustBeLoggedIn(userId))
      return;

    var value = this.formCategory.getValue();
    if (!Middlewares.requiresValues(value.getId(), "Please select a category"))
      return;

    AdminSuggestionFacade suggestionFacade = AdminSuggestionFacade.getAdminSuggestionFacade();
    var suggestion = new Suggestion(this.suggestionId, this.formTitle.getText(), this.formDescription.getText(),
        value.getId().get(),
        value.getName(), this.userId.get());

    if (suggestion.getId().isEmpty()) {
      try {
        suggestionFacade.addSuggestion(suggestion);
      } catch (SQLException e) {
        Popups.errorPopup("Creation failed");
        return;
      }
    } else {
      try {
        suggestionFacade.updateSuggestion(suggestion);
      } catch (SQLException e) {
        Popups.errorPopup("Update failed");
        return;
      }
    }

    callback.ifPresent(Runnable::run);
    this.close();
  }

  public void reset() {
    this.userId = Optional.empty();
    this.suggestionId = Optional.empty();
  }

  public void fillInputs(Suggestion suggestion) {
    this.suggestionId = suggestion.getId();
    this.formTitle.setText(suggestion.getTitle());
    this.formDescription.setText(suggestion.getDescription());
    this.formCategory.setValue(this.categories.filtered(x -> x.getId().get() == suggestion.getCategoryId()).get(0));
  }

  public void setUserId(Optional<Integer> userId) {
    this.userId = userId;
  }

  public void setCallback(Runnable callback) {
    this.callback = Optional.of(callback);
  }

  @FXML
  protected void close() {
    var stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      this.categories = FXCollections
          .observableArrayList(PostgresUserSuggestionDAO.getPostgresUserSuggestionDAO().getCategories());
      this.formCategory.setItems(this.categories);
    } catch (SQLException e) {
      Popups.errorPopup("Failed to load categories");
    }
  }
}