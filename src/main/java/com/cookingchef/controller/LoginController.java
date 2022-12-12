package com.cookingchef.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {
	@FXML
	private Label welcomeText;

	@FXML
	protected void onHelloButtonClick() {
		welcomeText.setText("Welcome to JavaFX Application!");
	}

	@FXML
	protected void onClickButtonLogin() {
		welcomeText.setText("you clicked login");
	}
}