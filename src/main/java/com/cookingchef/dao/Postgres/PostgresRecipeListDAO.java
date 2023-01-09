package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.RecipeListDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.PartnerDbFields;
import com.cookingchef.model.RecipeList;

import java.sql.SQLException;
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
     * @return A PostgresPartnerDAO object.
     */
    public static PostgresRecipeListDAO getPostgresPartnerDAO() {
        instance.compareAndSet(null, new PostgresRecipeListDAO());
        return instance.get();
    }


    /**
     * Creates a recipe list in the database
     *
     * @param userID     The ID of the current user.
     * @param recipeList The RecipeList object that you want to create in the
     *                   database.
     * @return An optional integer.
     */
    @Override
    public Optional<Integer> createRecipeListInDb(int userID, RecipeList recipeList) throws SQLException {
        var queryRL = "INSERT INTO recipe_list(name) VALUES(?) RETURNING id";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(queryRL)) {
            stmt.setString(1, recipeList.getName());

            stmt.executeQuery();
            var rs = stmt.getResultSet();
            rs.next();
            var newId = rs.getInt(PartnerDbFields.ID.value);
            recipeList.setId(newId);

            // Inserting dependencies
            createRecipeListLinkUserInDb(userID, newId);
            for (Recipe recipe : recipeList.getRecipeList()) {
                createRecipeListLinkRecipeInDb(recipe.getId(), newId);
            }
            return Optional.of(newId);
        }
    }

    public void createRecipeListLinkUserInDb(int userID, int recipeListID) throws SQLException {
        var queryRLUser = "INSERT INTO recipe_list_user(user_id, recipe_list_id) VALUES(?, ?)";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(queryRLUser)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, recipeListID);

            stmt.executeUpdate();
        }
    }

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
     * @param userID   The ID of the current user.
     * @param recipeID The ID of the recipe list you want to get.
     * @return An Optional object that contains a RecipeList object.
     */
    @Override
    public Optional<RecipeList> getRecipeListById(int userID, int recipeID) throws SQLException {
        return Optional.empty();
    }

    /**
     * Returns a list of RecipeList objects that the user in the parameter has.
     *
     * @param userID The ID of the specified user.
     * @return A list of recipe lists for the given user.
     */
    @Override
    public List<RecipeList> getAllRecipeListByUserId(int userID) throws SQLException {
        return null;
    }
}
