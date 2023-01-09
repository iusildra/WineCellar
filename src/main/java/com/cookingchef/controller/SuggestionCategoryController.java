package com.cookingchef.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.cookingchef.facade.AdminSuggestionFacade;
import com.cookingchef.model.SuggestionCategory;
import com.cookingchef.model.User;
import com.cookingchef.view.Main;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SuggestionCategoryController implements Initializable {
  @FXML
  private TableView<SuggestionCategory> categoryView = new TableView<>();
  @FXML
  private TableColumn<SuggestionCategory, Integer> id = new TableColumn<>("ID");
  @FXML
  private TableColumn<SuggestionCategory, String> name = new TableColumn<>("Name");

  private AdminSuggestionFacade facade;
  private ObservableList<SuggestionCategory> categoryList = FXCollections.observableArrayList();
  private User user = new User(0, null, null, null, null, null, null, null, true);//TODO: patch to use logged user

  public SuggestionCategoryController() {
    this.facade = AdminSuggestionFacade.getAdminSuggestionFacade();
  }

  public void onCreateSuggestionCategory() {
    this.openForm(Optional.empty(), this::fetchSuggestionCategories);
  }

  public void onUpdateSuggestionCategory(SuggestionCategory category) {
    this.openForm(Optional.of(category), this::fetchSuggestionCategories);
  }

  public void openForm(Optional<SuggestionCategory> category, Runnable callback) {
    var loader = new FXMLLoader(Main.class.getResource("suggestions/category-form.fxml"));
    Parent form;
    SuggestionCategoryFormController formController;
    try {
      form = loader.load();
      formController = loader.getController();
    } catch (IOException e) {
      Popups.errorPopup("Could not properly load form");
      e.printStackTrace();
      return;
    }

    category.ifPresent(part -> formController.fillInputs(part));

    formController.setCallback(callback);
    VBox formPage = new VBox();
    formPage.getChildren().add(form);
    Stage stage = new Stage();
    stage.setTitle("Modifying category");
    stage.setScene(new Scene(formPage, 450, 450));
    stage.setOnCloseRequest(e -> formController.reset());
    stage.show();
  }

  public void fetchSuggestionCategories() {
    try {
      var categories = facade.getAllCategories();
      this.categoryList.setAll(categories);
      this.categoryView.getItems().setAll(categoryList);
      this.categoryView.refresh();
    } catch (SQLException e) {
      Popups.errorPopup("Error while fetching categories");
      e.printStackTrace();
    }
  }

  public void addAdminCommands() {
    TableColumn<SuggestionCategory, SuggestionCategory> editSuggestionCategory = new TableColumn<>("Edit");
    TableColumn<SuggestionCategory, SuggestionCategory> deleteSuggestionCategory = new TableColumn<>("Delete");

    editSuggestionCategory.setCellValueFactory(
        param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    editSuggestionCategory.setCellFactory(param -> editButtonFactory(Optional.empty()));
    deleteSuggestionCategory.setCellValueFactory(
        param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    deleteSuggestionCategory.setCellFactory(param -> removeButtonFactory(Optional.empty()));

    this.categoryView.getColumns().add(editSuggestionCategory);
    this.categoryView.getColumns().add(deleteSuggestionCategory);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.id.setCellValueFactory(new PropertyValueFactory<SuggestionCategory, Integer>("id"));
    this.name.setCellValueFactory(new PropertyValueFactory<SuggestionCategory, String>("name"));

    if (this.user.getIsAdmin()) {
      this.addAdminCommands();
    }

    fetchSuggestionCategories();
  }

  public void confirmDelete(SuggestionCategory category) {
    Popups.confirmationPopup("Delete category ?", () -> {
      try {
        facade.deleteSuggestionCategory(category);
        this.categoryList.remove(category);
        this.categoryView.getItems().remove(category);
        this.categoryView.refresh();
      } catch (SQLException e) {
        Popups.errorPopup("Error while deleting category");
        e.printStackTrace();
      }
    });
  }

  public TableCell<SuggestionCategory, SuggestionCategory> removeButtonFactory(Optional<Runnable> callback) {
    return new TableCell<SuggestionCategory, SuggestionCategory>() {
      private final Button deleteButton = new Button("Delete");

      @Override
      protected void updateItem(SuggestionCategory category, boolean empty) {
        super.updateItem(category, empty);

        if (category == null) {
          setGraphic(null);
          return;
        }

        setGraphic(deleteButton);
        deleteButton.setOnAction(event -> {
          confirmDelete(category);
          callback.ifPresent(Runnable::run);
        });
      }
    };
  }

  public TableCell<SuggestionCategory, SuggestionCategory> editButtonFactory(Optional<Runnable> callback) {
    return new TableCell<SuggestionCategory, SuggestionCategory>() {
      private final Button deleteButton = new Button("Edit");

      @Override
      protected void updateItem(SuggestionCategory category, boolean empty) {
        super.updateItem(category, empty);

        if (category == null) {
          setGraphic(null);
          return;
        }

        setGraphic(deleteButton);
        deleteButton.setOnAction(event -> {
          onUpdateSuggestionCategory(category);
          callback.ifPresent(Runnable::run);
        });
      }
    };
  }
}