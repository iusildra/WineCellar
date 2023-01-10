package com.cookingchef.controller;

import com.cookingchef.facade.CategoryFacade;
import com.cookingchef.facade.IngredientFacade;
import com.cookingchef.facade.RecipeFacade;
import com.cookingchef.model.CategorySearch;
import com.cookingchef.model.Recipe;
import com.cookingchef.view.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class HomeController implements Initializable {

    @FXML
    private final TextField searchField = new TextField();


    @FXML
    private final ComboBox<CategorySearch> comboBox = new ComboBox<>();


    @FXML
    private final ListView<Recipe> listeRecipe = new ListView<>();

    private final RecipeFacade recipeFacade = RecipeFacade.getRecipeFacade();

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
                        List<Integer> ingredientsId = IngredientFacade.getIngredientFacade().getIngredientsIdByNames(ingredients);
                        this.listeRecipe.setItems(FXCollections.observableArrayList(this.recipeFacade.getRecipesByIngredients(ingredientsId)));
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
                        List<Integer> categoriesId = CategoryFacade.getCategoryFacade().getCategoriesIdByNames(categories);
                        this.listeRecipe.setItems(FXCollections.observableArrayList(this.recipeFacade.getRecipesByCategories(categoriesId)));
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
                        this.listeRecipe.setItems(FXCollections.observableArrayList(this.recipeFacade.getRecipesByName(search)));
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

    public void onClickClear() {
        this.searchField.clear();
        this.getRecettes();
    }


    public void redirectTo(Recipe recetteToGo) {
        // code de redirection vers la recette
        RecipeController controleur = new RecipeController(recetteToGo);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("recipe-view.fxml"));
        loader.setController(controleur);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.addScene("recette", loader.getLocation());
        try {
            Main.redirect("recette");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.listeRecipe.setCellFactory(param -> cellFactory());
        this.getRecettes();
        this.comboBox.getItems().setAll(CategorySearch.INGREDIENT, CategorySearch.RECIPE, CategorySearch.CATEGORY);
        this.comboBox.setValue(CategorySearch.RECIPE);

    }

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

                /* Récup plus tard l'image de la recette et l'afficher
                    ImageView imageView = new ImageView(new Image(new ByteArrayInputStream(recette.getImage())));
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                 */

                Button checkButton = new Button("Voir");
                checkButton.setOnAction(actionEvent -> {
                    Recipe recetteToGo = getItem();
                    // code de redirection vers la recette
                    redirectTo(recetteToGo);
                });

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
