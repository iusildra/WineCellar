package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.RecipeDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class PostgresRecipeDAO implements RecipeDAO {

    private static AtomicReference<PostgresRecipeDAO> instance = new AtomicReference<>();

    private PostgresRecipeDAO() {
    }

    /**
     * If the instance is null, create a new instance and return it.
     * Otherwise, return the existing instance
     *
     * @return A PostgresRecipeDAO object.
     */
    public static PostgresRecipeDAO getPostgresRecipeDAO() {
        instance.compareAndSet(null, new PostgresRecipeDAO());
        return instance.get();
    }

    /**
     * Get all the ingredient Recipe of a recipe by recipe id
     * @param recipeId
     * @return
     * @throws SQLException
     */
    public ArrayList<IngredientRecipe> getAllIngredientsRecipeByRecipeId(int recipeId) throws SQLException{
        var query = "SELECT * FROM recipe_ingredient WHERE recipe_id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, recipeId);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            var ingredients = new ArrayList<IngredientRecipe>();
            while (rs.next()) {
                ingredients.add(
                        new IngredientRecipe(
                                rs.getInt(IngredientRecipeDbFields.RECIPE_ID.value),
                                rs.getInt(IngredientRecipeDbFields.INGREDIENT_ID.value),
                                rs.getInt(IngredientRecipeDbFields.QUANTITY.value),
                                rs.getInt(IngredientRecipeDbFields.UNIT.value)
                        )
                );
            }
            return ingredients;
        }
    }

    /**
     * Get all the Category Recipe of a recipe by recipe id
     * @param recipeId
     * @return
     * @throws SQLException
     */
    public ArrayList<CategoryRecipe> getAllCategoriesRecipeByRecipeId(int recipeId) throws SQLException{
        var query = "SELECT * FROM recipe_category_recipe WHERE recipe_id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, recipeId);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            var categories = new ArrayList<CategoryRecipe>();
            while (rs.next()) {
                categories.add(
                        new CategoryRecipe(
                                rs.getInt(CategoryRecipeDbFields.RECIPE_ID.value),
                                rs.getInt(CategoryRecipeDbFields.RECIPE_CATEGORY_ID.value)
                        )
                );
            }
            return categories;
        }
    }

    /**
     * Check if a recipe exists in a list of recipe
     * @param recipes
     * @param recipe
     * @return
     */
    public boolean checkIfRecipeExists(List<Recipe> recipes, Recipe recipe) {
        for (Recipe r : recipes) {
            if (r.getId().equals(recipe.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Integer> registerRecipeInDb(Recipe recipe) throws SQLException {
        var query1 = "INSERT INTO recipe(name, description, summary, src, servings) VALUES(?, ?, ?, ?, ?) RETURNING id";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query1)) {
            stmt.setString(1, recipe.getName());
            stmt.setString(2, recipe.getDescription());
            stmt.setString(3, recipe.getSummary());
            stmt.setBytes(4, recipe.getSrc());
            stmt.setInt(5, recipe.getServings());

            stmt.executeQuery();
            var rs = stmt.getResultSet();
            rs.next();
            var newId = rs.getInt(RecipeDbFields.ID.value);
            recipe.setId(newId);

            for (CategoryRecipe categoryRecipe : recipe.getListofCategories()) {
                var query2 = "INSERT INTO recipe_category_recipe(recipe_id, recipe_category_id) VALUES(?,?) RETURNING id";

                var stmt2 = conn.prepareStatement(query2);
                stmt.setInt(1, newId);
                stmt.setInt(2, categoryRecipe.getCategoryId());

                stmt2.executeQuery();
                var rs2 = stmt.getResultSet();
                rs2.next();

                //Est ce qu'il faut faire pareil et set Id avec
                //var newId = rs.getInt(RecipeDbFields.ID.value);
                //recipe.setId(newId);
            }

            for (IngredientRecipe ingredientRecipe : recipe.getListOfIngredients()) {
                var query3 = "INSERT INTO recipe_ingredient(recipe_id, ingredient_id, quantity, unit) VALUES(?,?,?,?) RETURNING id";

                var stmt3 = conn.prepareStatement(query3);
                stmt.setInt(1, newId);
                stmt.setInt(2, ingredientRecipe.getIngredient());
                stmt.setInt(3, ingredientRecipe.getQuantity());
                stmt.setInt(4, ingredientRecipe.getUnit());

                stmt3.executeQuery();
                var rs3 = stmt.getResultSet();
                rs3.next();
            }

            return Optional.of(newId);
        }
    }

    @Override
    public void updateRecipeInDb(Recipe recipe) throws SQLException {
        var query1 = "UPDATE recipe SET name = ?, description = ?, summary = ?, src = ?, servings = ? WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query1)) {
            stmt.setString(1, recipe.getName());
            stmt.setString(2, recipe.getDescription());
            stmt.setString(3, recipe.getSummary());
            stmt.setBytes(4, recipe.getSrc());
            stmt.setInt(5, recipe.getServings());
            stmt.setInt(6, recipe.getId().get());

            stmt.executeUpdate();

            for (CategoryRecipe category : recipe.getListofCategories()) {
                var query2 = "UPDATE recipe_category_recipe SET recipe_category_id = ? WHERE recipe_id = ?";

                var stmt2 = conn.prepareStatement(query2);
                stmt.setInt(1, category.getCategoryId());
                stmt.setInt(2, recipe.getId().get());

                stmt2.executeUpdate();
            }

            for (IngredientRecipe ingredientRecipe : recipe.getListOfIngredients()) {
                var query3 = "UPDATE recipe_ingredient SET ingredient_id = ?, quantity = ?, unit = ? WHERE recipe_id = ?";

                var stmt3 = conn.prepareStatement(query3);
                stmt.setInt(1, ingredientRecipe.getIngredient());
                stmt.setInt(2, ingredientRecipe.getQuantity());
                stmt.setInt(3, ingredientRecipe.getUnit());
                stmt.setInt(4, recipe.getId().get());

                stmt3.executeUpdate();
            }

        }
    }

    @Override
    public void removeRecipeFromDb(Recipe recipe) throws SQLException {
        var query1 = "DELETE FROM recipe WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query1)) {
            stmt.setInt(1, recipe.getId().get());
            stmt.executeUpdate();

            for (CategoryRecipe category : recipe.getListofCategories()) {
                var query2 = "DELETE FROM recipe_category_recipe WHERE recipe_id = ? AND recipe_category_id= ?";

                var stmt2 = conn.prepareStatement(query2);
                stmt.setInt(1, recipe.getId().get());
                stmt.setInt(2, category.getCategoryId());

                stmt2.executeUpdate();
            }

            for (IngredientRecipe ingredientRecipe : recipe.getListOfIngredients()) {
                var query3 = "DELETE FROM recipe_ingredient WHERE recipe_id = ? AND ingredient_id = ?";

                var stmt3 = conn.prepareStatement(query3);
                stmt.setInt(1, recipe.getId().get());
                stmt.setInt(2, ingredientRecipe.getIngredient());
                stmt3.executeUpdate();
            }
        }
    }

    @Override
    public Optional<Recipe> getRecipeById(int id) throws SQLException {
        var query1 = "SELECT * FROM recipe WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query1)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
            var rs = stmt.getResultSet();

            ArrayList<IngredientRecipe> ingredients = this.getAllIngredientsRecipeByRecipeId(id);
            ArrayList<CategoryRecipe> categories = this.getAllCategoriesRecipeByRecipeId(id);

            if (rs.next()) {
                return Optional.of(
                    new Recipe(
                        rs.getInt(RecipeDbFields.ID.value),
                        rs.getString(RecipeDbFields.NAME.value),
                        rs.getString(RecipeDbFields.DESCRIPTION.value),
                        rs.getString(RecipeDbFields.SUMMARY.value),
                        rs.getBytes(RecipeDbFields.SRC.value),
                        rs.getInt(RecipeDbFields.SERVINGS.value),
                        ingredients,
                        categories
                    )
                );
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Recipe> getRecipesByName(String name) throws SQLException {
        var query = "SELECT * FROM recipe WHERE name LIKE ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%"+ name + "%");
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            var recipes = new ArrayList<Recipe>();
            while (rs.next()) {
                ArrayList<IngredientRecipe> ingredients = this.getAllIngredientsRecipeByRecipeId(rs.getInt(RecipeDbFields.ID.value));

                ArrayList<CategoryRecipe> categories = this.getAllCategoriesRecipeByRecipeId(rs.getInt(RecipeDbFields.ID.value));

                recipes.add(
                    new Recipe(
                        rs.getInt(RecipeDbFields.ID.value),
                        rs.getString(RecipeDbFields.NAME.value),
                        rs.getString(RecipeDbFields.DESCRIPTION.value),
                        rs.getString(RecipeDbFields.SUMMARY.value),
                        rs.getBytes(RecipeDbFields.SRC.value),
                        rs.getInt(RecipeDbFields.SERVINGS.value),
                        ingredients,
                        categories
                    )
                );
            }
            return recipes;
        }
    }

    @Override
    public List<Recipe> getRecipesByCategories(ArrayList<int> categories) throws SQLException {
        List<Recipe> recipes = new ArrayList<Recipe>();

        //Pour chaque category
        for (int categoryId : categories) {
            //On récupère les recettes qui ont cette catégorie
            var query = "SELECT * FROM recipe_category_recipe WHERE recipe_category_id = ?";
            var conn = ConnectionManager.getConnection();

            try (var stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, categoryId);
                stmt.executeQuery();
                var rs = stmt.getResultSet();
                while (rs.next()) {
                    ArrayList<IngredientRecipe> ingredients = this.getAllIngredientsRecipeByRecipeId(rs.getInt(RecipeDbFields.ID.value));

                    ArrayList<CategoryRecipe> categoriesRecipe = this.getAllCategoriesRecipeByRecipeId(rs.getInt(RecipeDbFields.ID.value));

                    Recipe newRecipe = new Recipe(
                            rs.getInt(RecipeDbFields.ID.value),
                            rs.getString(RecipeDbFields.NAME.value),
                            rs.getString(RecipeDbFields.DESCRIPTION.value),
                            rs.getString(RecipeDbFields.SUMMARY.value),
                            rs.getBytes(RecipeDbFields.SRC.value),
                            rs.getInt(RecipeDbFields.SERVINGS.value),
                            ingredients,
                            categoriesRecipe
                    );

                    //Vérification que la recette n'est pas déjà dans la liste
                    if (!this.checkIfRecipeExists(recipes, newRecipe)) {
                        recipes.add(newRecipe);
                    }
                }
            }

        }
        return recipes;
    }

    @Override
    public List<Recipe> getRecipesByIngredients(ArrayList<int> ingredients) throws SQLException{
        List<Recipe> recipes = new ArrayList<Recipe>();

        //Pour chaque ingredient
        for (int ingredientId : ingredients) {
            //On récupère les recettes qui ont cet ingredient
            var query = "SELECT * FROM recipe_ingredient WHERE ingredient_id = ?";
            var conn = ConnectionManager.getConnection();

            try (var stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, ingredientId);
                stmt.executeQuery();
                var rs = stmt.getResultSet();
                while (rs.next()) {
                    ArrayList<IngredientRecipe> ingredientsRecipe = this.getAllIngredientsRecipeByRecipeId(rs.getInt(RecipeDbFields.ID.value));

                    ArrayList<CategoryRecipe> categories = this.getAllCategoriesRecipeByRecipeId(rs.getInt(RecipeDbFields.ID.value));

                    Recipe newRecipe = new Recipe(
                            rs.getInt(RecipeDbFields.ID.value),
                            rs.getString(RecipeDbFields.NAME.value),
                            rs.getString(RecipeDbFields.DESCRIPTION.value),
                            rs.getString(RecipeDbFields.SUMMARY.value),
                            rs.getBytes(RecipeDbFields.SRC.value),
                            rs.getInt(RecipeDbFields.SERVINGS.value),
                            ingredientsRecipe,
                            categories
                    );

                    //Vérification que la recette n'est pas déjà dans la liste
                    if (!this.checkIfRecipeExists(recipes, newRecipe)) {
                        recipes.add(newRecipe);
                    }
                }
            }
        }
        return recipes;
    }

}