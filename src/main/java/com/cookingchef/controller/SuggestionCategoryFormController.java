package com.cookingchef.controller;

import com.cookingchef.facade.AdminSuggestionFacade;
import com.cookingchef.model.SuggestionCategory;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;

public class SuggestionCategoryFormController {
  @FXML
  private Label welcomeText;

  @FXML
  private TextField formName;

  @FXML
  private Button closeButton;

  private Optional<Integer> categoryId = Optional.empty();

  private Optional<Runnable> callback = Optional.empty();

  @FXML
  protected void onClickValidateButton() {
    if (!checkInputs()) {
      Popups.errorPopup("Please fill all fields");
      return;
    }
      
    AdminSuggestionFacade categoryFacade = AdminSuggestionFacade.getAdminSuggestionFacade();
    var category = new SuggestionCategory(categoryId, this.formName.getText());

    if (category.getId().isEmpty()) {
      try {
        categoryFacade.addSuggestionCategory(category);
      } catch (SQLException e) {
        Popups.errorPopup("Creation failed");
        return;
      }
    } else {
      try {
        categoryFacade.updateSuggestionCategory(category);
      } catch (SQLException e) {
        Popups.errorPopup("Update failed");
        return;
      }
    }

    callback.ifPresent(Runnable::run);
    this.close();
  }

  public void reset() {
    this.categoryId = Optional.empty();
  }

  public void fillInputs(SuggestionCategory category) {
    this.categoryId = category.getId();
    this.formName.setText(category.getName());
  }

  public boolean checkInputs() {
    return this.formName.getText().length() > 0;
  }

  public void setCallback(Runnable callback) {
    this.callback = Optional.of(callback);
  }

  @FXML
  protected void close() {
    var stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
  }
}