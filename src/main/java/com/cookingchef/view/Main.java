package com.cookingchef.view;

import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

	private static User user;

	public static Stage stage;

	private static VBox root = new VBox();
	private static Map<String, URL> scenes = new HashMap<>();

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Main.user = user;
	}

	public static Map<String, URL> getScenes() {
		return scenes;
	}

	public static URL getScene(String name) {
		return scenes.get(name);
	}

	public static void setScenes(Map<String, URL> scenes) {
		Main.scenes = scenes;
	}

	public static void addScene(String name, URL pane) {
		scenes.put(name, pane);
	}

	public static void redirect(String scene) throws IOException {
		root.getChildren().clear();
		root.getChildren().add(FXMLLoader.load(scenes.get(scene)));
	}

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws IOException {
		this.stage = stage;
		var defaultVal = "postgres";
		ConnectionManager.openConnectionPool(defaultVal, defaultVal, defaultVal, 5432);
		addScene("login", Main.class.getResource("login-view.fxml"));
		addScene("home", Main.class.getResource("home-view.fxml"));
		redirect("login");
		Scene scene = new Scene(root, 1920, 1080);
		stage.setTitle("MyChefCook");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}
}
