package com.cookingchef.controller;

import com.cookingchef.facade.PartnerFacade;
import com.cookingchef.model.Partner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class PartnerFormController {
	@FXML
	private Label welcomeText;

	@FXML
	private TextField formName;

	@FXML
	private TextField formImg;

	@FXML
	private TextField formWebiste;

	@FXML
	private TextField formDescription;

	@FXML
	private Button closeButton;

	private Optional<Integer> partnerId = Optional.empty();

	private Runnable callback;

	@FXML
	protected void onClickValidateButton() throws SQLException, IOException {
		PartnerFacade partnerFacade = PartnerFacade.getPartnerFacade();
		var partner = new Partner(this.formName.getText(), this.formImg.getText(), this.formWebiste.getText(),
				this.formDescription.getText());

		if (this.partnerId.isEmpty())
			partnerFacade.createPartner(partner);
		else
			partnerFacade.updatePartner(partner);

		callback.run();
		this.onClose();
	}

	public void reset() {
		this.partnerId = Optional.empty();
	}

	public void fillInputs(Partner partner) {
		this.formName = new TextField(partner.getName());
		this.formImg = new TextField(partner.getImg().orElse("No image"));
		this.formWebiste = new TextField(partner.getWebsite());
		this.formDescription = new TextField(partner.getDescription());
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