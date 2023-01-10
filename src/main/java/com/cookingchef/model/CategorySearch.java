package com.cookingchef.model;

public enum CategorySearch {
    INGREDIENT("Ingrédient"),
    RECIPE("Recette"),
    CATEGORY("Catégorie");

    public final String value;


    CategorySearch(String key) {
        this.value = key;
    }
}
