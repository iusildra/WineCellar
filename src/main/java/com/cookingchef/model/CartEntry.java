package com.cookingchef.model;

import java.util.Optional;

public class CartEntry {
    private int ingredientId;
    private int userId;
    private double quantity;
    private int unit;


    /**
     *
     * @param ingredientId
     * @param userId
     * @param quantity
     * @param unit
     */
    public CartEntry(int ingredientId, int userId, double quantity, int unit) {
        this.ingredientId = ingredientId;
        this.userId = userId;
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     *
     * @param quantity
     * @param unit
     */
    public CartEntry(double quantity, int unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     *
     * @return the ingredient id
     */
    public int getIngredientId() {
        return ingredientId;
    }

    /**
     *
     * @param ingredientId
     */
    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    /**
     *
     * @return the user who possess the cart
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     *
     * @return quantity for ingredient
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return unit of quantity
     */
    public int getUnit() {
        return unit;
    }

    /**
     *
     * @param unit
     */
    public void setUnit(int unit) {
        this.unit = unit;
    }
}
