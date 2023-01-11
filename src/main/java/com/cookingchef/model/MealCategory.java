package com.cookingchef.model;

public class MealCategory {
    private int id;
    private String name;

    public MealCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
