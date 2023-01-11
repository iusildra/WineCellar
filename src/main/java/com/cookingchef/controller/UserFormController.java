package com.cookingchef.controller;

import com.cookingchef.facade.UserFacade;
import com.cookingchef.model.User;
import com.cookingchef.utils.UserUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.util.Optional;

public class UserFormController {
	@FXML
	private TextField formName = new TextField();

	@FXML
	private TextField formEmail = new TextField();

	@FXML
	private TextField formPhone = new TextField();

	@FXML
	private CheckBox isAdmin = new CheckBox();

	@FXML
	private Button cancelButton = new Button();

	private Optional<Integer> userId = Optional.empty();

	private Runnable callback;

	private static final String ERROR_TITLE = "Error";
	private static final String INFORMATION_TITLE = "Information";
	private static final String SUCCESS_TITLE = "Success";

	@FXML
	protected void onClickValidateButton() {

		UserFacade facade = UserFacade.getUserFacade();

		try {
			if (this.userId.isEmpty()) {
				Notifications.create().title(INFORMATION_TITLE).text("user doesn't exist").showInformation();
				return;
			}
			if (facade.getUser(this.userId).isEmpty()) {
				Notifications.create().title(INFORMATION_TITLE).text("User doesn't exist anymore").showInformation();
				return;
			}
			if (!UserUtils.isEmailValid(this.formEmail.getText())) {
				Notifications.create().title(ERROR_TITLE).text("Please fill the email field").showError();
				return;
			}
			if (this.formName.getText().length() == 0) {
				Notifications.create().title(ERROR_TITLE).text("Please fill the name field").showError();
				return;
			}
			if (!UserUtils.isPhoneValid(this.formPhone.getText())) {
				Notifications.create().title(ERROR_TITLE).text("Please fill the phone field").showError();
				return;
			}

			facade.updateUser(this.userId.get(), this.formName.getText(), this.formEmail.getText(), this.formPhone.getText(),
					this.isAdmin.isSelected());
			Notifications.create().title(SUCCESS_TITLE).text("User updated").showInformation();

			this.callback.run();
			this.onClose();
		} catch (Exception e) {
			Notifications.create().title(ERROR_TITLE).text(e.getMessage()).showError();
		}
	}

	public void setCallback(Runnable callback) {
		this.callback = callback;
	}

	@FXML
	protected void onClose() {
		var stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	public void setUser(Optional<User> user) {
		if (user.isPresent()) {
			this.userId = user.get().getId();
			this.formName.setText(user.get().getName());
			this.formEmail.setText(user.get().getEmail());
			this.formPhone.setText(user.get().getPhone());
			this.isAdmin.setSelected(user.get().getIsAdmin());
		}
	}
}
