package com.cookingchef.model;

/**
 * The enum Ingredient db fields.
 */
public enum IngredientDbFields {
    /**
     * Id ingredient db fields.
     */
    ID("id"),
    /**
     * Name ingredient db fields.
     */
    NAME("name"),
    /**
     * Image ingredient db fields.
     */
    IMAGE("src"),
    /**
     * Allergen ingredient db fields.
     */
    ALLERGEN("allergen");

    /**
     * The Value.
     */
    public final String value;

    IngredientDbFields(String key) {
        this.value = key;
    }
}
