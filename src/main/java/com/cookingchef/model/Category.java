package com.cookingchef.model;

/**
 * The type Category.
 */
public class Category {
    private int idCategory;
    private String nameCategory;

    /**
     * Constructor for the Category class
     * @param idCategory
     * @param nameCategory
     */
    public Category(int idCategory, String nameCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    /**
     *
     * @return the id of the category
     */
    public int getIdCategory() {
        return idCategory;
    }

    /**
     * Set the id of the category
     * @param idCategory
     */
    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    /**
     *
     * @return the name of the category
     */
    public String getNameCategory() {
        return nameCategory;
    }

    /**
     * Set the name of the category
     * @param nameCategory
     */
    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    @Override
    public String toString() {
        return nameCategory;
    }
}
