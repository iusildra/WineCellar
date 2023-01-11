package com.cookingchef.model;

/**
 * The type Ingredient.
 */
public class Ingredient {
    private int id;
    private String name;
    private byte[] image;

    private Boolean allergen;

    /**
     * Constructor for the Ingredient class
     * @param id
     * @param name
     * @param image
     * @param allergen
     */
    public Ingredient(int id, String name, byte[] image, Boolean allergen) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.allergen = allergen;
    }

    /**
     *
     * @return the id of the ingredient
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the ingredient
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the name of the ingredient
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the ingredient
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the image of the ingredient
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Set the image of the ingredient
     * @param image
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     *
     * @return if the ingredient is an allergen
     */
    public Boolean getAllergen() {
        return allergen;
    }

    /**
     * Set if the ingredient is an allergen
     * @param allergen
     */
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
