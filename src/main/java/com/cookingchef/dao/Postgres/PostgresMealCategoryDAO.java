package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.MealCategoryDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.MealCategory;
import com.cookingchef.model.MealCategoryDbFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class PostgresMealCategoryDAO implements MealCategoryDAO {
    private static AtomicReference<PostgresMealCategoryDAO> instance = new AtomicReference<>();

    private PostgresMealCategoryDAO() {
    }

    /**
     * If the instance is null, create a new instance and return it.
     * Otherwise, return the existing instance.
     *
     * @return A PostgresCalendarDAO object.
     */
    public static PostgresMealCategoryDAO getPostgresMealCategoryDAO() {
        instance.compareAndSet(null, new PostgresMealCategoryDAO());
        return instance.get();
    }

    /**
     * Returns a list of MealCategory objects.
     *
     * @return A list of MealCategory objects.
     */
    @Override
    public List<Optional<MealCategory>> getAllMealCategories() throws SQLException {
        var query = "SELECT * FROM meal_category";
        var conn = ConnectionManager.getConnection();
        ArrayList<Optional<MealCategory>> toReturn = new ArrayList<>();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            while (rs.next()) {
                Optional<MealCategory> mealCategory = Optional.of(new MealCategory(
                        rs.getInt(MealCategoryDbFields.ID.value),
                        rs.getString(MealCategoryDbFields.NAME.value)
                ));
                toReturn.add(mealCategory);
            }
        }
        return toReturn;
    }

    /**
     * Returns a MealCategory object with the given ID.
     *
     * @param id The ID of the MealCategory object.
     * @return A MealCategory object.
     */
    @Override
    public Optional<MealCategory> getMealCategoryById(int id) throws SQLException {
        var query = "SELECT * FROM meal_category WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            if (rs.next()) {
                return Optional.of(new MealCategory(
                        rs.getInt(MealCategoryDbFields.ID.value),
                        rs.getString(MealCategoryDbFields.NAME.value)
                ));
            }
        }
        return Optional.empty();
    }

    /**
     * Returns a MealCategory object with the given name.
     *
     * @param name The name of the MealCategory object.
     * @return A MealCategory object.
     */
    @Override
    public Optional<MealCategory> getMealCategoryByName(String name) throws SQLException {
        var query = "SELECT * FROM meal_category WHERE name = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            if (rs.next()) {
                return Optional.of(new MealCategory(
                        rs.getInt(MealCategoryDbFields.ID.value),
                        rs.getString(MealCategoryDbFields.NAME.value)
                ));
            }
        }
        return Optional.empty();
    }
}
