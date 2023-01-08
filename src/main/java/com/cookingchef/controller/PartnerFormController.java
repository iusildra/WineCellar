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

public class PartnerFormController {
	@FXML
	private Label welcomeText;

	@FXML
	private TextField formName;

	@FXML
	private TextField formWebiste;

	@FXML
	private TextField formDescription;

	@FXML
	private Button closeButton;

	private Optional<Integer> partnerId = Optional.empty();

	private Runnable callback;

	@FXML
	protected void onClickValidateButton() {
		PartnerFacade partnerFacade = PartnerFacade.getPartnerFacade();
		var partner = new Partner(this.partnerId, this.formName.getText(), this.formDescription.getText(),
				this.formWebiste.getText());

		if (partner.getId().isEmpty()) {
			try {
				partnerFacade.createPartner(partner);
			} catch (SQLException e) {
				Popups.errorPopup("Creation failed");
				return;
			}
		} else {
			try {
				partnerFacade.updatePartner(partner);
			} catch (SQLException e) {
				Popups.errorPopup("Update failed");
				return;
			}
		}
		callback.run();
		this.onClose();
	}

	public void reset() {
		this.partnerId = Optional.empty();
	}

	public void fillInputs(Partner partner) {
		this.partnerId = partner.getId();
		this.formName.setText(partner.getName());
		this.formWebiste.setText(partner.getWebsite());
		this.formDescription.setText(partner.getDescription());
	}

	public void setCallback(Runnable callback) {
		this.callback = callback;
	}

	@FXML
	protected void onClose() {
		var stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
}