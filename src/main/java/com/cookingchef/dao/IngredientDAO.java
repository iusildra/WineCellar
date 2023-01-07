package com.cookingchef.dao;

import com.cookingchef.model.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IngredientDAO {

    public ArrayList<Ingredient> getAllIngredients() throws SQLException;

    public void createIngredient(String name, byte[] img, Boolean allergen) throws SQLException;

    public void deleteIngredient(int idIngredient) throws SQLException;

    public void updateIngredient(int idIngredient, String nameIngredient, byte[] imageIngredient, Boolean allergenIngredient) throws SQLException;
}
