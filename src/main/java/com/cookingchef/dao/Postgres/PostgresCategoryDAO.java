package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.CategoryDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.Category;
import com.cookingchef.model.CategoryDbFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class PostgresCategoryDAO implements CategoryDAO {
    private static AtomicReference<PostgresCategoryDAO> instance = new AtomicReference<>();

    private PostgresCategoryDAO() {
    }

    public static CategoryDAO getPostgresCategoryDAO() {
        instance.compareAndSet(null, new PostgresCategoryDAO());
        return instance.get();
    }

        public ArrayList<Category> getAllCategories() throws SQLException {
            var query = "SELECT * FROM category";
            var conn = ConnectionManager.getConnection();

            ArrayList<Category> categories;
            try (var stmt = conn.prepareStatement(query)) {

                var rs = stmt.executeQuery();

                categories = new ArrayList<Category>();

                while (rs.next()) {
                    categories.add(new Category(
                            rs.getInt(CategoryDbFields.ID.value),
                            rs.getString(CategoryDbFields.NAME.value)));
                }
            }
            return categories;
        }

        public void createCategory(String nameCategory) throws SQLException {
            // TODO : verif existe pas deja
            var query = "INSERT INTO category (name) VALUES (?)";
            var conn = ConnectionManager.getConnection();

            try (var stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nameCategory);
                stmt.executeUpdate();
            }
        }

        public void deleteCategory(int idCategory) throws SQLException {
            var query = "DELETE FROM category WHERE id = ?";
            var conn = ConnectionManager.getConnection();

            try (var stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idCategory);
                stmt.executeUpdate();
            }
        }

        public void updateCategory(int idCategory, String nameCategory) throws SQLException {
        // TODO : vérif nom existe pas déjà
            var query = "UPDATE category SET name = ? WHERE id = ?";
            var conn = ConnectionManager.getConnection();

            try (var stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nameCategory);
                stmt.setInt(2, idCategory);
                stmt.executeUpdate();
            }
        }

        public Boolean isAlreadyExist(String nameCategory) throws SQLException {
            var query = "SELECT * FROM category WHERE name = ?";
            var conn = ConnectionManager.getConnection();

            try (var stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nameCategory);
                var rs = stmt.executeQuery();
                return rs.next();
            }
        }

}
