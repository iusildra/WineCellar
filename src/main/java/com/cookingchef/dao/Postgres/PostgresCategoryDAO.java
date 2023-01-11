package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.CategoryDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.Category;
import com.cookingchef.model.CategoryDb;
import com.cookingchef.model.CategoryDbFields;
import com.cookingchef.model.IngredientDbFields;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * The type Postgres category dao.
 */
public class PostgresCategoryDAO implements CategoryDAO {
    private static final AtomicReference<PostgresCategoryDAO> instance = new AtomicReference<>();

    private PostgresCategoryDAO() {
    }

    /**
     * Gets postgres category dao.
     *
     * @return the postgres category dao
     */
    public static CategoryDAO getPostgresCategoryDAO() {
        instance.compareAndSet(null, new PostgresCategoryDAO());
        return instance.get();
    }

    @Override
    public List<Pair<CategoryDb, Category>> getAllCategories() throws SQLException {
        var query = "SELECT 'INGREDIENT' as source, * FROM ingredient_category UNION (SELECT 'RECIPE' as source, * FROM recipe_category) UNION (SELECT 'SUGGESTION' as source, * FROM suggestion_category)";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {

            var rs = stmt.executeQuery();
            ArrayList<Pair<CategoryDb, Category>> categories = new ArrayList<>();

            while (rs.next()) {
                categories.add(new Pair<>(
                        CategoryDb.valueOf(rs.getString("source")),
                        new Category(
                                rs.getInt(CategoryDbFields.ID.value),
                                rs.getString(CategoryDbFields.NAME.value))));
            }
            return categories;
        }
    }

    @Override
    public boolean createCategory(CategoryDb tableCategory, String nameCategory)
            throws SQLException, IllegalArgumentException {
        if (this.isAlreadyExist(tableCategory, nameCategory)) {
            throw new IllegalArgumentException("Category name already exist");
        }

        var query = "INSERT INTO " + tableCategory.value + " (name) VALUES (?)";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nameCategory);
            stmt.executeUpdate();
            return true;
        }
    }

    @Override
    public void deleteCategory(CategoryDb tableCategory, int idCategory) throws SQLException {
        var query = "DELETE FROM " + tableCategory.value + " WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idCategory);
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean updateCategory(CategoryDb tableCategory, int idCategory, String nameCategory) throws SQLException {
        if (this.isAlreadyExist(tableCategory, nameCategory)) {
            return false;
        } else {
            var query = "UPDATE " + tableCategory.value + " SET name = ? WHERE id = ?";
            var conn = ConnectionManager.getConnection();

            try (var stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nameCategory);
                stmt.setInt(2, idCategory);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
                return true;
            }
        }
    }

    @Override
    public boolean isAlreadyExist(CategoryDb tableCategory, String nameCategory) throws SQLException {
        var query = "SELECT * FROM " + tableCategory.value + " WHERE name = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nameCategory);
            var rs = stmt.executeQuery();
            return rs.next();
        }
    }

    @Override
    public List<Integer> getCategoriesIdByNames(List<String> categoriesNames) throws SQLException {
        var queryArgs = categoriesNames.stream()
                .distinct()
                .map(x -> "LOWER(name) LIKE ?")
                .collect(Collectors.joining(" OR "));
        var query = "SELECT id FROM recipe_category WHERE " + queryArgs;


        var conn = ConnectionManager.getConnection();
        List<Integer> categoriesId = new ArrayList<>();

        try(var stmt = conn.prepareStatement(query)) {
            for (int i = 0; i < categoriesNames.size(); i++) {
                stmt.setString(i + 1, "%" + categoriesNames.get(i).toLowerCase() + "%");
            }
            var rs = stmt.executeQuery();

            while (rs.next()) {
                categoriesId.add(rs.getInt(IngredientDbFields.ID.value));
            }
        }
        return categoriesId;
    }

    @Override
    public Category getCategoryRecipeById(int idCategory) throws SQLException {
        var query = "SELECT * FROM recipe_category WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idCategory);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                return new Category(
                        rs.getInt(CategoryDbFields.ID.value),
                        rs.getString(CategoryDbFields.NAME.value));
            }
            return null;
        }
    }
}
