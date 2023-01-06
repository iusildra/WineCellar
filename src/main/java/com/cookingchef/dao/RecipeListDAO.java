package com.cookingchef.dao;

import com.cookingchef.model.RecipeList;
import com.cookingchef.model.Suggestion;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RecipeListDAO {
    /**
     * Creates a recipe list in the database
     *
     * @param userID The ID of the current user.
     * @param recipeList The RecipeList object that you want to create in the
     *                   database.
     * @return An optional integer.
     */
    Optional<Integer> createRecipeListInDb(int userID, RecipeList recipeList) throws SQLException;

    /**
     * Fetch a recipe list from the database by its ID.
     *
     * @param userID The ID of the current user.
     * @param recipeID The ID of the recipe list you want to get.
     * @return An Optional object that contains a RecipeList object.
     */
    Optional<RecipeList> getRecipeListById(int userID, int recipeID) throws SQLException;

    /**
     * Returns a list of RecipeList objects that the user in the parameter has.
     *
     * @param userID The ID of the specified user.
     * @return A list of recipe lists for the given user.
     */
    List<RecipeList> getAllRecipeListByUserId(int userID) throws SQLException;

    /**
     * Fetch the favorite recipe list for the user from the database.
     *
     * @param userID The ID of the current user.
     * @return An Optional object that contains a RecipeList object.
     */
    default Optional<RecipeList> getFavRecipeList(int userID) throws SQLException {
        return getRecipeListById(userID, 0);
    }
}
