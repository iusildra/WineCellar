package com.cookingchef.model;

/**
 * The enum Category search.
 */
public enum CategorySearch {
    /**
     * Ingredient category search.
     */
    INGREDIENT("Ingrédient"),
    /**
     * Recipe category search.
     */
    RECIPE("Recette"),
    /**
     * Category category search.
     */
    CATEGORY("Catégorie");

    /**
     * The Value.
     */
    public final String value;


    CategorySearch(String key) {
        this.value = key;
    }
}
