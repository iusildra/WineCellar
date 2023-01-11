package com.cookingchef.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartEntry {
    private int ingredientId;

    private String ingredientName;
    private int userId;
    private double quantity;
    private int unit;


    /**
     *
     * @param ingredientId
     * @param ingredientName
     * @param userId
     * @param quantity
     * @param unit
     */
    public CartEntry(int ingredientId,String ingredientName, int userId, double quantity, int unit) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ingredientId;
        result = prime * result + userId;
        long temp;
        temp = Double.doubleToLongBits(quantity);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + unit;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        CartEntry other = (CartEntry) obj;

        if (ingredientId != other.ingredientId)
            return false;
        if (userId != other.userId)
            return false;
        if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.quantity))
            return false;
            
        return unit == other.unit;
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(ingredientName);
    }

    public StringProperty quantityProperty() {
        return new SimpleStringProperty(String.valueOf(quantity));
    }

    public StringProperty unitProperty() {
        return new SimpleStringProperty(String.valueOf(unit));
    }
    
}
