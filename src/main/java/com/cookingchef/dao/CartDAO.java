package com.cookingchef.dao;

import com.cookingchef.model.CartEntry;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CartDAO {
    /**
     * Add element to a cart in the database
     *
     * @param cartEntry The cart object that you want to register.
     * @return The id of the newly created cart
     */
    public void addElementIntoCartInDb(CartEntry cartEntry) throws SQLException;

    /**
     * Update the quantity of element of the cart in the database.
     *
     * @param cartEntry The cart object that you want to update in the database.
     */
    public void updateCartInDb(CartEntry cartEntry) throws SQLException;

    /**
     * Removes an element of the cart from the database.
     *
     * @param cartEntry The cart object to be removed from the database.
     */
    public void removeCartFromDb(CartEntry cartEntry) throws SQLException;

    /**
     * Get a cart by id
     *
     * @param ingredientId
     * @param userId
     * @return The cart if it exists, otherwise, returns an empty option
     * @throws SQLException
     */
    public Optional<CartEntry> getCartById(int ingredientId, int userId) throws SQLException;

    /**
     * Returns the cart whose user matches the given user.
     *
     * @param userId The user id of the user you want to search for.
     * @return The cart with the user specified.
     */
    public List<CartEntry> getCartByUser(int userId) throws SQLException;
}
