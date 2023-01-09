package com.cookingchef.model;

public class Ingredient {
    private int id;
    private String name;
    private byte[] image;

    private Boolean allergen;

    public Ingredient(int id, String name, byte[] image, Boolean allergen) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.allergen = allergen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Boolean getAllergen() {
        return allergen;
    }

    public void setAllergen(Boolean allergen) {
        this.allergen = allergen;
    }

    @Override
    public String toString() {
        return "Ingredient {" +
                "id: " + id +
                ", name: " + name + "\'" +
                ", image: " + image + "\'" +
                ", allergen: " + allergen + "\'" +
                "}";
    }
}
