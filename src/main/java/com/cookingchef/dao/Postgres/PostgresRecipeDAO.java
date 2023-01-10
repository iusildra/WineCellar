package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.RecipeDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.IngredientRecipe;
import com.cookingchef.model.Recipe;
import com.cookingchef.model.RecipeDbFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
            return Optional.of(newId);

            //boucle while catego +
            for (Category category : recipe.getListofCategories()) {
                var query2 = "INSERT INTO recipe_category_recipe(recipe_id, recipe_category_id) VALUES(?,?) RETURNING id";

                var stmt2 = conn.prepareStatement(query2);
                stmt.setInt(1, newId);
                stmt.setInt(2, category.getId());

                stmt2.executeQuery();
                var rs2 = stmt.getResultSet();
                rs2.next();
            }

            for (IngredientRecipe ingredientRecipe : recipe.getListOfIngredients()) {
                var query3 = "INSERT INTO recipe_ingredient(recipe_id, ingredient_id, quantity, unit) VALUES(?,?,?,?) RETURNING id";

                var stmt3 = conn.prepareStatement(query3);
                stmt.setInt(1, newId);
                stmt.setInt(2, ingredientRecipe.getIngredient().getId());
                stmt.setInt(3, ingredientRecipe.getQuantity());
                stmt.setInt(4, ingredientRecipe.getUnit());

                stmt3.executeQuery();
                var rs3 = stmt.getResultSet();
                rs3.next();
            }
        }
    }

    @Override
    public void updateRecipeInDb(Recipe recipe) throws SQLException {
        var query = "UPDATE recipe SET name = ?, summary = ?, listOfCategories = ?, listOfIngredients = ?, allergens = ? WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setString(1, recipe.getName());
            stmt.setString(2, recipe.getSummary());
            stmt.setArray(3, recipe.getListofCategories());
            stmt.setArray(4, recipe.getListOfIngredients());
            stmt.setInt(5, recipe.getId().get());
            stmt.executeUpdate();
        }
    }

    @Override
    public void removeRecipeFromDb(Recipe recipe) throws SQLException {
        var query = "DELETE FROM recipe WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, recipe.getId().get());
            stmt.executeUpdate();
        }

    }

    @Override
    public Optional<Recipe> getRecipeById(int id) throws SQLException {
        var query = "SELECT * FROM recipe WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            if (rs.next()) {
                return Optional.of(
                        new Recipe(
                                rs.getInt(RecipeDbFields.ID.value),
                                rs.getString(RecipeDbFields.NAME.value),
                                rs.getString(RecipeDbFields.DESCRIPTION.value),
                                rs.getString(RecipeDbFields.SUMMARY.value),
                                rs.getBytes(RecipeDbFields.SRC.value),
                                rs.getInt(RecipeDbFields.SERVINGS.value),
                                rs.getArray(RecipeDbFields.LISTOFINGREDIENTS.value),
                                rs.getArray(RecipeDbFields.LISTOFCATEGORIES.value)));
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
                recipes.add(
                        new Recipe(
                                rs.getInt(RecipeDbFields.ID.value),
                                rs.getString(RecipeDbFields.NAME.value),
                                rs.getString(RecipeDbFields.SUMMARY.value),
                                rs.getArray(RecipeDbFields.LISTOFINGREDIENTS.value),
                                rs.getArray(RecipeDbFields.LISTOFCATEGORIES.value),
                                rs.getBoolean(RecipeDbFields.ALLERGENS.value)));
            }
            return recipes;
        }
    }

}