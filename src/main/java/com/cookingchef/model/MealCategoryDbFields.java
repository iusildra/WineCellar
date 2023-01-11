package com.cookingchef.model;

public enum MealCategoryDbFields {
    ID("id"),
    NAME("name");

    public final String value;

    MealCategoryDbFields(String key) {
        this.value = key;
    }
}
