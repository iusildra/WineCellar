package com.cookingchef.controller;

import com.cookingchef.facade.IngredientFacade;
import com.cookingchef.model.Ingredient;
import com.cookingchef.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientController {

    @FXML
    private Text showText;

    @FXML
    private TextField nameIngredient;

    @FXML
    private byte[] imageIngredient;

    @FXML
    private CheckBox allergen;


    public String getNameIngredient() {
        return nameIngredient.getText();
    }

    public Boolean getAllergen() {
        return allergen.isSelected();
    }




    private IngredientFacade ingredientFacade;

    public IngredientController() {
        this.ingredientFacade  = IngredientFacade.getIngredientFacade();
        showList();
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

    public void onClickCreateIngredient() {
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

    public void onClickDeleteIngredient() {
        try {
            // TODO : récupérer l'id de l'ingrédient à supprimer
            this.ingredientFacade.deleteIngredient(idIngredient);
            showText.setText("Ingredient deleted");
        } catch (SQLException e) {
            // TODO : gérer exception
            showText.setText("Erreur lors de la suppressionn de l'ingrédient");
        }
    }

    public void onClickUpdateIngredient() {
        try {
            // TODO : récupérer l'id de l'ingrédient à modifier
            this.ingredientFacade.updateIngredient(idIngredient, this.getNameIngredient(), this.imageIngredient, this.getAllergen());
            showText.setText("Ingredient updated");
        } catch (SQLException e) {
            // TODO : gérer exception
            showText.setText("Erreur lors de la modification de l'ingrédient");
        }
    }

}
