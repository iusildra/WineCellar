package com.cookingchef.controller;

import com.cookingchef.facade.CategoryFacade;
import com.cookingchef.model.Category;
import com.cookingchef.model.CategoryDb;
import com.cookingchef.view.Main;
import javafx.collections.FXCollections;
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
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private ComboBox<CategoryDb> chocoBox = new ComboBox<>();

    @FXML
    private TextField nameCategory;

    @FXML
    private Button cancelButton;

    @FXML
    private Button validateButton;
    @FXML
    private ListView<Pair<CategoryDb, Category>> categoriesList = new ListView<>();

    @FXML
    private Stage secondaryStage;

    private final CategoryFacade categoryFacade;

    private static final String ERROR_TITLE = "Erreur";
    private static final String SUCCESS_TITLE = "Success";

    public CategoryController() {
        this.categoryFacade = CategoryFacade.getCategoryFacade();
    }

    public void fetchList() {
        try {
            this.categoriesList.setItems(FXCollections.observableArrayList(this.categoryFacade.getAllCategories()));
            this.categoriesList.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            Notifications.create()
                    .title(ERROR_TITLE)
                    .text("Erreur avec la base de donnée\nRelancer l'application")
                    .showError();
        }

    }

    public void deleteCategory(CategoryDb tableCategory, Category category) {
        try {
            this.categoryFacade.deleteCategory(tableCategory, category.getIdCategory());

            this.categoriesList.getItems().remove(new Pair<CategoryDb, Category>(tableCategory, category));
            Notifications.create()
                    .title("Succès")
                    .text("La catégorie a été supprimée avec succès")
                    .showConfirm();
        } catch (SQLException e) {
            Notifications.create()
                    .title(ERROR_TITLE)
                    .text("Erreur lors de la suppression")
                    .showError();
            e.printStackTrace();
        }
    }

    public void showFormCreate() {
        this.chocoBox.setDisable(false);
        this.showForm();

        this.validateButton.setOnAction(actionEvent -> {

            if (this.nameCategory.getText().isEmpty()) {
                Notifications.create()
                        .title("Information")
                        .text("Veuillez remplir le nom de la catégorie")
                        .showWarning();
                return;
            }

            if (this.createCategory(this.chocoBox.getValue(), this.nameCategory.getText())) {
                this.secondaryStage.close();
                Notifications.create()
                        .title("Succès")
                        .text("La catégorie a été créé avec succès")
                        .showConfirm();
                this.fetchList();
            } else {
                Notifications.create()
                        .title(ERROR_TITLE)
                        .text("Nom déjà utilisé")
                        .showWarning();
            }
        });
    }

    private boolean createCategory(CategoryDb tableCategory, String text) {
        try {
            return this.categoryFacade.createCategory(tableCategory, text);
        } catch (SQLException e) {
            Notifications.create()
                    .title(ERROR_TITLE)
                    .text("Erreur lors de la création")
                    .showError();
            e.printStackTrace();
            return false;
        }
    }

    public void showFormUpdate(CategoryDb tableCategory, Category categoryToUpdate) {
        this.showForm();

        this.chocoBox.setValue(tableCategory);
        this.chocoBox.setDisable(true);
        this.nameCategory.setText(categoryToUpdate.getNameCategory());

        this.validateButton.setOnAction(actionEvent -> {

            if (this.nameCategory.getText().isEmpty()) {
                Notifications.create()
                        .title("Information")
                        .text("Veuillez remplir le nom de la catégorie")
                        .showWarning();
                return;
            }

            if (this.updateCategory(this.chocoBox.getValue(), categoryToUpdate.getIdCategory(), this.nameCategory.getText())) {
                this.secondaryStage.close();
                Notifications.create()
                        .title(SUCCESS_TITLE)
                        .text("La catégorie a été mise à jour avec succès")
                        .showConfirm();
                this.fetchList();
            } else {
                Notifications.create()
                        .title(ERROR_TITLE)
                        .text("Nom déjà utilisé")
                        .showWarning();
            }
        });
    }

    private boolean updateCategory(CategoryDb tableCategory, int idCategory, String text) {
        try {
            return this.categoryFacade.updateCategory(tableCategory, idCategory, text);
        } catch (SQLException e) {
            Notifications.create()
                    .title(ERROR_TITLE)
                    .text("Erreur lors de la modification")
                    .showError();
            e.printStackTrace();
            return false;
        }
    }

    private void showForm() {
        this.secondaryStage = new Stage();
        secondaryStage.initModality(Modality.WINDOW_MODAL);
        secondaryStage.initOwner(Main.stage);

        Label label = new Label("Nom de la catégorie");
        this.chocoBox.setValue(CategoryDb.INGREDIENT);

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
    public void initialize(URL location, ResourceBundle resources) {
        categoriesList.setCellFactory(param -> cellFactory());
        fetchList();
        this.chocoBox.getItems().setAll(CategoryDb.INGREDIENT, CategoryDb.RECIPE, CategoryDb.SUGGESTION);
    }

    public ListCell<Pair<CategoryDb, Category>> cellFactory() {
        return new ListCell<Pair<CategoryDb, Category>>() {

            @Override
            protected void updateItem(Pair<CategoryDb, Category> category, boolean empty) {
                super.updateItem(category, empty);

                if (empty || category == null || category.getValue().getNameCategory() == null) {
                    setText(null);
                    return;
                }

                VBox vBox = new VBox();
                Button deleteButton = new Button("Supprimer");
                deleteButton.setOnAction(actionEvent -> {
                    Category categoryToDelete = getItem().getValue();
                    deleteCategory(getItem().getKey(), categoryToDelete);
                });

                Button updateButton = new Button("Modifer");
                updateButton.setOnAction(actionEvent -> {
                    Category categoryToUpdate = getItem().getValue();
                    showFormUpdate(getItem().getKey(), categoryToUpdate);
                });

                HBox hBox = new HBox(deleteButton, updateButton);
                hBox.setSpacing(10);
                hBox.setPadding(new Insets(10));
                hBox.setAlignment(Pos.CENTER);

                vBox.getChildren()
                        .addAll(new Label(category.getKey() + " : " + category.getValue().getNameCategory()), hBox);
                setGraphic(vBox);
            }
        };
    }
}