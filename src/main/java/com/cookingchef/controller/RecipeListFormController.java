package com.cookingchef.controller;

import com.cookingchef.facade.RecipeListFacade;
import com.cookingchef.model.RecipeList;
import com.cookingchef.view.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;
import java.util.Optional;

public class RecipeListFormController {
    @FXML
    private TextField formName;

    @FXML
    private Button closeButton;

    private Optional<Integer> recipeListId = Optional.empty();

    private Runnable callback;

    @FXML
    protected void onClickValidateButton() {
        RecipeListFacade recipeListFacade = RecipeListFacade.getRecipeListFacade();
        var recipeList = new RecipeList(this.recipeListId.get(), this.formName.getText());
        if (Main.getUser() == null) {
            Notifications.create().title("Error").text("You must be logged in to create a recipe list.").showError();
            return;
        }

        if (recipeList.getId().isEmpty()) {
            try {
                recipeListFacade.addRecipeList(Main.getUser().getId().get(), recipeList);
            } catch (SQLException e) {
                Notifications.create()
                        .title("Creation failed")
                        .text("Recipe list creation failed, please refer to an admin.")
                        .showError();
                return;
            }
        }
        callback.run();
        this.onClose();
    }

    public void reset() {
        this.recipeListId = Optional.empty();
    }

    public void fillInputs(RecipeList recipeList) {
        this.recipeListId = recipeList.getId();
        this.formName.setText(recipeList.getName());
    }

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    @FXML
    protected void onClose() {
        var stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
