package com.cookingchef.model;

import java.util.Optional;

public class Cart {
    private Optional<Integer> id = Optional.empty();
    private CartEntry[] cartEntries;
    private User user;

    /**
     *
     * @param id
     * @param cartEntries
     * @param user
     */
    public Cart(int id, CartEntry[] cartEntries, User user) {
        this.id = Optional.of(id);
        this.cartEntries = cartEntries;
        this.user = user;
    }

    /**
     *
     * @param cartEntries
     * @param user
     */
    public Cart(CartEntry[] cartEntries, User user) {
        this.cartEntries = cartEntries;
        this.user = user;
    }

    /**
     *
     * @return the id of the cart
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
     * @return the list of the entries of the cart
     */
    public CartEntry[] getCartEntries() {
        return cartEntries;
    }

    /**
     *
     * @param cartEntries
     */
    public void setCartEntries(CartEntry[] cartEntries) {
        this.cartEntries = cartEntries;
    }

    /**
     *
     * @return the cart user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
