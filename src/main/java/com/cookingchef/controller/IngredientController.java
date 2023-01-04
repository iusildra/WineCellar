package com.cookingchef.controller;

import com.cookingchef.facade.IngredientFacade;
import com.cookingchef.model.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientController {

    public IngredientController() {
        showList();
    }

    public ArrayList<Ingredient> showList() {

        IngredientFacade ingredientFacade = IngredientFacade.getIngredientFacade();
        ArrayList<Ingredient> ingredients;

        try {
            ingredients = ingredientFacade.getAllIngredients();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingredients;
    }

    public void onClickCreateIngredient() {

    }
}
