package com.cookingchef.model;

public enum CalendarDbFields {
    USER_ID("user_id"),
    RECIPE_ID("recipe_id"),
    MEAL_CATEGORY_ID("meal_category_id"),
    DATE("date");

    public final String value;

    CalendarDbFields(String key) {
        this.value = key;
    }
}
