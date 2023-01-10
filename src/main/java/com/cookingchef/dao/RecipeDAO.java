package com.cookingchef.dao;

import com.cookingchef.model.Recipe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface RecipeDAO {

    /**
     * Registers a recipe in the database
     *
     * @param recipe The recipe object that you want to register.
     * @return The id of the newly created recipe
     */
    public Optional<Integer> registerRecipeInDb(Recipe recipe) throws SQLException;

    /**
     * Update the recipe in the database.
     *
     * @param recipe The recipe object that you want to update in the database.
     */
    public void updateRecipeInDb(Recipe recipe) throws SQLException;

    /**
     * Removes a recipe from the database.
     *
     * @param recipe The recipe object to be removed from the database.
     */
    public void removeRecipeFromDb(Recipe recipe) throws SQLException;

    /**
     * Get a recipe by id
     *
     * @param id The id of the recipe you want to get.
     * @return The recipe if it exists, otherwise, returns an empty option
     */
    public Optional<Recipe> getRecipeById(int id) throws SQLException;

    /**
     * Returns a list of recipes whose name matches the given name.
     *
     * @param name The name of the recipe you want to search for.
     * @return A list of recipes with the name specified.
     */
    public List<Recipe> getRecipesByName(String name) throws SQLException;


    /**
     * Returns the whole list of recipes
     *
     * @return Every recipe in the database.
     */
    public default List<Recipe> getRecipes() throws SQLException {
        return getRecipesByName("");
    }

    /**
     * Returns a list of recipes whose category matches the given category.
     * @param categories
     * @return
     * @throws SQLException
     */
    public List<Recipe> getRecipesByCategories(ArrayList<int> categories) throws SQLException;

    /**
     * Returns a list of recipes whose ingredient matches the given ingredient.
     * @param ingredients
     * @return
     * @throws SQLException
     */
    public List<Recipe> getRecipesByIngredients(ArrayList<int> ingredients) throws SQLException;

}
