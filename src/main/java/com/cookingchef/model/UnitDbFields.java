package com.cookingchef.model;

public enum UnitDbFields {
    ID("id"),
    NAME("name");

    public final String value;

    UnitDbFields(String key) {
        this.value = key;
    }
}
