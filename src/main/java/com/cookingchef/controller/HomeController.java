package com.cookingchef.controller;

import com.cookingchef.facade.CategoryFacade;
import com.cookingchef.facade.IngredientFacade;
import com.cookingchef.facade.RecipeFacade;
import com.cookingchef.model.CategorySearch;
import com.cookingchef.model.Recipe;
import com.cookingchef.view.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The type Home controller.
 */
public class HomeController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<CategorySearch> comboBox;

    @FXML
    private ListView<Recipe> listeRecipe;

    private RecipeFacade recipeFacade = RecipeFacade.getRecipeFacade();

    private static final String ERROR_TITLE = "Erreur";

    private void getRecettes() {
        try {
            this.listeRecipe.setItems(FXCollections.observableArrayList(this.recipeFacade.getAllRecipes()));
            this.listeRecipe.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            Notifications.create()
                    .title(ERROR_TITLE)
                    .text("Erreur avec la base de donnée\nRelancer l'application")
                    .showError();
        }
    }

    /**
     * On click search.
     */
    public void onClickSearch() {
        String search = searchField.getText();
        CategorySearch category = comboBox.getValue();

        if (search.isEmpty()) {
            try {
                this.listeRecipe.setItems(FXCollections.observableArrayList(this.recipeFacade.getAllRecipes()));
            } catch (SQLException e) {
                e.printStackTrace();
                Notifications.create()
                        .title(ERROR_TITLE)
                        .text("Erreur avec la base de donnée\nRelancer l'application")
                        .showError();
            }
        } else {
            switch (category) {
                case INGREDIENT -> {
                    List<String> ingredients = List.of(search.split(", "));
                    try {
                        List<Integer> ingredientsId = IngredientFacade.getIngredientFacade()
                                .getIngredientsIdByNames(ingredients);
                        this.listeRecipe.setItems(FXCollections
                                .observableArrayList(this.recipeFacade.getRecipesByIngredients(ingredientsId)));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Notifications.create()
                                .title(ERROR_TITLE)
                                .text("Erreur avec la base de donnée\nPenser à séparer vos ingrédient d'une virgule suivie d'un espace")
                                .showError();
                    }
                }
                case CATEGORY -> {
                    List<String> categories = List.of(search.split(", "));
                    try {
                        List<Integer> categoriesId = CategoryFacade.getCategoryFacade()
                                .getCategoriesIdByNames(categories);
                        this.listeRecipe.setItems(FXCollections
                                .observableArrayList(this.recipeFacade.getRecipesByCategories(categoriesId)));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Notifications.create()
                                .title(ERROR_TITLE)
                                .text("Erreur avec la base de donnée\nPenser à séparer vos catégories d'une virgule suivie d'un espace")
                                .showError();
                    }

                }
                case RECIPE -> {
                    try {
                        this.listeRecipe.setItems(
                                FXCollections.observableArrayList(this.recipeFacade.getRecipesByName(search)));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Notifications.create()
                                .title(ERROR_TITLE)
                                .text("Erreur avec la base de donnée\nRelancer l'application")
                                .showError();
                    }
                }
            }
        }
        this.listeRecipe.refresh();
    }

    /**
     * On click clear.
     */
    public void onClickClear() {
        this.searchField.clear();
        this.getRecettes();
    }

    /**
     * Redirect to.
     *
     * @param recetteToGo the recette to go
     */
    public void redirectTo(Recipe recetteToGo) {
        this.recipeFacade.setRecipeChoice(recetteToGo);
        try {
            Main.redirect("recipe");
        } catch (IOException e) {
            e.printStackTrace();
            Notifications.create()
                    .title(ERROR_TITLE)
                    .text("Erreur avec la redirection\nRelancer l'application")
                    .showError();
        }
        /*// code de redirection vers la recette
        RecipeController controller;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("recipes/recipe-view.fxml"));
        try {
            loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        if (recetteToGo != null)
            controller.setRecipe(recetteToGo);

        try {
            Main.redirect("recipe");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.listeRecipe.setCellFactory(param -> cellFactory());
        this.comboBox.getItems().setAll(CategorySearch.INGREDIENT, CategorySearch.RECIPE, CategorySearch.CATEGORY);
        this.comboBox.setValue(CategorySearch.RECIPE);
        this.getRecettes();
    }

    /**
     * Cell factory list cell.
     *
     * @return the list cell
     */
    public ListCell<Recipe> cellFactory() {
        return new ListCell<>() {

            @Override
            protected void updateItem(Recipe recipe, boolean empty) {
                super.updateItem(recipe, empty);

                if (empty || recipe == null || recipe.getName() == null) {
                    setText(null);
                    return;
                }

                VBox vBox = new VBox();

                Label label = new Label(recipe.getName());

                /*
                 * Récup plus tard l'image de la recette et l'afficher
                 * ImageView imageView = new ImageView(new Image(new
                 * ByteArrayInputStream(recette.getImage())));
                 * imageView.setFitHeight(50);
                 * imageView.setFitWidth(50);
                 */

                Button checkButton = new Button("Voir");
                checkButton.setOnAction(actionEvent -> redirectTo(getItem()));

                HBox hBox = new HBox(label, checkButton);
                hBox.setSpacing(10);
                hBox.setPadding(new Insets(10));
                hBox.setAlignment(Pos.CENTER);

                // Si implémenter ajouter imageView dans les paramètres à addAll()
                vBox.getChildren().addAll(hBox);
                setGraphic(vBox);
            }
        };
    }
}