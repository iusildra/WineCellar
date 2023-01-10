package com.cookingchef.dao;

import com.cookingchef.model.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IngredientDAO {

    ArrayList<Ingredient> getAllIngredients() throws SQLException;

    boolean createIngredient(String name, byte[] img, Boolean allergen) throws SQLException;

    void deleteIngredient(int idIngredient) throws SQLException;

    boolean updateIngredient(int idIngredient, String nameIngredient, byte[] imageIngredient, Boolean allergenIngredient) throws SQLException;

    Ingredient getIngredientById(int idIngredient) throws SQLException;

    Ingredient getIngredientByName(String nameIngredient) throws SQLException;

    boolean ingredientAlreadyExist(String name) throws SQLException;

    List<Integer> getIngredientsIdByNames(List<String> ingredientsNames) throws SQLException;
}
