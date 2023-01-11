package com.cookingchef.model;

public enum CartEntryDbFields {
    INGREDIENT_ID("ingredient_id"),
    INGREDIENT_NAME("name"),
    USER_ID("user_id"),
    QUANTITY("quantity"),
    UNIT("unit");

    public final String value;

    private CartEntryDbFields(String key) {
        this.value = key;
    }
}
