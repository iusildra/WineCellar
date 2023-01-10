package com.cookingchef.model;

public enum IngredientRecipeDbFields {
    RECIPE_ID("recipe_id"),
    INGREDIENT_ID("ingredient_id"),
    QUANTITY("quantity"),
    UNIT("unit");

    public final String value;

    private IngredientRecipeDbFields(String key) {
        this.value = key;
    }
}
