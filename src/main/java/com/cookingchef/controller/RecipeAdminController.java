package com.cookingchef.controller;

import com.cookingchef.facade.CategoryFacade;
import com.cookingchef.facade.IngredientFacade;
import com.cookingchef.facade.RecipeFacade;
import com.cookingchef.model.*;
import com.cookingchef.view.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.Notifications;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeAdminController implements Initializable {
    @FXML
    private TextField name;

    @FXML
    private TextArea description;

    @FXML
    private TextField summary;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField servings;

    @FXML
    private CheckComboBox<Category> categoryRecipeCheckComboBox;

    @FXML
    private HBox ingredientHBox;

    @FXML
    private Button addIngredientButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button validateButton;

    @FXML
    private Button addImage;

    @FXML
    private Stage secondaryStage;

    @FXML
    private ListView<Recipe> recipeListView = new ListView<>();

    @FXML
    private ListView<Ingredient> ingredientListView = new ListView<>();

    private final RecipeFacade recipeFacade;
    private final IngredientFacade ingredientFacade;
    private final CategoryFacade categoryFacade;

    public RecipeAdminController(){
        this.recipeFacade = RecipeFacade.getRecipeFacade();
        this.ingredientFacade = IngredientFacade.getIngredientFacade();
        this.categoryFacade = CategoryFacade.getCategoryFacade();
    }

    public List<Recipe> showList(){
        List<Recipe> recipes;

        try {
            recipes = this.recipeFacade.getAllRecipes();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if (recipes == null) {
            Notifications.create()
                    .title("Erreur")
                    .text("Erreur avec la base de donnée\nRelancer l'application")
                    .showError();
        }
        return recipes;
    }

    public void deleteRecipe(Recipe recipe) {
        try {
            this.recipeFacade.deleteRecipe(recipe);

            this.recipeListView.getItems().remove(recipe);
            Notifications.create()
                    .title("Succès")
                    .text("La recette a été supprimée avec succès")
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

    public void validateForm(){
        if (this.name.getText().isEmpty()) {
            Notifications.create()
                    .title("Information")
                    .text("Veuillez remplir le nom de la recette")
                    .showWarning();

        }

        if (this.description.getText().isEmpty()) {
            Notifications.create()
                    .title("Information")
                    .text("Veuillez remplir la description de la recette")
                    .showWarning();

        }

        if (this.summary.getText().isEmpty()) {
            Notifications.create()
                    .title("Information")
                    .text("Veuillez remplir le résumé de la recette")
                    .showWarning();

        }

        if (this.servings.getText().matches("[0-9]+")){
            Notifications.create()
                    .title("Information")
                    .text("Veuillez remplir le nombre de personnes pour la recette")
                    .showWarning();

        }
    }

    private byte[] addImageRecipe(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                return Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private byte[] recupImage() {
        // Récupérer l'image séléctionnée
        PixelReader pixelReader = imageView.getImage().getPixelReader();
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bImage.setRGB(x, y, pixelReader.getArgb(x, y));
            }
        }

        byte[] imageData = new byte[0];
        try {
            File tempFile = File.createTempFile("image", ".tmp");
            ImageIO.write(bImage, "png", tempFile);

            imageData = Files.readAllBytes(tempFile.toPath());
            tempFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageData;
    }

    public void showFormCreate() {
        // Afficher le formulaire général de create Ingredient
        this.showForm();

        // Définir le bouton valider pour le create form
        this.validateButton.setOnAction(actionEvent -> {

            this.validateForm();

            //Liste ingrédient
            ArrayList<IngredientRecipe> ingredients = new ArrayList<>();
            ObservableList<Node> childrens = this.ingredientHBox.getChildren();
            for(Node children : childrens){
                if (children.getClass() == HBox.class){
                    int quantity = 0;
                    int unit = 0;
                    int ingredientId =0;
                    HBox hBox = (HBox) children;
                    ObservableList<Node> childrensHBox = hBox.getChildren();
                    for(Node childrenHBox : childrensHBox){
                        if(childrenHBox.getClass() == TextField.class){
                            TextField textField = (TextField) childrenHBox;
                            if(textField.getId().equals("quantity")){
                                quantity = Integer.parseInt(textField.getText());
                            }
                            if(textField.getId().equals("unit")){
                                unit = Integer.parseInt(textField.getText());
                            }
                        }
                        if(childrenHBox.getClass() == ComboBox.class){
                            ComboBox comboBox = (ComboBox) childrenHBox;
                            ingredientId = ((Ingredient) comboBox.getValue()).getId();
                        }
                    }
                    if (quantity != 0 && unit != 0 && ingredientId != 0){
                        IngredientRecipe ingredientRecipe = new IngredientRecipe(quantity, unit, ingredientId);
                        ingredients.add(ingredientRecipe);
                    }
                }
            }


            Recipe recipe = new Recipe(
                    this.name.getText(),
                    this.description.getText(),
                    this.summary.getText(),
                    this.recupImage(),
                    Integer.parseInt(this.servings.getText()),
                    ingredients,
                    this.transformCategoryIntoCategoryRecipe(this.categoryRecipeCheckComboBox.getCheckModel().getCheckedItems())
            );

            this.createRecipe(recipe);
        });
    }

    private void createRecipe(Recipe recipe) {
        try {
            this.recipeFacade.addRecipe(recipe);
            this.secondaryStage.close();
            Notifications.create()
                    .title("Succès")
                    .text("La recette a été créé avec succès")
                    .showConfirm();
            this.showList();
        } catch (SQLException e) {
            Notifications.create()
                    .title("Erreur")
                    .text("Erreur lors de la création")
                    .showError();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ArrayList<CategoryRecipe> transformCategoryIntoCategoryRecipe(List<Category> categories){
        ArrayList<CategoryRecipe> categoryRecipes = new ArrayList<CategoryRecipe>();
        for (Category category : categories) {
            CategoryRecipe categoryRecipe = new CategoryRecipe(
                    category.getIdCategory()
            );
            categoryRecipes.add(categoryRecipe);
        }
        return categoryRecipes;
    }

    public void showFormUpdate(Recipe recipe) {
        this.showForm();

        // Remplir avec les informations existantes
        this.name.setText(recipe.getName());
        this.description.setText(recipe.getDescription());
        this.summary.setText(recipe.getSummary());
        this.imageView.setImage(new Image(new ByteArrayInputStream(recipe.getSrc())));
        this.servings.setText(String.valueOf(recipe.getServings()));


        /*List<IngredientRecipe> ingredientRecipes = recipe.getListOfIngredients();
        for (IngredientRecipe ingredientRecipe : ingredientRecipes) {
            try {
                this.ingredientRecipeCheckComboBox.getCheckModel().check(ingredientFacade.getIngredientById(ingredientRecipe.getIngredient()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }*/

        List<CategoryRecipe> categoryRecipes = recipe.getListofCategories();
        for (CategoryRecipe categoryRecipe : categoryRecipes) {
            try {
                this.categoryRecipeCheckComboBox.getCheckModel().check(categoryFacade.getCategoryRecipeById(categoryRecipe.getCategoryId()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        this.validateButton.setOnAction(actionEvent -> {

            this.validateForm();

            //Liste ingrédient
            ArrayList<IngredientRecipe> ingredients = new ArrayList<>();
            ObservableList<Node> childrens = this.ingredientHBox.getChildren();
            for(Node children : childrens){
                if (children.getClass() == HBox.class){
                    int quantity = 0;
                    int unit = 0;
                    int ingredientId =0;
                    HBox hBox = (HBox) children;
                    ObservableList<Node> childrensHBox = hBox.getChildren();
                    for(Node childrenHBox : childrensHBox){
                        if(childrenHBox.getClass() == TextField.class){
                            TextField textField = (TextField) childrenHBox;
                            if(textField.getId().equals("quantity")){
                                quantity = Integer.parseInt(textField.getText());
                            }
                            if(textField.getId().equals("unit")){
                                unit = Integer.parseInt(textField.getText());
                            }
                        }
                        if(childrenHBox.getClass() == ComboBox.class){
                            ComboBox comboBox = (ComboBox) childrenHBox;
                            ingredientId = ((Ingredient) comboBox.getValue()).getId();
                        }
                    }
                    if (quantity != 0 && unit != 0 && ingredientId != 0){
                        IngredientRecipe ingredientRecipe = new IngredientRecipe(quantity, unit, ingredientId);
                        ingredients.add(ingredientRecipe);
                    }
                }
            }


            Recipe recipeToUpdate = new Recipe(
                    this.name.getText(),
                    this.description.getText(),
                    this.summary.getText(),
                    this.recupImage(),
                    Integer.parseInt(this.servings.getText()),
                    ingredients,
                    this.transformCategoryIntoCategoryRecipe(this.categoryRecipeCheckComboBox.getCheckModel().getCheckedItems())
            );

            this.updateCategory(recipeToUpdate);
        });
    }

    private void updateCategory(Recipe recipeToUpdate) {
        try {
            this.recipeFacade.updateRecipe(recipeToUpdate);
            this.secondaryStage.close();
            Notifications.create()
                    .title("Sucess")
                    .text("La catégorie a été mise à jour avec succès")
                    .showConfirm();
            this.showList();
        } catch (SQLException e) {
            Notifications.create()
                    .title("Erreur")
                    .text("Erreur lors de la modification")
                    .showError();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void createHBoxIngredient(){
        Label labelIngredient = new Label("Ingrédients");
        ComboBox<Ingredient> ingredientRecipeComboBox = new ComboBox<>();
        try {
            ingredientRecipeComboBox.getItems().addAll(this.ingredientFacade.getAllIngredients());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Label labelQuantity = new Label("Quantité");
        TextField quantity = new TextField();

        Label labelUnit = new Label("Unité");
        TextField unit = new TextField();

        HBox five2 = new HBox(labelIngredient, ingredientRecipeComboBox, labelQuantity, quantity, labelUnit, unit);
        five2.setSpacing(10);
        five2.setPadding(new Insets(10));
        five2.setAlignment(Pos.CENTER);

        this.ingredientHBox.getChildren().add(five2);
    }

    private void showForm() {
        // Création de la seconde fenêtre
        this.secondaryStage = new Stage();
        secondaryStage.initModality(Modality.WINDOW_MODAL);
        secondaryStage.initOwner(Main.getStage());

        Label labelName = new Label("Nom de la recette");
        this.name = new TextField();

        Label labelDescription = new Label("Description de la recette");
        this.description = new TextArea();

        Label labelSummary = new Label("Résumé de la recette");
        this.summary = new TextField();

        Label labelImage = new Label("Image de la recette");
        this.imageView = new ImageView();
        this.imageView.setFitHeight(150);
        this.imageView.setFitWidth(188.0);
        this.imageView.setPickOnBounds(true);
        this.imageView.setPreserveRatio(true);
        this.addImage = new Button("Importer image");
        addImage.setOnAction(actionEvent -> {
            byte[] bufferImage = this.addImageRecipe(this.secondaryStage);
            if (bufferImage != null) {
                this.imageView.setImage(new Image(new ByteArrayInputStream(bufferImage)));
            } else {
                Notifications.create()
                        .title("Information")
                        .text("Image inchangée")
                        .showInformation();
            }
        });

        Label labelServings = new Label("Nombre de personnes");
        this.servings = new TextField();

        Label labelIngredient = new Label("Ingrédients");
        ComboBox<Ingredient> ingredientRecipeComboBox = new ComboBox<>();
        try {
            ingredientRecipeComboBox.getItems().addAll(this.ingredientFacade.getAllIngredients());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Label labelQuantity = new Label("Quantité");
        TextField quantity = new TextField();

        Label labelUnit = new Label("Unité");
        TextField unit = new TextField();

        this.addIngredientButton = new Button("Ajouter un ingrédient");
        this.addIngredientButton.setOnAction(actionEvent -> createHBoxIngredient());

        Label labelCategory = new Label("Catégories");
        this.categoryRecipeCheckComboBox = new CheckComboBox<Category>();
        ArrayList<Category> categories = new ArrayList<Category>();
        List<Pair<CategoryDb, Category>> categoriesDb = null;
        try {
            categoriesDb = new ArrayList<Pair<CategoryDb, Category>>(this.categoryFacade.getAllCategories());
            for (Pair<CategoryDb, Category>  category : categoriesDb) {
                categories.add(category.getValue());
            }
            this.categoryRecipeCheckComboBox.getItems().addAll(categories);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.cancelButton = new Button("Annuler");
        this.cancelButton.setOnAction(actionEvent -> secondaryStage.close());

        this.validateButton = new Button("Valider");

        HBox first = new HBox(labelName, this.name);
        first.setSpacing(10);
        first.setPadding(new Insets(10));
        first.setAlignment(Pos.CENTER);

        HBox second = new HBox(labelSummary, this.summary);
        second.setSpacing(10);
        second.setPadding(new Insets(10));
        second.setAlignment(Pos.CENTER);

        HBox third = new HBox(labelServings, this.servings);
        third.setSpacing(10);
        third.setPadding(new Insets(10));
        third.setAlignment(Pos.CENTER);

        HBox fourth = new HBox(labelCategory, this.categoryRecipeCheckComboBox);
        fourth.setSpacing(10);
        fourth.setPadding(new Insets(10));
        fourth.setAlignment(Pos.CENTER);

        HBox five2 = new HBox(labelIngredient, ingredientRecipeComboBox, labelQuantity, quantity, labelUnit, unit);
        five2.setSpacing(10);
        five2.setPadding(new Insets(10));
        five2.setAlignment(Pos.CENTER);

        this.ingredientHBox = new HBox(five2);
        ingredientHBox.setSpacing(10);
        ingredientHBox.setPadding(new Insets(10));
        ingredientHBox.setAlignment(Pos.CENTER);

        HBox five = new HBox(this.ingredientHBox);
        five.setSpacing(10);
        five.setPadding(new Insets(10));
        five.setAlignment(Pos.CENTER);

        HBox six = new HBox(labelDescription, this.description);
        six.setSpacing(10);
        six.setPadding(new Insets(10));
        six.setAlignment(Pos.CENTER);

        HBox seven = new HBox(labelImage, this.imageView, this.addImage);
        seven.setSpacing(10);
        seven.setPadding(new Insets(10));
        seven.setAlignment(Pos.CENTER);

        HBox eight = new HBox(this.cancelButton, this.validateButton);
        eight.setSpacing(10);
        eight.setPadding(new Insets(10));
        eight.setAlignment(Pos.CENTER);

        VBox root = new VBox(first, second, third, fourth, five, six, seven, eight);
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 500, 400);
        this.secondaryStage.setScene(scene);
        this.secondaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recipeListView.setCellFactory(param -> this.listCellFactory());
        this.showList();
    }

    public ListCell<Recipe> listCellFactory() {
        return new ListCell<Recipe>() {

            @Override
            protected void updateItem(Recipe recipe, boolean empty) {
                super.updateItem(recipe, empty);

                if (empty || recipe == null || recipe.getName() == null) {
                    setText(null);
                } else {
                    VBox vBox = new VBox();
                    Label nameLabel = new Label(recipe.getName());

                    //Récupération des catégories
                    List<CategoryRecipe> categoryIds = recipe.getListofCategories();
                    String categories = "";
                    for (CategoryRecipe categoryRecipe : categoryIds) {
                        try {
                            categories += categoryFacade.getCategoryRecipeById(categoryRecipe.getCategoryId()).getNameCategory() + " ";
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    Label categoryLabel = new Label(categories);

                    Button deleteButton = new Button("Supprimer");
                    deleteButton.setOnAction(actionEvent -> {
                        Recipe recipeToDelete = getItem();
                        deleteRecipe(recipeToDelete);
                    });

                    Button updateButton = new Button("Modifer");
                    updateButton.setOnAction(actionEvent -> {
                        Recipe recipeToUpdate = getItem();
                        showFormUpdate(recipeToUpdate);
                    });

                    HBox hBox = new HBox(deleteButton, updateButton);
                    hBox.setSpacing(10);
                    hBox.setPadding(new Insets(10));
                    hBox.setAlignment(Pos.CENTER);

                    vBox.getChildren().addAll(nameLabel, categoryLabel, hBox);
                    setGraphic(vBox);
                }
            }
        };
    }
}
