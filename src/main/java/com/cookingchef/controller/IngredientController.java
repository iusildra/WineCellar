package com.cookingchef.controller;

import com.cookingchef.facade.IngredientFacade;
import com.cookingchef.model.Ingredient;
import com.cookingchef.view.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class IngredientController implements Initializable {

    @FXML
    private Text showText;

    @FXML
    private TextField nameIngredient;

    @FXML
    private byte[] imageIngredient;

    @FXML
    private CheckBox allergen;

    @FXML
    private ListView<Ingredient> ingredientList = new ListView<Ingredient>();


    public String getNameIngredient() {
        return nameIngredient.getText();
    }

    public Boolean getAllergen() {
        return allergen.isSelected();
    }




    private IngredientFacade ingredientFacade;

    public IngredientController() {
        this.ingredientFacade  = IngredientFacade.getIngredientFacade();
    }

    public ArrayList<Ingredient> showList() {

        ArrayList<Ingredient> ingredients;

        try {
            ingredients = this.ingredientFacade.getAllIngredients();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingredients;
    }

    public void onClickAddImageIngredient() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(Main.stage);
        if (file != null) {
            try {
                this.imageIngredient = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                // TODO : gérer exception
                e.printStackTrace();
            }
        }
    }

    public void createIngredient() {
        if (this.imageIngredient == null) {
            // TODO : afficher toast erreur ou mettre le form en rouge
            showText.setText("Please select an image");
        }
        if (this.nameIngredient.getText().isEmpty()) {
            // TODO : afficher toast erreur ou mettre le form en rouge
            showText.setText("Please enter a name");
        }
        else {
            try {
                this.ingredientFacade.createIngredient(this.getNameIngredient(), this.imageIngredient, this.getAllergen());
                showText.setText("Ingredient created");
            } catch (SQLException e) {
                // TODO: gérer exception (si ingrédient déjà existe ou autre)
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteIngredient(int idIngredient) {
        try {
            // TODO : récupérer l'id de l'ingrédient à supprimer
            this.ingredientFacade.deleteIngredient(idIngredient);
            this.ingredientList.getItems().remove(idIngredient);
            showText.setText("Ingredient deleted");
        } catch (SQLException e) {
            // TODO : gérer exception
            showText.setText("Erreur lors de la suppressionn de l'ingrédient");
        }
    }

    public void updateIngredient(int idIngredient) {
        // TODO : récupérer l'id de l'ingrédient à modifier
        if (this.imageIngredient == null) {
            // TODO : afficher toast erreur ou mettre le form en rouge
            showText.setText("Please select an image");
        }
        if (this.nameIngredient.getText().isEmpty()) {
            // TODO : afficher toast erreur ou mettre le form en rouge
            showText.setText("Please enter a name");
        } else {
            try {
                this.ingredientFacade.updateIngredient(idIngredient, this.getNameIngredient(), this.imageIngredient, this.getAllergen());
                showText.setText("Ingredient updated");
            } catch (SQLException e) {
                // TODO : gérer exception
                showText.setText("Erreur lors de la modification de l'ingrédient");
            }
        }
    }

    public void showFormCreate() {
        // TODO : afficher le formulaire de création d'ingrédient
        // TODO : afficher le bouton de création d'ingrédient et le bouton cancel
    }

    public void showFormUpdate(Ingredient ingredientToUpdate) {
        // TODO : afficher le formulaire de modification d'ingrédient avec info pré-remplies
        // TODO : envoyer les modifs
        // TODO : mettre à jour la liste
            //getListView().getItems().set(getIndex(), newIngredient);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Ingredient> ingredients = FXCollections.observableArrayList(this.showList());

        ingredientList.setItems(ingredients);

        ingredientList.setCellFactory(param -> new ListCell<Ingredient>() {

            @Override
            protected void updateItem(Ingredient ingredient, boolean empty) {
                super.updateItem(ingredient, empty);

                if (empty || ingredient == null || ingredient.getName() == null) {
                    setText(null);
                } else {
                    HBox hBox = new HBox(5);
                    Label label = new Label(ingredient.getName());
                    ImageView imageView = new ImageView(ingredient.getImage());

                    Button deleteButton = new Button("Delete");
                    Button updateButton = new Button("Update");

                    deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            Ingredient ingredientToDelete = getItem();
                            // code de suppression de l'ingrédient de la ListView
                            deleteIngredient(ingredientToDelete.getId());
                        }
                    });

                    updateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            Ingredient ingredientToUpdate = getItem();
                            // code de modification de l'ingrédient de la ListView
                            showFormUpdate(ingredientToUpdate);
                        }
                    });

                    if (ingredient.getAllergen()) {
                        label.setStyle("-fx-text-fill: red");
                    }
                    hBox.getChildren().addAll(imageView, label, deleteButton, updateButton);
                    setGraphic(hBox);
                }
            }
        });
    }
}
