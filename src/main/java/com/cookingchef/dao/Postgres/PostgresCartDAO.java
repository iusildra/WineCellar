package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.CartDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.CartEntry;
import com.cookingchef.model.CartEntryDbFields;

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
    public void addElementIntoCartInDb(CartEntry cartEntry) throws SQLException {
        var query = "INSERT INTO cart_user(ingredient_id, user_id, quantity,unit) VALUES(?, ?, ?, ?)";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cartEntry.getIngredientId());
            stmt.setInt(2, cartEntry.getUserId());
            stmt.setDouble(3, cartEntry.getQuantity());
            stmt.setInt(4, cartEntry.getUnit());

            stmt.executeUpdate();
        }
    }

    @Override
    public void updateCartInDb(CartEntry cartEntry) throws SQLException {
        var query = "UPDATE cart_user SET quantity = ?, unit = ? WHERE ingredient_id = ? AND user_id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, cartEntry.getQuantity());
            stmt.setInt(2, cartEntry.getUnit());
            stmt.setInt(3, cartEntry.getIngredientId());
            stmt.setInt(4, cartEntry.getUserId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void removeCartFromDb(CartEntry cartEntry) throws SQLException {
        var query = "DELETE FROM cart_user WHERE ingredient_id = ? AND user_id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cartEntry.getIngredientId());
            stmt.setInt(2, cartEntry.getUserId());
            stmt.executeUpdate();
        }

    }

    @Override
    public Optional<CartEntry> getCartById(int ingredientId, int userId) throws SQLException {
        var query = "SELECT * FROM cart_user INNER JOIN ingredient ON ingredient.id = cart_user.ingredient_id WHERE ingredient_id = ? AND user_id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ingredientId);
            stmt.setInt(2, userId);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            if (rs.next()) {
                return Optional.of(
                        new CartEntry(
                                rs.getInt(CartEntryDbFields.INGREDIENT_ID.value),
                                rs.getString(CartEntryDbFields.INGREDIENT_NAME.value),
                                rs.getInt(CartEntryDbFields.USER_ID.value),
                                rs.getDouble(CartEntryDbFields.QUANTITY.value),
                                rs.getInt(CartEntryDbFields.UNIT.value)));
            }
        }

        return Optional.empty();
    }

    @Override
    public List<CartEntry> getCartByUser(int userId) throws SQLException {
        var query = "SELECT * FROM cart_user INNER JOIN ingredient on ingredient.id = cart_user.ingredient_id WHERE user_id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            var carts = new ArrayList<CartEntry>();
            while (rs.next()) {
                carts.add(
                        new CartEntry(
                                rs.getInt(CartEntryDbFields.INGREDIENT_ID.value),
                                rs.getString(CartEntryDbFields.INGREDIENT_NAME.value),
                                rs.getInt(CartEntryDbFields.USER_ID.value),
                                rs.getDouble(CartEntryDbFields.QUANTITY.value),
                                rs.getInt(CartEntryDbFields.UNIT.value)));
            }
            return carts;
        }
    }
}
