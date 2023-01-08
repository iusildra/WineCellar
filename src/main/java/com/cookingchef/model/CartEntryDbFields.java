package com.cookingchef.model;

public enum CartEntryDbFields {
    INGREDIENT_ID("ingredientId"),
    USER_ID("userId"),
    QUANTITY("quantity"),
    UNIT("unit");

    public final String value;

    private CartEntryDbFields(String key) {
        this.value = key;
    }
}
