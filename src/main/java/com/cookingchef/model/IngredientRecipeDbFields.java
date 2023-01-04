package com.cookingchef.model;

public enum IngredientRecipeDbFields {
    INGREDIENT("ingredient"),
    QUANTITY("quantity"),
    UNIT("unit");

    public final String value;

    private IngredientRecipeDbFields(String key) {
        this.value = key;
    }
}
