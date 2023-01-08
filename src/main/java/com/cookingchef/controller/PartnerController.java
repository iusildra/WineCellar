package com.cookingchef.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.cookingchef.dao.PartnerDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Partner;
import com.cookingchef.view.Main;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PartnerController implements Initializable {
  @FXML
  private TableView<Partner> partnerView = new TableView<>();
  @FXML
  private TableColumn<Partner, Integer> id = new TableColumn<>("ID");
  @FXML
  private TableColumn<Partner, String> name = new TableColumn<>("Name");
  @FXML
  private TableColumn<Partner, String> website = new TableColumn<>("Website");
  @FXML
  private TableColumn<Partner, String> description = new TableColumn<>("Description");
  @FXML
  private TableColumn<Partner, Partner> editPartner = new TableColumn<>("");
  @FXML
  private TableColumn<Partner, Partner> deletePartner = new TableColumn<>("");

  private PartnerDAO dao;
  private ObservableList<Partner> partnerList = FXCollections.observableArrayList();
  private PartnerFormController formController;

  public PartnerController() {
    var factory = new PostgresFactory();
    this.dao = factory.getPartnerDAO();
  }

  public void onCreatePartner() {
    this.openForm(Optional.empty(), () -> fetchPartners());
  }

  public void onUpdatePartner(Partner partner) {
    this.openForm(Optional.of(partner), () -> fetchPartners());
  }

  public void openForm(Optional<Partner> partner, Runnable callback) {
    var loader = new FXMLLoader(Main.class.getResource("partners/form.fxml"));
    Parent form;
    try {
      form = loader.load();
      this.formController = loader.getController();
    } catch (IOException e) {
      Popups.errorPopup("Could not properly load form");
      e.printStackTrace();
      return;
    }

    partner.ifPresent(part -> this.formController.fillInputs(part));

    this.formController.setCallback(callback);
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
      Popups.errorPopup("Error while fetching partners");
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.id.setCellValueFactory(new PropertyValueFactory<Partner, Integer>("id"));
    this.name.setCellValueFactory(new PropertyValueFactory<Partner, String>("name"));
    this.website.setCellValueFactory(new PropertyValueFactory<Partner, String>("website"));
    this.description.setCellValueFactory(new PropertyValueFactory<Partner, String>("description"));
    this.editPartner.setCellValueFactory(
        param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    this.editPartner.setCellFactory(param -> editButtonFactory(Optional.empty()));
    this.deletePartner.setCellValueFactory(
        param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    this.deletePartner.setCellFactory(param -> removeButtonFactory(Optional.empty()));

    fetchPartners();
  }

  public void confirmDelete(Partner partner) {
    Popups.confirmationPopup("Delete partner ?", () -> {
      try {
        dao.removePartnerFromDb(partner);
        this.partnerList.remove(partner);
        this.partnerView.getItems().remove(partner);
        this.partnerView.refresh();
      } catch (SQLException e) {
        Popups.errorPopup("Error while deleting partner");
        e.printStackTrace();
      }
    });

  }

  public TableCell<Partner, Partner> removeButtonFactory(Optional<Runnable> callback) {
    return new TableCell<Partner, Partner>() {
      private final Button deleteButton = new Button("Delete");

      @Override
      protected void updateItem(Partner partner, boolean empty) {
        super.updateItem(partner, empty);

        if (partner == null) {
          setGraphic(null);
          return;
        }

        setGraphic(deleteButton);
        deleteButton.setOnAction(event -> {
          confirmDelete(partner);
          callback.ifPresent(Runnable::run);
        });
      }
    };
  }

  public TableCell<Partner, Partner> editButtonFactory(Optional<Runnable> callback) {
    return new TableCell<Partner, Partner>() {
      private final Button deleteButton = new Button("Edit");

      @Override
      protected void updateItem(Partner partner, boolean empty) {
        super.updateItem(partner, empty);

        if (partner == null) {
          setGraphic(null);
          return;
        }

        setGraphic(deleteButton);
        deleteButton.setOnAction(event -> {
          onUpdatePartner(partner);
          callback.ifPresent(Runnable::run);
        });
      }
    };
  }
}