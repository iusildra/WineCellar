package com.cookingchef.dao;

import com.cookingchef.model.MealCategory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MealCategoryDAO {
    /**
     * Returns a list of MealCategory objects.
     *
     * @return A list of MealCategory objects.
     */
    List<Optional<MealCategory>> getAllMealCategories() throws SQLException;

    /**
     * Returns a MealCategory object with the given ID.
     *
     * @param id The ID of the MealCategory object.
     * @return A MealCategory object.
     */
    Optional<MealCategory> getMealCategoryById(int id) throws SQLException;

    /**
     * Returns a MealCategory object with the given name.
     *
     * @param name The name of the MealCategory object.
     * @return A MealCategory object.
     */
    Optional<MealCategory> getMealCategoryByName(String name) throws SQLException;
}

