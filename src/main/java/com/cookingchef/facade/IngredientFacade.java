package com.cookingchef.facade;

import com.cookingchef.dao.IngredientDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Ingredient;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Ingredient facade.
 */
public class IngredientFacade {
    private static IngredientFacade instance;
    private final IngredientDAO ingredientDAO;

    private IngredientFacade() {
        var factory = new PostgresFactory();
        this.ingredientDAO = factory.getIngredientDAO();
    }

    /**
     * Gets ingredient facade.
     *
     * @return the ingredient facade
     */
    public static IngredientFacade getIngredientFacade() {
        if (instance == null) {
            instance = new IngredientFacade();
        }
        return instance;
    }

    /**
     * Gets all ingredients.
     *
     * @return the all ingredients
     * @throws SQLException the sql exception
     */
    public List<Ingredient> getAllIngredients() throws SQLException{
        return this.ingredientDAO.getAllIngredients();
    }

    /**
     * Create ingredient boolean.
     *
     * @param name     the name
     * @param img      the img
     * @param allergen the allergen
     * @return the boolean
     * @throws SQLException the sql exception
     */
    public boolean createIngredient(String name, byte[] img, Boolean allergen) throws SQLException {
        return this.ingredientDAO.createIngredient(name, img, allergen);
    }

    /**
     * Delete ingredient.
     *
     * @param idIngredient the id ingredient
     * @throws SQLException the sql exception
     */
    public void deleteIngredient(int idIngredient) throws SQLException {
        this.ingredientDAO.deleteIngredient(idIngredient);
    }

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
    public boolean updateIngredient(int idIngredient, String nameIngredient, byte[] imageIngredient, Boolean allergenIngredient) throws SQLException {
        return this.ingredientDAO.updateIngredient(idIngredient, nameIngredient, imageIngredient, allergenIngredient);
    }

    /**
     * Gets ingredient by id.
     *
     * @param idIngredient the id ingredient
     * @return the ingredient by id
     * @throws SQLException the sql exception
     */
    public Ingredient getIngredientById(int idIngredient) throws SQLException {
        return this.ingredientDAO.getIngredientById(idIngredient);
    }

    /**
     * Gets ingredients id by names.
     *
     * @param ingredients the ingredients
     * @return the ingredients id by names
     * @throws SQLException the sql exception
     */
    public List<Integer> getIngredientsIdByNames(List<String> ingredients) throws SQLException {
        return this.ingredientDAO.getIngredientsIdByNames(ingredients);
    }
}
