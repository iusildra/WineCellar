package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.RecipeListDAO;
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
        return Optional.empty();
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
