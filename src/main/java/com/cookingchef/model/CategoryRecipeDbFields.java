package com.cookingchef.model;

public enum CategoryRecipeDbFields {
    RECIPE_ID("recipe_id"),
    RECIPE_CATEGORY_ID("recipe_category_id");

    public final String value;

    private CategoryRecipeDbFields(String key) {
        this.value = key;
    }
}
