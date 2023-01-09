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

	private User user;

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
		return !getEmail().isEmpty() && !getName().isEmpty() && !getPhone().isEmpty() && !getPassword().isEmpty() && !getConfirmPassword().isEmpty() && !getQuestion().isEmpty() && !getAnswer().isEmpty();
	}

	@FXML
	public void onClickButtonRegister() throws SQLException {
		if (fieldAreNotEmpty()) {
			if(UserUtils.isEmailValid(getEmail())) {
				if(UserUtils.isPhoneValid(getPhone())){
					if(UserUtils.isDateValid(getBirthdate())) {
						if (getPassword().equals(getConfirmPassword())) {
							User user = new User(getName(), getEmail(), getPassword(), getPhone(), getBirthdate(), getQuestion(), getAnswer());
							Optional<User> result = userFacade.register(user);
							if (result.isPresent()) {
								Notifications.create().title("Registration").text("Registration successful").showInformation();
								try {
									Main.redirect("login");
								} catch (IOException e) {
									e.printStackTrace();
								}
							} else {
								Notifications.create().title("Registration").text("Registration failed").showError();
							}
							try {
								Main.redirect("login");
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							Notifications.create().title("Error").text("Passwords do not match!").showError();
						}
					} else {
						Notifications.create().title("Error").text("Birthdate is not valid!").showError();
					}
				}else{
					Notifications.create().title("Error").text("Phone number is not valid!").showError();
				}
			}else{
				Notifications.create().title("Error").text("Email is not valid!").showError();
			}
		} else {
			Notifications.create().title("Error").text("Please fill all the fields!").showError();
		}
	}

	public void onClickAlreadyRegistered() throws IOException {
		Main.redirect("login");
	}
}
