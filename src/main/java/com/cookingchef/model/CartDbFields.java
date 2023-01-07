package com.cookingchef.model;

public enum CartDbFields {
    ID("id"),
    INGREDIENT_ID("ingredient_id"),
    USER_ID("user_id"),
    QUANTITY("quantity"),
    UNIT("unit");

    public final String value;

    private CartDbFields(String key) {
        this.value = key;
    }
}
