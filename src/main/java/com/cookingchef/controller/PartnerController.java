package com.cookingchef.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.cookingchef.dao.PartnerDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Partner;
import com.cookingchef.view.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PartnerController implements Initializable {
  @FXML
  private TableView<Partner> partnerView = new TableView<>();
  @FXML
  private TableColumn<Partner, Integer> id = new TableColumn<>("ID");
  @FXML
  private TableColumn<Partner, String> name = new TableColumn<>("Name");
  @FXML
  private TableColumn<Partner, String> img = new TableColumn<>("Image");
  @FXML
  private TableColumn<Partner, String> website = new TableColumn<>("Website");
  @FXML
  private TableColumn<Partner, String> description = new TableColumn<>("Description");
  @FXML
  private TableColumn<Partner, Button> editPartner = new TableColumn<>("Edit");
  @FXML
  private TableColumn<Partner, Button> deletePartner = new TableColumn<>("Delete");

  private Popup popup = new Popup();

  private PartnerDAO dao;
  private ObservableList<Partner> partnerList = FXCollections.observableArrayList();
  private PartnerFormController formController;

  public PartnerController() {
    var factory = new PostgresFactory();
    this.dao = factory.getPartnerDAO();
  }

  public void onCreatePartner() {
    this.openForm();
  }

  public void onUpdatePartner(Partner partner) {
    this.formController.fillInputs(partner);
    this.openForm();
  }

  public void openForm() {
    var loader = new FXMLLoader(Main.class.getResource("partners/form.fxml"));
    Parent form;
    try {
      form = loader.load();
      this.formController = loader.getController();
    } catch (IOException e) {
      showPopup("Could not properly load form");
      e.printStackTrace();
      return;
    }

    this.formController.setCallback(() -> fetchPartners());
    VBox formPage = new VBox();
    formPage.getChildren().add(form);
    Stage stage = new Stage();
    stage.setTitle("Modifying partner");
    stage.setScene(new Scene(formPage, 450, 450));
    stage.setOnCloseRequest(e -> this.formController.reset());
    stage.show();
  }

  public void fetchPartners() {
    try {
      var partners = dao.getPartners();
      this.partnerList.setAll(partners);
      this.partnerView.getItems().setAll(partnerList);
      this.partnerView.refresh();
    } catch (SQLException e) {
      showPopup("Error while fetching partners");
      e.printStackTrace();
    }
  }

  public void showPopup(String msg) {
    this.popup.getContent().add(new Text(msg));
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.id.setCellValueFactory(new PropertyValueFactory<Partner, Integer>("id"));
    this.name.setCellValueFactory(new PropertyValueFactory<Partner, String>("name"));
    this.img.setCellValueFactory(new PropertyValueFactory<Partner, String>("img"));
    this.website.setCellValueFactory(new PropertyValueFactory<Partner, String>("website"));
    this.description.setCellValueFactory(new PropertyValueFactory<Partner, String>("description"));

    fetchPartners();
  }
}