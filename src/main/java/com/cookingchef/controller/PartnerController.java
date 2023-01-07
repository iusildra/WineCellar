package com.cookingchef.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cookingchef.dao.PartnerDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Partner;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PartnerController {
  @FXML
  private TableView<Partner> partnerView = new TableView<>();

  @FXML
  private TableColumn<Partner, Integer> id = new TableColumn<>("ID");

  @FXML
  private TableColumn<Partner, String> name = new TableColumn<>("Name");

  @FXML
  private TableColumn<Partner, ?> img = new TableColumn<>("Image");

  @FXML
  private TableColumn<Partner, String> website = new TableColumn<>("Website");

  @FXML
  private TableColumn<Partner, String> description = new TableColumn<>("Description");

  @FXML
  private TableColumn<Partner, ?> editPartner = new TableColumn<>("Edit");

  @FXML
  private TableColumn<Partner, ?> deletePartner = new TableColumn<>("Delete");

  private PartnerDAO dao;
  private List<Partner> partnerList = new ArrayList<>();

  public PartnerController() {
    var factory = new PostgresFactory();
    this.dao = factory.getPartnerDAO();
    partnerList.add(new Partner("aaa", "aaa", "aaa"));
    partnerList.add(new Partner("bbb", "bbb", "bbb"));
    partnerList.add(new Partner("ccc", "ccc", "ccc"));

    ObservableList<Partner> partners = partnerList.stream()
        .collect(Collectors.toCollection(javafx.collections.FXCollections::observableArrayList));

    this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
    this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
    this.img.setCellValueFactory(new PropertyValueFactory<>("img"));
    this.website.setCellValueFactory(new PropertyValueFactory<>("website"));
    this.description.setCellValueFactory(new PropertyValueFactory<>("description"));

    this.partnerView.getColumns().add(id);
    this.partnerView.getColumns().add(name);
    this.partnerView.getColumns().add(img);
    this.partnerView.getColumns().add(website);
    this.partnerView.getColumns().add(description);
    this.partnerView.getColumns().add(editPartner);
    this.partnerView.getColumns().add(deletePartner);
    this.partnerView.getItems().addAll(partners);
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
}