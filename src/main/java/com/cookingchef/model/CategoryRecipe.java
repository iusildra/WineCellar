package com.cookingchef.model;

public class CategoryRecipe {
    private int recipeId;
    private int categoryId;

    /**
     * Constructor for the CategoryRecipe Class
     * @param recipeId
     * @param categoryId
     */
    public CategoryRecipe(int recipeId, int categoryId) {
        this.recipeId = recipeId;
        this.categoryId = categoryId;
    }

    /**
     * Constructor for the CategoryRecipe Class
     * @param categoryId
     */
    public CategoryRecipe(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     *
     * @return the id of the recipe
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * Set the id of the recipe
     * @param recipeId
     */
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    /**
     *
     * @return the id of the category
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Set the id of the category
     * @param categoryId
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
