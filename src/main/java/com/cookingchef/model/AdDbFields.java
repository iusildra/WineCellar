package com.cookingchef.model;

public enum AdDbFields {
    ID("id"),
    DESCRIPTION_PROMOTION("description_promotion"),
    PRICE("price"),
    PARTNER_ID("partner_id"),
    INGREDIENT_ID("ingredient_id");

    public final String value;

    private AdDbFields(String key) {
        this.value = key;
    }
}
