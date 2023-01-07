package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.CartDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.Cart;
import com.cookingchef.model.CartDbFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class PostgresCartDAO implements CartDAO {
    private static AtomicReference<PostgresCartDAO> instance = new AtomicReference<>();

    private PostgresCartDAO() {
    }

    /**
     * If the instance is null, create a new instance and return it.
     * Otherwise, return the existing instance
     *
     * @return A PostgresCartDAO object.
     */
    public static PostgresCartDAO getPostgresCartDAO() {
        instance.compareAndSet(null, new PostgresCartDAO());
        return instance.get();
    }

    @Override
    public Optional<Integer> addElementIntoCartInDb(Cart cart) throws SQLException {
        var query = "INSERT INTO cart_user(ingredient_id, user_id, quantity,unit) VALUES(?, ?, ?, ?) RETURNING id";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cart.getIngredient_id());
            stmt.setInt(2, cart.getUser_id());
            stmt.setInt(3, cart.getQuantity());
            stmt.setInt(4, cart.getUnit());

            stmt.executeQuery();
            var rs = stmt.getResultSet();
            rs.next();
            var newId = rs.getInt(CartDbFields.ID.value);
            cart.setId(newId);
            return Optional.of(newId);
        }
    }

    @Override
    public void updateCartInDb(Cart cart) throws SQLException {
        var query = "UPDATE cart_user SET quantity = ? WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cart.getIngredient_id());
            stmt.setInt(2, cart.getUser_id());
            stmt.setInt(3, cart.getQuantity());
            stmt.setInt(4, cart.getUnit());
            stmt.setInt(4, cart.getId().get());
            stmt.executeUpdate();
        }
    }

    @Override
    public void removeCartFromDb(Cart cart) throws SQLException {
        var query = "DELETE FROM cart_user WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cart.getId().get());
            stmt.executeUpdate();
        }

    }

    @Override
    public Optional<Cart> getCartById(int id) throws SQLException {
        var query = "SELECT * FROM cart_user WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            if (rs.next()) {
                return Optional.of(
                        new Cart(
                                rs.getInt(CartDbFields.ID.value),
                                rs.getInt(CartDbFields.INGREDIENT_ID.value),
                                rs.getInt(CartDbFields.USER_ID.value),
                                rs.getInt(CartDbFields.QUANTITY.value),
                                rs.getInt(CartDbFields.UNIT.value)));
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Cart> getCartsByUser(int userId) throws SQLException {
        var query = "SELECT * FROM cart_user WHERE user_id LIKE ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            var carts = new ArrayList<Cart>();
            while (rs.next()) {
                carts.add(
                        new Cart(
                                rs.getInt(CartDbFields.ID.value),
                                rs.getInt(CartDbFields.INGREDIENT_ID.value),
                                rs.getInt(CartDbFields.USER_ID.value),
                                rs.getInt(CartDbFields.QUANTITY.value),
                                rs.getInt(CartDbFields.UNIT.value)));
            }
            return carts;
        }
    }
}
