package com.cookingchef.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.cookingchef.dao.PartnerDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Partner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

  private PartnerDAO dao;
  private ObservableList<Partner> partnerList = FXCollections.observableArrayList();

  public PartnerController() {
    var factory = new PostgresFactory();
    this.dao = factory.getPartnerDAO();
  }

  public void onCreatePartner() {
    var newParter = new Partner("ddd", "ddd", "ddd");
    try {
      var partner = dao.registerPartnerInDb(newParter);
      if (partner.isPresent())
        partnerList.add(newParter);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.partnerList.add(new Partner("aaa", "aaa", "aaa"));
    this.partnerList.add(new Partner("bbb", "bbb", "bbb"));
    this.partnerList.add(new Partner("ccc", "ccc", "ccc"));

    this.id.setCellValueFactory(new PropertyValueFactory<Partner, Integer>("id"));
    this.name.setCellValueFactory(new PropertyValueFactory<Partner, String>("name"));
    this.img.setCellValueFactory(new PropertyValueFactory<Partner, String>("img"));
    this.website.setCellValueFactory(new PropertyValueFactory<Partner, String>("website"));
    this.description.setCellValueFactory(new PropertyValueFactory<Partner, String>("description"));

    this.partnerView.getItems().addAll(partnerList);
    
  }
}