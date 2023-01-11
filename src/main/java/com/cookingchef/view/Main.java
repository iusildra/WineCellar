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
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {

	private static User user;

	private static AtomicReference<Stage> stage = new AtomicReference<>();

	private static final VBox root = new VBox();
	private static Map<String, URL> scenes = new HashMap<>();

	public static Stage getStage() {
		return stage.get();
	}

	public static void setStage(Stage stage) {
		Main.stage.set(stage);
	}

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

	public static void main(String[] args) {launch();}

	@Override
	public void start(Stage stage) throws IOException {
		Main.stage.set(stage);
		var defaultVal = "postgres";
		ConnectionManager.openConnectionPool(defaultVal, defaultVal, defaultVal, 5432);
		addScene("ad", Main.class.getResource("ads/ad-view.fxml"));
		addScene("category", Main.class.getResource("category/category-view.fxml"));
		addScene("home", Main.class.getResource("home-view.fxml"));
		addScene("ingredient", Main.class.getResource("ingredient/ingredient-view.fxml"));
		addScene("login", Main.class.getResource("users/login-view.fxml"));
		addScene("navbar", Main.class.getResource("navbar-view.fxml"));
		addScene("partner", Main.class.getResource("partners/partner-view.fxml"));
		addScene("profile", Main.class.getResource("users/profile-view.fxml"));
		addScene("recipeAdmin", Main.class.getResource("recipe/recipe-admin-view.fxml"));
		addScene("recipe", Main.class.getResource("recipe/recipe-view.fxml"));
		addScene("register", Main.class.getResource("users/register-view.fxml"));
		addScene("suggestion", Main.class.getResource("suggestions/suggestion-view.fxml"));
		addScene("user-management", Main.class.getResource("users/user-management-view.fxml"));
		redirect("login");
		Scene scene = new Scene(root, 1920, 1080);
		stage.setTitle("MyChefCook");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}
}