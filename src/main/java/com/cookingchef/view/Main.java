package com.cookingchef.view;

import com.cookingchef.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Dictionary;
import java.util.Hashtable;


public class Main extends Application {

	private static User user;

	private static VBox root = new VBox();
	private static Dictionary<String, Pane> scenes = new Hashtable<String, Pane>();

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Main.user = user;
	}

	public static Dictionary<String, Pane> getScenes() {
		return scenes;
	}

	public static Pane getScene(String name) {
		return scenes.get(name);
	}

	public static void setScenes(Dictionary<String, Pane> scenes) {
		Main.scenes = scenes;
	}

	public static void addScene(String name, Pane pane) {
		scenes.put(name, pane);
	}

	public static void set_Scene(String scene) {
		root.getChildren().clear();
		root.getChildren().add(scenes.get(scene));
	}

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
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
