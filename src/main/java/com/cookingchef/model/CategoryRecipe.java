package com.cookingchef.model;

public class CategoryRecipe {
    private int recipeId;
    private int categoryId;

    public CategoryRecipe(int recipeId, int categoryId) {
        this.recipeId = recipeId;
        this.categoryId = categoryId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
