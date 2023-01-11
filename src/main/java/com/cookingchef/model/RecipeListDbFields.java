package com.cookingchef.model;

public enum RecipeListDbFields {
    ID("id"),
    NAME("name"),
    USER_ID("user_id"),
    IS_FAV("is_fav");

    public final String value;

    RecipeListDbFields(String key) {
        this.value = key;
    }
}
