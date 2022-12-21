package com.cookingchef.controller;

import com.cookingchef.facade.UserFacade;
import com.cookingchef.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class LoginController {
	@FXML
	private Label welcomeText;

	@FXML
	private Text showText;

	@FXML
	private TextField email;

	@FXML
	private PasswordField password;

	public String getEmail() {
		return email.getText();
	}

	public String getPassword() {
		return password.getText();
	}

	@FXML
	protected void onClickButtonLogin() throws SQLException {
		UserFacade userFacade = UserFacade.getUserFacade();
		User user = userFacade.login(this.getEmail(), this.getPassword());

		if (user != null) {
			showText.setText("Welcome " + user.getName());
		} else {
			showText.setText("Wrong email or password");
		}
	}
}