package com.cookingchef.model;

import java.util.List;
import java.util.Optional;

public class Cart {
    private Optional<Integer> id = Optional.empty();
    private List<CartEntry> cartEntries;
    private User user;

    /**
     *
     * @param id
     * @param cartEntries
     * @param user
     */
    public Cart(int id, List<CartEntry> cartEntries, User user) {
        this.id = Optional.of(id);
        this.cartEntries = cartEntries;
        this.user = user;
    }

    /**
     *
     * @param cartEntries
     * @param user
     */
    public Cart(List<CartEntry> cartEntries, User user) {
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
    public List<CartEntry> getCartEntries() {
        return cartEntries;
    }

    /**
     * Set the list of the entries of the cart
     * @param cartEntries
     */
    public void setCartEntries(List<CartEntry> cartEntries) {
        this.cartEntries = cartEntries;
    }

    /**
     * Add a cart entry to the cart
     *
     * @param cartEntry
     */
    public void addCartEntry(CartEntry cartEntry){
        this.cartEntries.add(cartEntry);
    }

    /**
     * Remove the cart entry
     *
     * @param cartEntry
     */
    public void deleteCartEntry(CartEntry cartEntry){
        this.cartEntries.remove(cartEntry);
    }

    /**
     * Get the user of the cart
     * @return the cart user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user of the cart
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
