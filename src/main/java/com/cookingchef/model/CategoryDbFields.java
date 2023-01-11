package com.cookingchef.model;

/**
 * The enum Category db fields.
 */
public enum CategoryDbFields{
    /**
     * Id category db fields.
     */
    ID("id"),
    /**
     * Name category db fields.
     */
    NAME("name");

    /**
     * The Value.
     */
    public final String value;

    CategoryDbFields(String key) {
        this.value = key;
    }
}
