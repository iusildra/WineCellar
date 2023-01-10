package com.cookingchef.controller;

import com.cookingchef.facade.UserFacade;
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

import com.cookingchef.model.User;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserManagementController implements Initializable {
	@FXML
	private TableView table = new TableView<User>();
	private ObservableList<User> userList = FXCollections.observableArrayList();
	@FXML
	private TableColumn<User, Integer> id = new TableColumn<>("ID");
	@FXML
	private TableColumn<User, String> name = new TableColumn<>("Name");
	@FXML
	private TableColumn<User, String> email = new TableColumn<>("Email");
	@FXML
	private TableColumn<User, String> phone = new TableColumn<>("Phone");
	@FXML
	private TableColumn<User, String> birthdate = new TableColumn<User, String>("birthdate");
	@FXML
	private TableColumn<User, Boolean> isAdmin = new TableColumn<>("isAdmin");
	@FXML
	private TableColumn<User,User> edit = new TableColumn<>("Edit");
	@FXML
	private TableColumn<User,User> delete = new TableColumn<>("Delete");

	@FXML
	private UserFormController formController;



	public void onCreateUser() {
		this.openForm(Optional.empty(), this::fetchUsers);
	}

	public void onUpdateUser(User user) {
		this.openForm(Optional.of(user), this::fetchUsers);
	}


	public void openForm(Optional<User> user, Runnable callback) {
		var loader = new FXMLLoader(Main.class.getResource("users/user-form-view.fxml"));
		Parent form;
		try {
			form = loader.load();
			this.formController = loader.getController();
		} catch (IOException e) {
			Notifications.create().text("Error loading form").showError();
			e.printStackTrace();
			return;
		}

		this.formController.setUser(user);
		this.formController.setCallback(callback);
		Stage stage = new Stage();
		stage.setTitle("Modifying partner");
		stage.setScene(new Scene(form, 600, 400));
		stage.show();
	}

	public void fetchUsers() {
		try {
			UserFacade facade = UserFacade.getUserFacade();
			userList.setAll(facade.getUsers());
			table.getItems().setAll(userList);
			table.refresh();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
		email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
		phone.setCellValueFactory(new PropertyValueFactory<User,String>("phone"));
		birthdate.setCellValueFactory(new PropertyValueFactory<User,String>("birthdate"));
		isAdmin.setCellValueFactory(new PropertyValueFactory<User,Boolean>("isAdmin"));
		edit.setCellValueFactory(new PropertyValueFactory<User,User>("edit"));
		delete.setCellValueFactory(new PropertyValueFactory<User,User>("delete"));
		this.edit.setCellValueFactory(
				param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		this.edit.setCellFactory(param -> editButtonFactory(Optional.empty()));
		this.delete.setCellValueFactory(
				param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		this.delete.setCellFactory(param -> removeButtonFactory(Optional.empty()));

		fetchUsers();
		System.out.println(table.getItems());
	}

	public TableCell<User, User> editButtonFactory(Optional<Runnable> callback) {
		return new TableCell<User, User>() {
			private final Button deleteButton = new Button("Edit");

			@Override
			protected void updateItem(User user, boolean empty) {
				super.updateItem(user, empty);

				if (user == null) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);
				deleteButton.setOnAction(event -> {
					onUpdateUser(user);
					callback.ifPresent(Runnable::run);
				});
			}
		};
	}

	public void deleteUser(User user) {
		UserFacade facade = UserFacade.getUserFacade();
		facade.deleteUser(user);
		this.userList.remove(user);
		this.table.getItems().remove(user);
		this.table.refresh();
	}

		public TableCell<User, User> removeButtonFactory(Optional<Runnable> callback) {
		return new TableCell<User, User>() {
			private final Button deleteButton = new Button("Delete");

			@Override
			protected void updateItem(User user, boolean empty) {
				super.updateItem(user, empty);

				if (user == null) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);
				deleteButton.setOnAction(event -> {
					deleteUser(user);
					callback.ifPresent(Runnable::run);
				});
			}
		};
	}
}
