package com.cookingchef.dao.Postgres;


import com.cookingchef.dao.IngredientDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.Ingredient;
import com.cookingchef.model.IngredientDbFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class PostgresIngredientDAO implements IngredientDAO {
    private static final AtomicReference<PostgresIngredientDAO> instance = new AtomicReference<>();

    private PostgresIngredientDAO() {
    }

    public static IngredientDAO getPostgresIngredientDAO() {
        instance.compareAndSet(null, new PostgresIngredientDAO());
        return instance.get();
    }

    @Override
    public boolean ingredientAlreadyExist(String name) throws SQLException{
        var query = "SELECT * FROM ingredient WHERE name = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            var rs = stmt.executeQuery();

            return rs.next();
        }
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
                        rs.getBytes(IngredientDbFields.IMAGE.value),
                        rs.getBoolean(IngredientDbFields.ALLERGEN.value)));
            }
        }
        return ingredients;
    }

    @Override
    public boolean createIngredient(String name, byte[] img, Boolean allergen) throws SQLException {
        if (this.ingredientAlreadyExist(name)) {
            return false;
        } else {
            var query = "INSERT INTO ingredient (name, src, allergen) VALUES (?, ?, ?)";
            var conn = ConnectionManager.getConnection();

            try (var stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setBytes(2, img);
                stmt.setBoolean(3, allergen);
                stmt.executeUpdate();
                return true;
            }
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
    public boolean updateIngredient(int idIngredient, String nameIngredient, byte[] imageIngredient, Boolean allergenIngredient) throws SQLException {
        if (this.ingredientAlreadyExist(nameIngredient)) {
            return false;
        } else {
            var query = "UPDATE ingredient SET name = ?, src = ?, allergen = ? WHERE id = ?";
            var conn = ConnectionManager.getConnection();

            try (var stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nameIngredient);
                stmt.setBytes(2, imageIngredient);
                stmt.setBoolean(3, allergenIngredient);
                stmt.setInt(4, idIngredient);
                stmt.executeUpdate();

                return true;
            }
        }
    }

    @Override
    public Ingredient getIngredientById(int idIngredient) throws SQLException {
        var query = "SELECT * FROM ingredient WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try(var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idIngredient);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                return new Ingredient(
                        rs.getInt(IngredientDbFields.ID.value),
                        rs.getString(IngredientDbFields.NAME.value),
                        rs.getBytes(IngredientDbFields.IMAGE.value),
                        rs.getBoolean(IngredientDbFields.ALLERGEN.value)
                );
            } else {
                return null;
            }
        }
    }
}
