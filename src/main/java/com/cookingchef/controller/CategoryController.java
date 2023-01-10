package com.cookingchef.controller;

import com.cookingchef.facade.CategoryFacade;
import com.cookingchef.model.Category;
import com.cookingchef.view.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private ComboBox chocoBox;

    @FXML
    private TextField nameCategory;

    @FXML
    private Button cancelButton;

    @FXML
    private Button validateButton;
    @FXML
    private ListView<Pair<String, Category>> categoriesList = new ListView<>();

    @FXML
    private Stage secondaryStage;

    private final CategoryFacade categoryFacade;

    public CategoryController() {
        this.categoryFacade = CategoryFacade.getCategoryFacade();
    }

    public ArrayList<Pair<String, Category>> showList() {

        ArrayList<Pair<String, Category>> categories;

        try {
            categories = this.categoryFacade.getAllCategories();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if (categories == null) {
            Notifications.create()
                    .title("Erreur")
                    .text("Erreur avec la base de donnée\nRelancer l'application")
                    .showError();
        }
        return categories;
    }

    public void deleteCategory(String tableCategory, int idCategory) {
        try {
            this.categoryFacade.deleteCategory(tableCategory, idCategory);

            this.categoriesList.getItems().remove(idCategory);
            Notifications.create()
                    .title("Succès")
                    .text("La catégorie a été supprimée avec succès")
                    .showConfirm();
        } catch (SQLException e) {
            Notifications.create()
                    .title("Erreur")
                    .text("Erreur lors de la suppression")
                    .showError();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void showFormCreate() {
        // Afficher le formulaire général de create Ingredient
        this.showForm();

        // Définir le bouton valider pour le create form
        this.validateButton.setOnAction(actionEvent -> {

            if (this.nameCategory.getText().isEmpty()) {
                Notifications.create()
                        .title("Information")
                        .text("Veuillez remplir le nom de la catégorie")
                        .showWarning();
                return;
            }

            String tableToUpdate;
            switch ((String) this.chocoBox.getValue()) {
                case "Suggestion":
                    tableToUpdate = "suggestion_category";
                    break;
                case "Recette":
                    tableToUpdate = "recipe_category";
                    break;
                default:
                    tableToUpdate = "ingredient_category";
                    break;
            }

            if (this.createCategory(tableToUpdate, this.nameCategory.getText())) {
                this.secondaryStage.close();
                Notifications.create()
                        .title("Succès")
                        .text("La catégorie a été créé avec succès")
                        .showConfirm();
                this.showList();
            } else {
                Notifications.create()
                        .title("Erreur")
                        .text("Nom déjà utilisé")
                        .showWarning();
            }
        });
    }

    private boolean createCategory(String tableCategory, String text) {
        try {
            return this.categoryFacade.createCategory(tableCategory, text);
        } catch (SQLException e) {
            Notifications.create()
                    .title("Erreur")
                    .text("Erreur lors de la création")
                    .showError();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void showFormUpdate(String tableCategory, Category categoryToUpdate) {
        // Afficher le formulaire général du update form
        this.showForm();

        // Remplir avec les informations existantes
        this.chocoBox.setValue(tableCategory);
        this.nameCategory.setText(categoryToUpdate.getNameCategory());

        // Définir le bouton valider pour le update form
        this.validateButton.setOnAction(actionEvent -> {

            if (this.nameCategory.getText().isEmpty()) {
                Notifications.create()
                        .title("Information")
                        .text("Veuillez remplir le nom de la catégorie")
                        .showWarning();
                return;
            }

            String tableToUpdate;
            switch ((String) this.chocoBox.getValue()) {
                case "Suggestion":
                    tableToUpdate = "suggestion_category";
                    break;
                case "Recette":
                    tableToUpdate = "recipe_category";
                    break;
                default:
                    tableToUpdate = "ingredient_category";
                    break;
            }

            if (this.updateCategory(tableToUpdate, categoryToUpdate.getIdCategory(), this.nameCategory.getText())) {
                this.secondaryStage.close();
                Notifications.create()
                        .title("Sucess")
                        .text("La catégorie a été mise à jour avec succès")
                        .showConfirm();
                this.showList();
            } else {
                Notifications.create()
                        .title("Erreur")
                        .text("Nom déjà utilisé")
                        .showWarning();
            }
        });
    }

    private boolean updateCategory(String tableCategory, int idCategory, String text) {
        try {
            return this.categoryFacade.updateCategory(tableCategory, idCategory, text);
        } catch (SQLException e) {
            Notifications.create()
                    .title("Erreur")
                    .text("Erreur lors de la modification")
                    .showError();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void showForm() {
        // Création de la seconde fenêtre
        this.secondaryStage = new Stage();
        secondaryStage.initModality(Modality.WINDOW_MODAL);
        secondaryStage.initOwner(Main.stage);

        // Création du layout
        Label label = new Label("Nom de la catégorie");
        this.chocoBox = new ComboBox();
        this.chocoBox.getItems().addAll("Ingrédient", "Recette", "Suggestion");
        this.chocoBox.setValue("Ingrédient");

        Label labelName = new Label("Entrer le nom de la catégorie :");
        this.nameCategory = new TextField();


        this.cancelButton = new Button("Annuler");
        this.cancelButton.setOnAction(actionEvent -> secondaryStage.close());

        this.validateButton = new Button("Valider");

        HBox first = new HBox(label, chocoBox);
        first.setSpacing(10);
        first.setPadding(new Insets(10));
        first.setAlignment(Pos.CENTER);

        HBox second = new HBox(labelName, this.nameCategory);
        second.setSpacing(10);
        second.setPadding(new Insets(10));
        second.setAlignment(Pos.CENTER);


        HBox third = new HBox(this.cancelButton, this.validateButton);
        third.setSpacing(10);
        third.setPadding(new Insets(10));
        third.setAlignment(Pos.CENTER);

        VBox root = new VBox(first, second, third);
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 500, 400);
        this.secondaryStage.setScene(scene);
        this.secondaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Pair<String, Category>> categories = FXCollections.observableArrayList(this.showList());

        categoriesList.setItems(categories);

        categoriesList.setCellFactory(param -> new ListCell<>() {

            @Override
            protected void updateItem(Pair<String, Category> category, boolean empty) {
                super.updateItem(category, empty);

                if (empty || category == null || category.getValue().getNameCategory() == null) {
                    setText(null);
                } else {
                    VBox vBox = new VBox();
                    Label label = new Label(category.getKey() + " : " + category.getValue().getNameCategory());

                    Button deleteButton = new Button("Supprimer");
                    deleteButton.setOnAction(actionEvent -> {
                        Category categoryToDelete = getItem().getValue();
                        // code de suppression de l'ingrédient de la ListView
                        deleteCategory(getItem().getKey(), categoryToDelete.getIdCategory());
                    });

                    Button updateButton = new Button("Modifer");
                    updateButton.setOnAction(actionEvent -> {
                        Category categoryToUpdate = getItem().getValue();
                        // code de modification de l'ingrédient de la ListView
                        showFormUpdate(getItem().getKey(), categoryToUpdate);
                    });

                    HBox hBox = new HBox(deleteButton, updateButton);
                    hBox.setSpacing(10);
                    hBox.setPadding(new Insets(10));
                    hBox.setAlignment(Pos.CENTER);

                    vBox.getChildren().addAll(label, hBox);
                    setGraphic(vBox);
                }
            }
        });
    }
}