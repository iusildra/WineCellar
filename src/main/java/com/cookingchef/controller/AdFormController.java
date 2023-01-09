package com.cookingchef.controller;

import com.cookingchef.model.Ingredient;
import com.cookingchef.facade.AdFacade;
import com.cookingchef.facade.IngredientFacade;
import com.cookingchef.model.Ad;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class AdFormController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField formDescription;

    @FXML
    private TextField formPrice;

    @FXML
    private Button closeButton;

    @FXML
    private ComboBox<Ingredient> ingredientComboBox = new ComboBox<Ingredient>();

    private IngredientFacade ingredientFacade;

    public AdFormController() {
        this.ingredientFacade = IngredientFacade.getIngredientFacade();
    }

    private Optional<Integer> adId = Optional.empty();

    private Runnable callback;

    @FXML
    protected void onClickValidateButton() throws SQLException, IOException {
        AdFacade adFacade = AdFacade.getAdFacade();

        var ad = new Ad(this.formDescription.getText(), Integer.parseInt(this.formPrice.getText()), this.ingredientComboBox.getValue().getId());

        if (ad.getId().isEmpty()){
            try{
                adFacade.addAd(ad);
            } catch (SQLException e) {
                Notifications.create()
                        .title("Error")
                        .text("Creation failed")
                        .showError();
                return;
            }
        }else{
            try{
                adFacade.updateAd(ad);
            } catch (SQLException e) {
                Notifications.create()
                        .title("Error")
                        .text("Update failed")
                        .showError();
                return;
            }
        }

        callback.run();
        this.onClose();
    }

    public void reset() {
        this.adId = Optional.empty();
    }

    public void fillInputs(Ad ad) {
        this.adId = ad.getId();
        this.formDescription.setText(ad.getDescriptionPromotion());
        this.formPrice.setText(String.valueOf(ad.getPrice()));
        this.ingredientComboBox.setValue(ingredientFacade.getIngredientById(ad.getIngredientId()).getName());
    }

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    @FXML
    protected void onClose() {
        var stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Formater l'input de price pour qu'elle n'accepte que des chiffres

        // Créer un TextFormatter qui n'autorise que les chiffres
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            // Autoriser seulement les chiffres
            if (text.matches("[0-9]*")) {
                return change;
            }

            return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        formPrice.setTextFormatter(textFormatter);

        //Initialiser la combobox avec tous les ingredients
        // Ajouter des éléments à la ComboBox
        this.ingredientComboBox.getItems().addAll(ingredientFacade.getAllIngredients());

        // Sélectionner un élément par défaut
        this.ingredientComboBox.getSelectionModel().select(0);
    }
}
