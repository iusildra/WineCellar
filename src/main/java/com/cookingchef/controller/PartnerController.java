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

public class PartnerController {
  @FXML
  private TableView<Partner> partnerView;
  @FXML
  private TableColumn<Partner, String> name;
  @FXML
  private TableColumn<?, ?> img;
  @FXML
  private TableColumn<Partner, String> website;
  @FXML
  private TableColumn<Partner, String> description;
  @FXML
  private TableColumn<Partner, ?> editPartner;
  @FXML
  private TableColumn<Partner, ?> deletePartner;

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
    this.partnerView = new TableView<>(partners);
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