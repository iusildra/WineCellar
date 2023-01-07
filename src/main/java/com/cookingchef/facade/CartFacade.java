package com.cookingchef.facade;

import com.cookingchef.dao.CartDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Cart;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class CartFacade {
    private static AtomicReference<CartFacade> instance = new AtomicReference<>();
    private final CartDAO cartDAO;

    protected CartFacade() {
        var factory = new PostgresFactory();
        this.cartDAO = factory.getCartDAO();
    }

    public static CartFacade getCartFacade() {
        instance.compareAndSet(null, new CartFacade());
        return instance.get();
    }

    public Optional<Cart> addElementIntoCart(Cart cart) throws SQLException {
        var newId = cartDAO.addElementIntoCartInDb(cart);

        if (newId.isPresent())
            return cartDAO.getCartById(newId.get());

        return Optional.empty();
    }

    public void deleteCart(Cart cart) throws SQLException {
        cartDAO.removeCartFromDb(cart);
    }

    public void updateCart(Cart cart) throws SQLException {
        cartDAO.updateCartInDb(cart);
    }

    public Optional<Cart> getCartById(int id) throws SQLException {
        return cartDAO.getCartById(id);
    }

    public List<Cart> getCartsByUser(int userId) throws SQLException {
        return cartDAO.getCartsByUser(userId);
    }
}
