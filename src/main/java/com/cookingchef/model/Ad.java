package com.cookingchef.model;

import java.util.Optional;

public class Ad {
    private Optional<Integer> id = Optional.empty();
    private String description_promotion;
    private int price;

    private int partner_id;
    private int ingredient_id;

    /**
     * @param id
     * @param description_promotion
     * @param price
     * @param partner_id
     * @param ingredient_id
     */
    public Ad(int id, String description_promotion, int price, int partner_id, int ingredient_id) {
        this.id = Optional.of(id);
        this.description_promotion = description_promotion;
        this.price = price;
        this.partner_id = partner_id;
        this.ingredient_id = ingredient_id;
    }

    /**
     * @param description_promotion
     * @param price
     * @param partner_id
     * @param ingredient_id
     */
    public Ad(String description_promotion, int price, int partner_id, int ingredient_id) {
        this.description_promotion = description_promotion;
        this.price = price;
        this.partner_id = partner_id;
        this.ingredient_id = ingredient_id;
    }

    /**
     * @return the id
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
     * @return the description of the ad
     */
    public String getDescription_promotion() {
        return description_promotion;
    }

    /**
     *
     * @param description_promotion
     */
    public void setDescription_promotion(String description_promotion) {
        this.description_promotion = description_promotion;
    }

    /**
     *
     * @return the price of the ad
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *
     * @return the partner id who propose this ad
     */
    public int getPartnerId() {
        return partner_id;
    }

    /**
     *
     * @param partner_id
     */
    public void setPartnerId(int partner_id) {
        this.partner_id = partner_id;
    }

    /**
     *
     * @return the ingredient id the ad is about
     */
    public int getIngredientId() {
        return ingredient_id;
    }

    /**
     *
     * @param ingredient_id
     */
    public void setIngredientId(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }
}
