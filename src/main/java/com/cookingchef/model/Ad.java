package com.cookingchef.model;

import java.util.Optional;

public class Ad {
    private Optional<Integer> id = Optional.empty();
    private String descriptionPromotion;
    private int price;

    private int partnerId;
    private int ingredientId;

    /**
     * @param id
     * @param descriptionPromotion
     * @param price
     * @param partnerId
     * @param ingredientId
     */
    public Ad(int id, String descriptionPromotion, int price, int partnerId, int ingredientId) {
        this.id = Optional.of(id);
        this.descriptionPromotion = descriptionPromotion;
        this.price = price;
        this.partnerId = partnerId;
        this.ingredientId = ingredientId;
    }

    /**
     * @param descriptionPromotion
     * @param price
     * @param partnerId
     * @param ingredientId
     */
    public Ad(String descriptionPromotion, int price, int partnerId, int ingredientId) {
        this.descriptionPromotion = descriptionPromotion;
        this.price = price;
        this.partnerId = partnerId;
        this.ingredientId = ingredientId;
    }

    /**
     * @return the id of the ad
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
    public String getDescriptionPromotion() {
        return descriptionPromotion;
    }

    /**
     * Set the description of the ad
     * @param descriptionPromotion
     */
    public void setDescriptionPromotion(String descriptionPromotion) {
        this.descriptionPromotion = descriptionPromotion;
    }

    /**
     *
     * @return the price of the ad
     */
    public int getPrice() {
        return price;
    }

    /**
     * Set the price of the ad
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
        return partnerId;
    }

    /**
     * Set the partner id who propose this ad
     * @param partnerId
     */
    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    /**
     *
     * @return the ingredient id the ad is about
     */
    public int getIngredientId() {
        return ingredientId;
    }

    /**
     * Set the ingredient id the ad is about
     * @param ingredientId
     */
    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id.isEmpty()) ? 0 : id.hashCode());
        result = prime * result + ((descriptionPromotion == null) ? 0 : descriptionPromotion.hashCode());
        result = prime * result + price;
        result = prime * result + partnerId;
        result = prime * result + ingredientId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ad) {
            Ad other = (Ad) obj;
            return this.hashCode() == other.hashCode();
        }
        return false;
    }

}
