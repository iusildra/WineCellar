package com.cookingchef.dao;

import com.cookingchef.model.Category;
import com.cookingchef.model.CategoryDb;

import javafx.util.Pair;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {

    /**
     * Get all categories from the database
     * @return List of categories
     * @throws SQLException
     */
    List<Pair<CategoryDb, Category>> getAllCategories() throws SQLException;

    /**
     * Add a category into the database
     * @param tableCategory
     * @param nameCategory
     * @return
     * @throws SQLException
     */
    boolean createCategory(CategoryDb tableCategory, String nameCategory) throws SQLException;

    /**
     * Delete a category
     * @param tableCategory
     * @param idCategory
     * @throws SQLException
     */
    void deleteCategory(CategoryDb tableCategory, int idCategory) throws SQLException;

    /**
     * Update a category
     * @param tableCategory
     * @param idCategory
     * @param nameCategory
     * @return
     * @throws SQLException
     */
    boolean updateCategory(CategoryDb tableCategory, int idCategory, String nameCategory) throws SQLException;

    /**
     * Return if the category already exist in the database
     * @param tableCategory
     * @param nameCategory
     * @return
     * @throws SQLException
     */
    boolean isAlreadyExist(CategoryDb tableCategory, String nameCategory) throws SQLException;

}
