package com.cookingchef.model;

public enum RecipeDbFields {
    ID("id"),
    NAME("name"),
    DESCRIPTION("description"),
    SUMMARY("summary"),
    SRC("src"),
    SERVINGS("servings");

    public final String value;

    private RecipeDbFields(String key) {
        this.value = key;
    }
}
