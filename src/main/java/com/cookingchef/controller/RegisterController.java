package com.cookingchef.controller;

import com.cookingchef.facade.UserFacade;
import com.cookingchef.model.User;
import com.cookingchef.view.Main;
import com.cookingchef.utils.UserUtils;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;

public class RegisterController {

	public UserFacade userFacade = UserFacade.getUserFacade();
	@FXML
	private TextField email;
	@FXML
	private TextField name;
	@FXML
	private DatePicker birthdate;
	@FXML
	private TextField phone;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField confirmPassword;
	@FXML
	private TextField question;
	@FXML
	private TextField answer;

	private static final String LOGIN_PAGE = "login";
	private static final String ERROR_TITLE = "Error";

	public String getEmail() {
		return email.getText();
	}

	public String getName() {
		return name.getText();
	}

	public Date getBirthdate() {
		return Date.valueOf(birthdate.getValue());
	}

	public String getPhone() {
		return phone.getText();
	}

	public String getPassword() {
		return password.getText();
	}

	public String getQuestion() {
		return question.getText();
	}

	public String getAnswer() {
		return answer.getText();
	}

	public String getConfirmPassword() {
		return confirmPassword.getText();
	}

	public boolean fieldAreNotEmpty() {
		return !getEmail().isEmpty() && !getName().isEmpty() && !getPhone().isEmpty() && !getPassword().isEmpty()
				&& !getConfirmPassword().isEmpty() && !getQuestion().isEmpty() && !getAnswer().isEmpty();
	}

	@FXML
	public void onClickButtonRegister() throws SQLException {
		if (!fieldAreNotEmpty()) {
			Notifications.create().title(ERROR_TITLE).text("Please fill all the fields!").showError();
			return;
		}
		if (!UserUtils.isEmailValid(getEmail())) {
			Notifications.create().title(ERROR_TITLE).text("Email is not valid!").showError();
			return;
		}
		if (!UserUtils.isPhoneValid(getPhone())) {
			Notifications.create().title(ERROR_TITLE).text("Phone number is not valid!").showError();
			return;
		}
		if (!UserUtils.isDateValid(getBirthdate())) {
			Notifications.create().title(ERROR_TITLE).text("Birthdate is not valid!").showError();
			return;
		}
		if (!getPassword().equals(getConfirmPassword())) {
			Notifications.create().title(ERROR_TITLE).text("Passwords do not match!").showError();
			return;
		}

		User user = new User(getName(), getEmail(), getPassword(), getPhone(), getBirthdate(), getQuestion(), getAnswer());
		Optional<User> result = userFacade.register(user);
		if (result.isEmpty()) {
			Notifications.create().title("Registration").text("Registration failed").showError();
			return;
		}

		Notifications.create().title("Registration").text("Registration successful").showInformation();
		try {
			Main.redirect(LOGIN_PAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Main.redirect(LOGIN_PAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onClickAlreadyRegistered() throws IOException {
		Main.redirect(LOGIN_PAGE);
	}
}
