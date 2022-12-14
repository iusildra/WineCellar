package com.cookingchef.controller;

import com.cookingchef.facade.UserFacade;
import com.cookingchef.model.User;
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
		UserFacade userFacade = UserFacade.getUserFacade();
		User user = userFacade.login();

		if (user != null) {
			// TODO redirect to home page
		} else {
			// TODO show error message
		}
	}
}