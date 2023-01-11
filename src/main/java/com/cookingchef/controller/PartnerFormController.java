package com.cookingchef.controller;

import com.cookingchef.facade.PartnerFacade;
import com.cookingchef.model.Partner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;

import org.controlsfx.control.Notifications;

public class PartnerFormController {
	@FXML
	private Label welcomeText;

	@FXML
	private TextField formName;

	@FXML
	private TextField formWebsite;

	@FXML
	private TextField formDescription;

	@FXML
	private Button closeButton;

	private Optional<Integer> partnerId = Optional.empty();

	private Runnable callback;

	private static final String ERROR_TITLE = "Error";
	private static final String INFORMATION_TITLE = "Information";
	private static final String SUCCESS_TITLE = "Success";

	@FXML
	protected void onClickValidateButton() {
		if (!checkInputs()) {
			Notifications.create().title(INFORMATION_TITLE).text("Please fill all the fields").showInformation();
			return;
		}

		PartnerFacade partnerFacade = PartnerFacade.getPartnerFacade();
		var partner = new Partner(this.partnerId, this.formName.getText(), this.formDescription.getText(),
				this.formWebsite.getText());

		try {
			if (partnerFacade.getPartnerByName(partner.getName()).size() > 0) {
				Notifications.create().title(ERROR_TITLE).text("Partner already exists").showError();
				return;
			}
		} catch (SQLException e) {
			Notifications.create().title(ERROR_TITLE).text("Connection to database failed\nPlease try again").showError();
			return;
		}

		if (partner.getId().isEmpty()) {
			try {
				partnerFacade.createPartner(partner);
			} catch (SQLException e) {
				Notifications.create().title(ERROR_TITLE).text("Creation failed\nPlease try again").showError();
				return;
			}
		} else {
			try {
				partnerFacade.updatePartner(partner);
			} catch (SQLException e) {
				Notifications.create().title(ERROR_TITLE).text("Update failed\nPlease try again").showError();
				return;
			}
		}

		Notifications.create().title(SUCCESS_TITLE).text("Operation successful").showConfirm();

		callback.run();
		this.onClose();
	}

	public void reset() {
		this.partnerId = Optional.empty();
	}

	public void fillInputs(Partner partner) {
		this.partnerId = partner.getId();
		this.formName.setText(partner.getName());
		this.formWebsite.setText(partner.getWebsite());
		this.formDescription.setText(partner.getDescription());
	}

	public void setCallback(Runnable callback) {
		this.callback = callback;
	}

	public boolean checkInputs() {
		return this.formName.getText().length() > 0 && this.formWebsite.getText().length() > 0
				&& this.formDescription.getText().length() > 0;
	}

	@FXML
	protected void onClose() {
		var stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
}