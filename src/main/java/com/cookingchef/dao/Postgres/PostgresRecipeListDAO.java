package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.RecipeListDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.Recipe;
import com.cookingchef.model.RecipeList;
import com.cookingchef.model.RecipeListDbFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class PostgresRecipeListDAO implements RecipeListDAO {
    private static AtomicReference<PostgresRecipeListDAO> instance = new AtomicReference<>();

    private PostgresRecipeListDAO() {
    }

    /**
     * If the instance is null, create a new instance and return it.
     * Otherwise, return the existing instance.
     *
     * @return A PostgresRecipeListDAO object.
     */
    public static PostgresRecipeListDAO getPostgresRecipeListDAO() {
        instance.compareAndSet(null, new PostgresRecipeListDAO());
        return instance.get();
    }

    /**
     * Creates a recipe list in the database. By default, the isFav attribute is false.
     *
     * @param userID     The ID of the current user.
     * @param recipeList The RecipeList object that you want to create in the
     *                   database.
     * @return An optional integer.
     */
    @Override
    public Optional<Integer> createRecipeListInDb(int userID, RecipeList recipeList) throws SQLException {
        var queryRL = "INSERT INTO recipe_list(name, user_id, is_fav) VALUES(?, ?, ?) RETURNING id";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(queryRL)) {
            stmt.setString(1, recipeList.getName());
            stmt.setInt(2, userID);
            stmt.setBoolean(3, recipeList.isFav());

            stmt.executeQuery();
            var rs = stmt.getResultSet();
            rs.next();
            var newId = rs.getInt(RecipeListDbFields.ID.value);
            recipeList.setId(newId);

            // Inserting dependencies
            for (Recipe recipe : recipeList.getRecipeList()) {
                createRecipeListLinkRecipeInDb(recipe.getId().get(), newId);
            }
            return Optional.of(newId);
        }
    }

    /**
     * Auxiliary function to create dependencies on recipes inserting a RecipeList.
     *
     * @param recipeID     The ID of the Recipe to add in the database.
     * @param recipeListID The ID of the RecipeList object that you want to create
     *                     in the database.
     */
    public void createRecipeListLinkRecipeInDb(int recipeID, int recipeListID) throws SQLException {
        var queryRLUser = "INSERT INTO recipe_list_recipe(recipe_list_id, recipe_id) VALUES(?, ?)";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(queryRLUser)) {
            stmt.setInt(1, recipeListID);
            stmt.setInt(2, recipeID);

            stmt.executeUpdate();
        }
    }

    /**
     * Fetch a recipe list from the database by its ID.
     *
     * @param recipeID The ID of the recipe list you want to get.
     * @return An Optional object that contains a RecipeList object.
     */
    @Override
    public Optional<RecipeList> getRecipeListById(int recipeID) throws SQLException {
        var query = "SELECT * FROM recipe_list WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, recipeID);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            if (rs.next()) {
                return Optional.of(
                        new RecipeList(
                                rs.getInt(RecipeListDbFields.ID.value),
                                rs.getString(RecipeListDbFields.NAME.value)));
            }
        }
        return Optional.empty();
    }

    /**
     * Returns a list of RecipeList objects that the user in the parameter has.
     * Caution! The list may be empty.
     *
     * @param userID The ID of the specified user.
     * @return A list of recipe lists for the given user.
     */
    @Override
    public List<Optional<RecipeList>> getAllRecipeListByUserId(int userID) throws SQLException {
        var query = "SELECT * FROM recipe_list_user WHERE id = ?";
        var conn = ConnectionManager.getConnection();
        ArrayList<Optional<RecipeList>> toReturn = new ArrayList<>();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            while (rs.next()) {
                var recipeID = rs.getInt(RecipeListDbFields.ID.value);
                toReturn.add(getRecipeListById(recipeID));
            }
        }
        return toReturn;
    }

    /**
     * Fetch the favorite recipe list for the user from the database.
     *
     * @param userID The ID of the current user.
     * @return An Optional object that contains a RecipeList object.
     */
    @Override
    public Optional<RecipeList> getFavRecipeList(int userID) throws SQLException {
        var query = "SELECT * FROM recipe_list WHERE user_id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            if (rs.next()) {
                return Optional.of(
                        new RecipeList(
                                rs.getInt(RecipeListDbFields.ID.value),
                                rs.getString(RecipeListDbFields.NAME.value),
                                PostgresRecipeDAO.getRecipebyId(rs.getInt(RecipeDbFields.ID.value)),
                                // returns an ArrayList<Recipe> object for a recipeID (int) given.
                                rs.getBoolean(RecipeListDbFields.IS_FAV.value)));
            }
            return Optional.empty();
        }
    }

    /**
     * Delete all occurrences of the RecipeList given.
     *
     * @param recipeList The recipe list to delete.
     */
    @Override
    public void removeRecipeListFromDb(RecipeList recipeList) throws SQLException {
        var query = "DELETE FROM recipe_list_recipe WHERE recipe_list_id = ?";
        var query2 = "DELETE FROM recipe_list WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, recipeList.getId().get());
            stmt.executeUpdate();
        }
        try (var stmt = conn.prepareStatement(query2)) {
            stmt.setInt(1, recipeList.getId().get());
            stmt.executeUpdate();
        }

    }
}
