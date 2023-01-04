package com.cookingchef.controller;

import com.cookingchef.facade.UserFacade;
import com.cookingchef.model.User;
import com.cookingchef.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.Optional;

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
	protected void onClickButtonLogin() {
		UserFacade userFacade = UserFacade.getUserFacade();
		Optional<User> user = null;
		try {
			user = userFacade.login(this.getEmail(), this.getPassword());
		} catch (SQLException e) {
			// TODO : gérer exception
			throw new RuntimeException(e);
		}

		if (user.isPresent()) {
			showText.setText("Welcome " + user.get().getName());
			Main.redirect("home");
		} else {
			showText.setText("Wrong email or password");
		}
	}
}