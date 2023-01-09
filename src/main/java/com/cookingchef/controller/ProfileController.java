package com.cookingchef.controller;

import com.cookingchef.facade.UserFacade;
import com.cookingchef.model.User;
import com.cookingchef.utils.UserUtils;
import com.cookingchef.view.Main;
import com.dlsc.formsfx.model.structure.DateField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {


	@FXML
	private User user = Main.getUser();

	@FXML
	private TextField name;
	@FXML
	private Text email;
	@FXML
	private Text birthdate;
	@FXML
	private TextField phone;
	@FXML
	Button edit;



	@FXML
	public void onClickButtonLogout() throws IOException, IOException {
		Main.setUser(null);
		Main.redirect("login");
	}

	@FXML
	public void onClickButtonEdit(){
		name.setDisable(false);
		phone.setDisable(false);
		edit.setStyle("-fx-background-color: #3DCF3A;");
		edit.setText("Save");
		edit.setOnAction(event -> {
			try {
				onClickButtonSave();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		});

	}

	public void onClickButtonSave() throws SQLException {
		if(!name.getText().isEmpty()){
			user.setName(name.getText());
			if(UserUtils.isPhoneValid(phone.getText())){
				user.setPhone(phone.getText());
				UserFacade userFacade = UserFacade.getUserFacade();
				User res = userFacade.update(user);



			}else{
				Notifications.create().title("Error").text("Phone number is empty").showError();
			}
		}else{
			Notifications.create().title("Error").text("Name is empty").showError();
		}

		name.setDisable(true);
		phone.setDisable(true);
		edit.setStyle("-fx-background-color: #006AF5;");
		edit.setText("Edit");
		edit.setOnAction(event -> { onClickButtonEdit(); });
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		name.setText(user.getName());
		email.setText(user.getEmail());
		birthdate.setText(user.getBirthdate().toString());
		phone.setText(user.getPhone());
	}
}
