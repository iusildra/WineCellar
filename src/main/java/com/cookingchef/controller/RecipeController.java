package com.cookingchef.controller;

import com.cookingchef.facade.CategoryFacade;
import com.cookingchef.facade.IngredientFacade;
import com.cookingchef.model.*;
import com.cookingchef.view.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

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
    private ListView<Ingredient> ingredientListView = new ListView<>();

    @FXML
    private ListView<Category> categoryListView = new ListView<>();

    private Optional<Recipe> recipe = Optional.empty();

    @FXML
    private Button retourButton;
    private final IngredientFacade ingredientFacade = IngredientFacade.getIngredientFacade();
    private final CategoryFacade categoryFacade = CategoryFacade.getCategoryFacade();

    public RecipeController() {
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = Optional.of(recipe);
        this.recipeName = new Label(recipe.getName());
        this.recipeDescription = new Text(recipe.getDescription());
        this.recipeSummary = new Text(recipe.getSummary());
        this.recipeServings = new Text(String.valueOf(recipe.getServings()));

        if (recipe.getSrc() != null)
            this.imageView = new ImageView(new Image(new ByteArrayInputStream(recipe.getSrc())));
    }

    public void reset() {
        this.recipe = Optional.empty();
        this.recipeName.setText("");
        this.recipeDescription.setText("");
        this.recipeSummary.setText("");
        this.recipeServings.setText("");
        this.imageView.setImage(null);
    }

    private void fetchData() {
        List<IngredientRecipe> ingredientsId = this.recipe.get().getListOfIngredients();
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        for (IngredientRecipe ingredientId : ingredientsId) {
            try {
                ingredients.add(this.ingredientFacade.getIngredientById(ingredientId.getIngredient()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        this.ingredientListView.getItems().setAll(ingredients);
        this.ingredientListView.refresh();

        List<CategoryRecipe> categoryRecipes = this.recipe.get().getListofCategories();
        List<Category> categories = new ArrayList<Category>();
        for (CategoryRecipe categoryRecipe : categoryRecipes) {
            try {
                categories.add(this.categoryFacade.getCategoryRecipeById(categoryRecipe.getCategoryId()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        this.categoryListView.getItems().setAll(categories);
        this.categoryListView.refresh();
    }

    @FXML
    public void goBack() {
        try {
            Main.redirect(Page.HOME.value);
        } catch (IOException e) {
            Notifications.create()
                    .title("Error")
                    .text("Erreur lors du chargement de la page")
                    .showError();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryListView.setCellFactory(param -> this.listCellFactoryCategory());
        ingredientListView.setCellFactory(param -> this.listCellFactoryIngredient());

        this.recipe.ifPresent(rec -> fetchData());
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