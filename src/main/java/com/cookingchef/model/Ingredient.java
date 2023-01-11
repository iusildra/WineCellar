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
     * Instantiates a new Ingredient.
     *
     * @param id       the id
     * @param name     the name
     * @param image    the image
     * @param allergen the allergen
     */
    public Ingredient(int id, String name, byte[] image, Boolean allergen) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.allergen = allergen;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get image byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * Gets allergen.
     *
     * @return the allergen
     */
    public Boolean getAllergen() {
        return allergen;
    }

    /**
     * Sets allergen.
     *
     * @param allergen the allergen
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
