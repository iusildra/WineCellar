package com.cookingchef.model;

/**
 * The type Category.
 */
public class Category {
    private int idCategory;
    private String nameCategory;

    /**
     * Instantiates a new Category.
     *
     * @param idCategory   the id category
     * @param nameCategory the name category
     */
    public Category(int idCategory, String nameCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    /**
     * Gets id category.
     *
     * @return the id category
     */
    public int getIdCategory() {
        return idCategory;
    }

    /**
     * Sets id category.
     *
     * @param idCategory the id category
     */
    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    /**
     * Gets name category.
     *
     * @return the name category
     */
    public String getNameCategory() {
        return nameCategory;
    }

    /**
     * Sets name category.
     *
     * @param nameCategory the name category
     */
    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    @Override
    public String toString() {
        return "Category {" +
                "idCategory: " + idCategory +
                ", nameCategory: " + nameCategory + "\'" +
                "}";
    }
}
