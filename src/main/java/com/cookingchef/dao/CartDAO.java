package com.cookingchef.dao;

import com.cookingchef.model.Cart;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CartDAO {
    /**
     * Add element to a cart in the database
     *
     * @param cart The cart object that you want to register.
     * @return The id of the newly created cart
     */
    public Optional<Integer> addElementIntoCartInDb(Cart cart) throws SQLException;

    /**
     * Update the quantity of element of the cart in the database.
     *
     * @param cart The cart object that you want to update in the database.
     */
    public void updateCartInDb(Cart cart) throws SQLException;

    /**
     * Removes an element of the cart from the database.
     *
     * @param cart The cart object to be removed from the database.
     */
    public void removeCartFromDb(Cart cart) throws SQLException;

    /**
     * Get a cart by id
     *
     * @param id The id of the cart you want to get.
     * @return The cart if it exists, otherwise, returns an empty option
     */
    public Optional<Cart> getCartById(int id) throws SQLException;

    /**
     * Returns a list of carts whose user matches the given user.
     *
     * @param userId The user id of the user you want to search for.
     * @return A list of carts with the user specified.
     */
    public List<Cart> getCartsByUser(int userId) throws SQLException;
}
