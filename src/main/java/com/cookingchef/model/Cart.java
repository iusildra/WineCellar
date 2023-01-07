package com.cookingchef.model;

import java.util.Optional;

public class Cart {
    private Optional<Integer> id = Optional.empty();
    private int ingredient_id;
    private int user_id;
    private int quantity;
    private int unit;

    /**
     *
     * @param id
     * @param ingredient_id
     * @param user_id
     * @param quantity
     * @param unit
     */
    public Cart(int id, int ingredient_id, int user_id, int quantity, int unit) {
        this.id = Optional.of(id);
        this.ingredient_id = ingredient_id;
        this.user_id = user_id;
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     *
     * @param ingredient_id
     * @param user_id
     * @param quantity
     * @param unit
     */
    public Cart(int ingredient_id, int user_id, int quantity, int unit) {
        this.ingredient_id = ingredient_id;
        this.user_id = user_id;
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     *
     * @return id of the cart
     */
    public Optional<Integer> getId() {
        return id;
    }

    /**
     * Set the ID only once, if it is not already set.
     *
     * @param id The id of the object.
     */
    public void setId(int id) {
        if (this.id.isEmpty())
            this.id = Optional.of(id);
    }

    /**
     *
     * @return the ingredient id
     */
    public int getIngredient_id() {
        return ingredient_id;
    }

    /**
     *
     * @param ingredient_id
     */
    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    /**
     *
     * @return the user who possess the cart
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     *
     * @param user_id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     *
     * @return quantity for ingredient
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
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
