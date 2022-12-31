package com.cookingchef.view;

import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

	private static User user;

	private static VBox root = new VBox();
	private static Map<String, Pane> scenes = new HashMap<>();

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Main.user = user;
	}

	public static Map<String, Pane> getScenes() {
		return scenes;
	}

	public static Pane getScene(String name) {
		return scenes.get(name);
	}

	public static void setScenes(Map<String, Pane> scenes) {
		Main.scenes = scenes;
	}

	public static void addScene(String name, Pane pane) {
		scenes.put(name, pane);
	}

	public static void redirect(String scene) {
		root.getChildren().clear();
		root.getChildren().add(scenes.get(scene));
	}

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		var defaultVal = "postgres";
		ConnectionManager.openConnectionPool(defaultVal, defaultVal, defaultVal, 5432);
		addScene("login", FXMLLoader.load(Main.class.getResource("login-view.fxml")));
		addScene("home", FXMLLoader.load(Main.class.getResource("home-view.fxml")));
		root.getChildren().add(getScene("login"));
		Scene scene = new Scene(root, 1920, 1080);
		stage.setTitle("MyChefCook");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}
}
