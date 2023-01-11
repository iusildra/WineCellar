package com.cookingchef.model;

public class IngredientRecipe {
    private int recipeId;
    private int ingredientId;
    private int quantity;
    private int unit;

    /**
     * Constructor for the ingredient recipe
     * @param recipeId
     * @param ingredientId
     * @param quantity
     * @param unit
     */
    public IngredientRecipe(int recipeId, int ingredientId, int quantity, int unit) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     *
     * @return the recipe id
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * Set the recipe id
     * @param recipeId
     */
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    /**
     *
     * @return the ingredient id
     */
    public int getIngredient() {
        return ingredientId;
    }

    /**
     * Set the ingredient id
     * @param ingredientId
     */
    public void setIngredient(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    /**
     *
     * @return the quantity of the ingredient
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of the ingredient
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return the unit of the quantity
     */
    public int getUnit() {
        return unit;
    }

    /**
     * set the unit of the quantity
     * @param unit
     */
    public void setUnit(int unit) {
        this.unit = unit;
    }
}
