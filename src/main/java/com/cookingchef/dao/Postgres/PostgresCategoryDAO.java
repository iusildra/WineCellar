package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.CategoryDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.Category;
import com.cookingchef.model.CategoryDbFields;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class PostgresCategoryDAO implements CategoryDAO {
    private static final AtomicReference<PostgresCategoryDAO> instance = new AtomicReference<>();

    private PostgresCategoryDAO() {
    }

    public static CategoryDAO getPostgresCategoryDAO() {
        instance.compareAndSet(null, new PostgresCategoryDAO());
        return instance.get();
    }

    @Override
    public ArrayList<Pair<String, Category>> getAllCategories() throws SQLException {
        var query = "SELECT 'Ingredient' as source, * FROM ingredient_category UNION (SELECT 'Recette' as source, * FROM recipe_category) UNION (SELECT 'Suggestion' as source, * FROM suggestion_category)";
        var conn = ConnectionManager.getConnection();

        ArrayList<Pair<String, Category>> categories;
        try (var stmt = conn.prepareStatement(query)) {

            var rs = stmt.executeQuery();

            categories = new ArrayList<Pair<String, Category>>();

            while (rs.next()) {
                categories.add(new Pair<>(rs.getString("source"),
                        new Category(
                        rs.getInt(CategoryDbFields.ID.value),
                        rs.getString(CategoryDbFields.NAME.value))));
            }
        }
        return categories;
    }

    @Override
    public boolean createCategory(String tableCategory, String nameCategory) throws SQLException {
        if (this.isAlreadyExist(tableCategory, nameCategory)) {
            return false;
        } else {
            var query = "INSERT INTO ? (name) VALUES (?)";
            var conn = ConnectionManager.getConnection();

            try (var stmt = conn.prepareStatement(query)) {
                stmt.setString(1, tableCategory);
                stmt.setString(2, nameCategory);
                stmt.executeUpdate();
                return true;
            }
        }
    }

    @Override
    public void deleteCategory(String tableCategory, int idCategory) throws SQLException {
        var query = "DELETE FROM ? WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tableCategory);
            stmt.setInt(2, idCategory);
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean updateCategory(String tableCategory, int idCategory, String nameCategory) throws SQLException {
        if (this.isAlreadyExist(tableCategory, nameCategory)) {
            return false;
        } else {
            var query = "UPDATE ? SET name = ? WHERE id = ?";
            var conn = ConnectionManager.getConnection();

            try (var stmt = conn.prepareStatement(query)) {
                stmt.setString(1, tableCategory);
                stmt.setString(2, nameCategory);
                stmt.setInt(3, idCategory);
                stmt.executeUpdate();
                return true;
            }
        }
    }

    @Override
    public boolean isAlreadyExist(String tableCategory, String nameCategory) throws SQLException {
        var query = "SELECT * FROM ? WHERE name = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tableCategory);
            stmt.setString(2, nameCategory);
            var rs = stmt.executeQuery();
            return rs.next();
        }
    }

}
