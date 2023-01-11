package com.cookingchef.facade;

import com.cookingchef.dao.IngredientDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientFacade {
    private static IngredientFacade instance;
    private final IngredientDAO ingredientDAO;

    private IngredientFacade() {
        var factory = new PostgresFactory();
        this.ingredientDAO = factory.getIngredientDAO();
    }

    public static IngredientFacade getIngredientFacade() {
        if (instance == null) {
            instance = new IngredientFacade();
        }
        return instance;
    }

    public ArrayList<Ingredient> getAllIngredients() throws SQLException{
        return this.ingredientDAO.getAllIngredients();
    }

    public boolean createIngredient(String name, byte[] img, Boolean allergen) throws SQLException {
        return this.ingredientDAO.createIngredient(name, img, allergen);
    }

    public void deleteIngredient(int idIngredient) throws SQLException {
        this.ingredientDAO.deleteIngredient(idIngredient);
    }

    public boolean updateIngredient(int idIngredient, String nameIngredient, byte[] imageIngredient, Boolean allergenIngredient) throws SQLException {
        return this.ingredientDAO.updateIngredient(idIngredient, nameIngredient, imageIngredient, allergenIngredient);
    }

    public Ingredient getIngredientById(int idIngredient) throws SQLException {
        return this.ingredientDAO.getIngredientById(idIngredient);
    }
}
