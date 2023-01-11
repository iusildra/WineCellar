package com.cookingchef.dao;

import com.cookingchef.model.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The interface Ingredient dao.
 */
public interface IngredientDAO {

    /**
     * Gets all ingredients.
     *
     * @return the all ingredients
     * @throws SQLException the sql exception
     */
    ArrayList<Ingredient> getAllIngredients() throws SQLException;

    /**
     * Create ingredient boolean.
     *
     * @param name     the name
     * @param img      the img
     * @param allergen the allergen
     * @return the boolean
     * @throws SQLException the sql exception
     */
    boolean createIngredient(String name, byte[] img, Boolean allergen) throws SQLException;

    /**
     * Delete ingredient.
     *
     * @param idIngredient the id ingredient
     * @throws SQLException the sql exception
     */
    void deleteIngredient(int idIngredient) throws SQLException;

    /**
     * Update ingredient boolean.
     *
     * @param idIngredient       the id ingredient
     * @param nameIngredient     the name ingredient
     * @param imageIngredient    the image ingredient
     * @param allergenIngredient the allergen ingredient
     * @return the boolean
     * @throws SQLException the sql exception
     */
    boolean updateIngredient(int idIngredient, String nameIngredient, byte[] imageIngredient, Boolean allergenIngredient) throws SQLException;

    /**
     * Gets ingredient by id.
     *
     * @param idIngredient the id ingredient
     * @return the ingredient by id
     * @throws SQLException the sql exception
     */
    Ingredient getIngredientById(int idIngredient) throws SQLException;

    /**
     * Gets ingredient by name.
     *
     * @param nameIngredient the name ingredient
     * @return the ingredient by name
     * @throws SQLException the sql exception
     */
    Ingredient getIngredientByName(String nameIngredient) throws SQLException;

    /**
     * Ingredient already exist boolean.
     *
     * @param name the name
     * @return the boolean
     * @throws SQLException the sql exception
     */
    boolean ingredientAlreadyExist(String name) throws SQLException;

    /**
     * Gets ingredients id by names.
     *
     * @param ingredientsNames the ingredients names
     * @return the ingredients id by names
     * @throws SQLException the sql exception
     */
    List<Integer> getIngredientsIdByNames(List<String> ingredientsNames) throws SQLException;
}
