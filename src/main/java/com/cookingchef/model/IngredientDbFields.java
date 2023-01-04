package com.cookingchef.model;

public enum IngredientDbFields {
    ID("id"),
    NAME("name"),
    IMAGE("src"),
    ALLERGEN("allergen");

    public final String value;

    IngredientDbFields(String key) {
        this.value = key;
    }
}
