package com.cookingchef.dao;

import com.cookingchef.model.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IngredientDAO {

    public ArrayList<Ingredient> getAllIngredients() throws SQLException;
}
