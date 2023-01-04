package com.cookingchef.model;

public enum RecipeDbFields {
    ID("id"),
    NAME("name"),
    DESCRIPTION("description"),
    SUMMARY("summary"),
    SRC("src"),
    SERVINGS("servings"),
    LISTOFINGREDIENTS("listOfIngredients"),
    LISTOFCATEGORIES("listofCategories");


    public final String value;

    private RecipeDbFields(String key) {
        this.value = key;
    }
}
