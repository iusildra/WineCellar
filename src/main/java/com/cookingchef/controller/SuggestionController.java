package com.cookingchef.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.cookingchef.facade.AdminSuggestionFacade;
import com.cookingchef.model.Suggestion;
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
import javafx.stage.Stage;

public class SuggestionController implements Initializable {
  @FXML
  private TableView<Suggestion> suggestionView = new TableView<>();
  @FXML
  private TableColumn<Suggestion, Integer> id = new TableColumn<>("ID");
  @FXML
  private TableColumn<Suggestion, String> title = new TableColumn<>("Name");
  @FXML
  private TableColumn<Suggestion, String> description = new TableColumn<>("Description");
  @FXML
  private TableColumn<Suggestion, String> category = new TableColumn<>("Category");
  @FXML
  private TableColumn<Suggestion, Integer> author = new TableColumn<>("Author");

  private static final String ERROR_TITLE = "Error";

  private ObservableList<Suggestion> suggestionList = FXCollections.observableArrayList();
  private User writer = Main.getUser();
  private AdminSuggestionFacade facade;

  public SuggestionController() {
    this.facade = AdminSuggestionFacade.getAdminSuggestionFacade();
  }

  public void onCreateSuggestion() {
    this.openForm(Optional.empty(), this::fetchSuggestions);
  }

  public void onUpdateSuggestion(Suggestion suggestion) {
    this.openForm(Optional.of(suggestion), this::fetchSuggestions);
  }

  public void openForm(Optional<Suggestion> suggestion, Runnable callback) {
    var loader = new FXMLLoader(Main.class.getResource("suggestions/form.fxml"));
    Parent form;
    SuggestionFormController formController;
    try {
      form = loader.load();
      formController = loader.getController();
    } catch (IOException e) {
      Notifications.create().title(ERROR_TITLE).text("Error while opening form").showError();
      e.printStackTrace();
      return;
    }

    suggestion.ifPresent(part -> formController.fillInputs(part));

    formController.setCallback(callback);
    Stage stage = new Stage();
    stage.setTitle("Modifying suggestion");
    stage.setScene(new Scene(form, 450, 450));
    stage.setOnCloseRequest(e -> formController.reset());
    stage.show();
  }

  public void fetchSuggestions() {
    try {
      var suggestions = facade.getAllSuggestions();
      this.suggestionList.setAll(suggestions);
      this.suggestionView.getItems().setAll(suggestionList);
      this.suggestionView.refresh();
    } catch (SQLException e) {
      Notifications.create().title(ERROR_TITLE).text("Error while fetching suggestions").showError();
      e.printStackTrace();
    }
  }

  public void addAdminCommands() {
    TableColumn<Suggestion, Suggestion> editSuggestion = new TableColumn<>("Edit");
    TableColumn<Suggestion, Suggestion> deleteSuggestion = new TableColumn<>("Delete");

    editSuggestion.setCellValueFactory(
        param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    editSuggestion.setCellFactory(param -> editButtonFactory(Optional.empty()));
    deleteSuggestion.setCellValueFactory(
        param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    deleteSuggestion.setCellFactory(param -> removeButtonFactory(Optional.empty()));

    this.suggestionView.getColumns().add(editSuggestion);
    this.suggestionView.getColumns().add(deleteSuggestion);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.id.setCellValueFactory(new PropertyValueFactory<Suggestion, Integer>("id"));
    this.title.setCellValueFactory(new PropertyValueFactory<Suggestion, String>("title"));
    this.description.setCellValueFactory(new PropertyValueFactory<Suggestion, String>("description"));
    this.category.setCellValueFactory(new PropertyValueFactory<Suggestion, String>("categoryName"));
    this.author.setCellValueFactory(new PropertyValueFactory<Suggestion, Integer>("author"));

    if (this.writer.getIsAdmin()) {
      this.addAdminCommands();
    }

    fetchSuggestions();
  }

  public void deleteSuggestion(Suggestion suggestion) {
      try {
        facade.deleteSuggestion(suggestion);
        this.suggestionList.remove(suggestion);
        this.suggestionView.getItems().remove(suggestion);
        this.suggestionView.refresh();
      } catch (SQLException e) {
        Notifications.create().title(ERROR_TITLE).text("Error while deleting suggestion").showError();
        e.printStackTrace();
      }
  }

  public TableCell<Suggestion, Suggestion> removeButtonFactory(Optional<Runnable> callback) {
    return new TableCell<Suggestion, Suggestion>() {
      private final Button deleteButton = new Button("Delete");

      @Override
      protected void updateItem(Suggestion suggestion, boolean empty) {
        super.updateItem(suggestion, empty);

        if (suggestion == null) {
          setGraphic(null);
          return;
        }

        setGraphic(deleteButton);
        deleteButton.setOnAction(event -> {
          deleteSuggestion(suggestion);
          callback.ifPresent(Runnable::run);
        });
      }
    };
  }

  public TableCell<Suggestion, Suggestion> editButtonFactory(Optional<Runnable> callback) {
    return new TableCell<Suggestion, Suggestion>() {
      private final Button deleteButton = new Button("Edit");

      @Override
      protected void updateItem(Suggestion suggestion, boolean empty) {
        super.updateItem(suggestion, empty);

        if (suggestion == null) {
          setGraphic(null);
          return;
        }

        setGraphic(deleteButton);
        deleteButton.setOnAction(event -> {
          onUpdateSuggestion(suggestion);
          callback.ifPresent(Runnable::run);
        });
      }
    };
  }
}