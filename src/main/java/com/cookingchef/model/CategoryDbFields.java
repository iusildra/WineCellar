package com.cookingchef.model;

public enum CategoryDbFields{
    ID("id"),
    NAME("name");

    public final String value;

    CategoryDbFields(String key) {
        this.value = key;
    }
}
