package com.cookingchef.model;

public class IngredientRecipe {
    private int recipeId;
    private int ingredientId;
    private int quantity;
    private int unit;

    public IngredientRecipe(int recipeId, int ingredientId, int quantity, int unit) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getIngredient() {
        return ingredientId;
    }

    public void setIngredient(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
