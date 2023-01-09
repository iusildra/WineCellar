package com.cookingchef.controller;


import com.cookingchef.facade.AdFacade;
import com.cookingchef.model.Ad;
import com.cookingchef.view.Main;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdController implements Initializable {
    @FXML
    private TableView<Object> adView = new TableView<>();

    @FXML
    private TableColumn<Ad, Integer> id = new TableColumn<>("ID");

    @FXML
    private TableColumn<Ad, String> description = new TableColumn<>("Description");

    @FXML
    private TableColumn<Ad, Integer> price = new TableColumn<>("Price");

    @FXML
    private TableColumn<Ingredient, String> ingredient = new TableColumn<>("ingredient");

    @FXML
    private TableColumn<Ad, Ad> editAd = new TableColumn<>("");

    @FXML
    private TableColumn<Ad, Ad> deleteAd = new TableColumn<>("");

    private Popup popup = new Popup();

    private AdFacade adFacade;
    private IngredientFacade ingredientFacade;

    private AdFormController formController;

    public AdController() {
        this.adFacade = AdFacade.getAdFacade();
        this.ingredientFacade = IngredientFacade.getIngredientFacade();
    }

    public void onCreateAd() {
        this.openForm(Optional.empty(), () -> fetchAds());
    }

    public void onUpdateAd(Ad ad) {
        this.formController.fillInputs(ad);
        this.openForm();
    }

    public void openForm() {
        var loader = new FXMLLoader(Main.class.getResource("ads/form.fxml"));
        Parent form;
        try {
            form = loader.load();
            this.formController = loader.getController();
        } catch (IOException e) {
            Notifications.create()
                    .title("Error")
                    .text("Could not properly load form")
                    .showError();
            e.printStackTrace();
            return;
        }

        this.formController.setCallback(() -> fetchAds());
        VBox formPage = new VBox();
        formPage.getChildren().add(form);
        Stage stage = new Stage();
        stage.setTitle("Modifying ad");
        stage.setScene(new Scene(formPage, 450, 450));
        stage.setOnCloseRequest(e -> this.formController.reset());
        stage.show();
    }

    public void fetchAds() {
        try {
            var ads = adFacade.getAllAds();
            ArrayList<Integer> idAds = new ArrayList<Integer>();
            ArrayList<String> descriptionAds = new ArrayList<String>();
            ArrayList<Integer> priceAds = new ArrayList<Integer>();
            ArrayList<String> ingredientAds = new ArrayList<String>();
            for (Ad ad: ads) {
                idAds.add(ad.getId().get());
                descriptionAds.add((ad.getDescriptionPromotion()));
                priceAds.add(ad.getPrice());
                ingredientAds.add(this.ingredientFacade.getIngredientById(ad.getIngredientId()).getName());
            }
            this.adView.getItems().add(idAds);
            this.adView.getItems().add(descriptionAds);
            this.adView.getItems().add(priceAds);
            this.adView.getItems().add(ingredientAds);
            this.adView.refresh();
        } catch (SQLException e) {
            Notifications.create()
                    .title("Error")
                    .text("An error occurred")
                    .showError();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id.setCellValueFactory(new PropertyValueFactory<Ad, Integer>("id"));
        this.description.setCellValueFactory(new PropertyValueFactory<Ad, String>("description_promotion"));
        this.price.setCellValueFactory(new PropertyValueFactory<Ad,Integer>("price"));
        this.ingredient.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));

        this.editAd.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        this.editAd.setCellFactory(param -> editButtonFactory(Optional.empty()));
        this.deleteAd.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        this.deleteAd.setCellFactory(param -> removeButtonFactory(Optional.empty()));

        fetchAds();
    }

    public TableCell<Ad, Ad> removeButtonFactory(Optional<Runnable> callback) {
        return new TableCell<Ad, Ad>() {
            private final Button deleteButton = new Button("Delete");
            @Override
            protected void updateItem(Ad ad, boolean empty) {
                super.updateItem(ad, empty);

                if (ad == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    Notifications.create()
                            .title("Confirmation")
                            .text("Are you sure you want to delete this ad?")
                            .showConfirm();
                    if (Notifications.create().showConfirm() == ButtonType.YES) {
                        try {
                            adFacade.deleteAd(ad);
                            adView.getItems().remove(ad);
                            adView.refresh();
                        } catch (SQLException e) {
                            Notifications.create()
                                    .title("Error")
                                    .text("An error occurred")
                                    .showError();
                            e.printStackTrace();
                        }
                    }
                    callback.ifPresent(Runnable::run);
                });
            }
        };
    }
