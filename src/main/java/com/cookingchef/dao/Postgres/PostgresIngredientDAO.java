package com.cookingchef.dao.Postgres;


import com.cookingchef.dao.IngredientDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.Ingredient;
import com.cookingchef.model.IngredientDbFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class PostgresIngredientDAO implements IngredientDAO {
    private static AtomicReference<PostgresIngredientDAO> instance = new AtomicReference<>();

    private PostgresIngredientDAO() {
    }

    public static IngredientDAO getPostgresIngredientDAO() {
        instance.compareAndSet(null, new PostgresIngredientDAO());
        return instance.get();
    }

    @Override
    public ArrayList<Ingredient> getAllIngredients() throws SQLException {
        var query = "SELECT * FROM ingredient";
        var conn = ConnectionManager.getConnection();

        ArrayList<Ingredient> ingredients;
        try (var stmt = conn.prepareStatement(query)) {

            var rs = stmt.executeQuery();

            ingredients = new ArrayList<Ingredient>();

            while (rs.next()) {
                ingredients.add(new Ingredient(
                        rs.getInt(IngredientDbFields.ID.value),
                        rs.getString(IngredientDbFields.NAME.value),
                        rs.getString(IngredientDbFields.IMAGE.value),
                        rs.getBoolean(IngredientDbFields.ALLERGEN.value)));
            }
        }
        return ingredients;
    }

    @Override
    public void createIngredient(String name, byte[] img, Boolean allergen) throws SQLException {
        var query = "INSERT INTO ingredient (name, image, allergen) VALUES (?, ?, ?)";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setBytes(2, img);
            stmt.setBoolean(3, allergen);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteIngredient(int idIngredient) throws SQLException {
        var query = "DELETE FROM ingredient WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idIngredient);
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateIngredient(int idIngredient, String nameIngredient, byte[] imageIngredient, Boolean allergenIngredient) throws SQLException {
        var query = "UPDATE ingredient SET name = ?, image = ?, allergen = ? WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nameIngredient);
            stmt.setBytes(2, imageIngredient);
            stmt.setBoolean(3, allergenIngredient);
            stmt.setInt(4, idIngredient);
            stmt.executeUpdate();
        }
    }
}
