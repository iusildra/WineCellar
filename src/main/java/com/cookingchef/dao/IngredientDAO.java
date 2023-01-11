package com.cookingchef.dao;

import com.cookingchef.model.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IngredientDAO {
    /**
     * Get all ingredients from the database.
     * @return
     * @throws SQLException
     */
    ArrayList<Ingredient> getAllIngredients() throws SQLException;

    /**
     * Add an ingredient to the database.
     * @param name
     * @param img
     * @param allergen
     * @return
     * @throws SQLException
     */
    boolean createIngredient(String name, byte[] img, Boolean allergen) throws SQLException;

    /**
     * Delete an ingredient
     * @param idIngredient
     * @throws SQLException
     */
    void deleteIngredient(int idIngredient) throws SQLException;

    /**
     * Update an ingredient
     * @param idIngredient
     * @param nameIngredient
     * @param imageIngredient
     * @param allergenIngredient
     * @return
     * @throws SQLException
     */
    boolean updateIngredient(int idIngredient, String nameIngredient, byte[] imageIngredient, Boolean allergenIngredient) throws SQLException;

    /**
     * Get an ingredient by its id.
     * @param idIngredient
     * @return
     * @throws SQLException
     */
    Ingredient getIngredientById(int idIngredient) throws SQLException;

    /**
     * Return if the ingredient already exist in database
     * @param name
     * @return
     * @throws SQLException
     */
    boolean ingredientAlreadyExist(String name) throws SQLException;
}
