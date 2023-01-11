package com.cookingchef.controller;

import com.cookingchef.facade.CategoryFacade;
import com.cookingchef.facade.IngredientFacade;
import com.cookingchef.facade.RecipeFacade;
import com.cookingchef.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.swing.text.View;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeController implements Initializable {
    @FXML
    private ImageView imageView;

    @FXML
    private Label recipeName;

    @FXML
    private Text recipeDescription;

    @FXML
    private Text recipeSummary;

    @FXML
    private Text recipeServings;

    @FXML
    private ListView<Ingredient> ingredientListView = new ListView<Ingredient>();

    @FXML
    private ListView<Category> categoryListView = new ListView<Category>();

    @FXML
    private Button retourButton;
    private final IngredientFacade ingredientFacade;
    private final CategoryFacade categoryFacade;

    private final Recipe recipe;

    public RecipeController(Recipe recipe){
        this.recipe = recipe;
        this.ingredientFacade = IngredientFacade.getIngredientFacade();
        this.categoryFacade = CategoryFacade.getCategoryFacade();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.recipeName = new Label(recipe.getName());
        this.recipeDescription = new Text(recipe.getDescription());
        this.recipeSummary = new Text(recipe.getSummary());
        this.recipeServings = new Text(String.valueOf(recipe.getServings()));


        List<IngredientRecipe> ingredientsId = this.recipe.getListOfIngredients();
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        for (IngredientRecipe ingredientId : ingredientsId) {
            try {
                ingredients.add(this.ingredientFacade.getIngredientById(ingredientId.getIngredient()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        this.ingredientListView.getItems().addAll(ingredients);

        List<CategoryRecipe> categoryRecipes = this.recipe.getListofCategories();
        List<Category> categories = new ArrayList<Category>();
        for (CategoryRecipe categoryRecipe : categoryRecipes) {
            try {
                categories.add(this.categoryFacade.getCategoryRecipeById(categoryRecipe.getCategoryId()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        this.categoryListView.getItems().addAll(categories);

        this.imageView = new ImageView(new Image(new ByteArrayInputStream(recipe.getSrc())));

        categoryListView.setCellFactory(param -> this.listCellFactoryCategory());
        ingredientListView.setCellFactory(param -> this.listCellFactoryIngredient());
    }

    public ListCell<Category> listCellFactoryCategory() {
        return new ListCell<Category>() {

            @Override
            protected void updateItem(Category category, boolean empty) {
                super.updateItem(category, empty);

                if (empty || category == null || category.getNameCategory() == null) {
                    setText(null);
                } else {
                    VBox vBox = new VBox();
                    Label nameLabel = new Label(category.getNameCategory());
                    vBox.getChildren().addAll(nameLabel);
                    setGraphic(vBox);
                }
            }
        };
    }

    public ListCell<Ingredient> listCellFactoryIngredient() {
        return new ListCell<Ingredient>() {

            @Override
            protected void updateItem(Ingredient ingredient, boolean empty) {
                super.updateItem(ingredient, empty);

                if (empty || ingredient == null || ingredient.getName() == null) {
                    setText(null);
                } else {
                    VBox vBox = new VBox();
                    Label nameLabel = new Label(ingredient.getName());
                    vBox.getChildren().addAll(nameLabel);
                    setGraphic(vBox);
                }
            }
        };
    }
}