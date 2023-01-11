package com.cookingchef.dao;

import com.cookingchef.model.Category;
import com.cookingchef.model.CategoryDb;

import javafx.util.Pair;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface Category dao.
 */
public interface CategoryDAO {

    /**
     * Gets all categories.
     *
     * @return the all categories
     * @throws SQLException the sql exception
     */
    List<Pair<CategoryDb, Category>> getAllCategories() throws SQLException;

    /**
     * Create category boolean.
     *
     * @param tableCategory the table category
     * @param nameCategory  the name category
     * @return the boolean
     * @throws SQLException the sql exception
     */
    boolean createCategory(CategoryDb tableCategory, String nameCategory) throws SQLException;

    /**
     * Delete category.
     *
     * @param tableCategory the table category
     * @param idCategory    the id category
     * @throws SQLException the sql exception
     */
    void deleteCategory(CategoryDb tableCategory, int idCategory) throws SQLException;

    /**
     * Update category boolean.
     *
     * @param tableCategory the table category
     * @param idCategory    the id category
     * @param nameCategory  the name category
     * @return the boolean
     * @throws SQLException the sql exception
     */
    boolean updateCategory(CategoryDb tableCategory, int idCategory, String nameCategory) throws SQLException;

    /**
     * Is already exist boolean.
     *
     * @param tableCategory the table category
     * @param nameCategory  the name category
     * @return the boolean
     * @throws SQLException the sql exception
     */
    boolean isAlreadyExist(CategoryDb tableCategory, String nameCategory) throws SQLException;

    /**
     * Gets categories id by names.
     *
     * @param categoriesNames the categories names
     * @return the categories id by names
     * @throws SQLException the sql exception
     */
    List<Integer> getCategoriesIdByNames(List<String> categoriesNames) throws SQLException;

    /**
     * Gets category recipe by id.
     *
     * @param idCategory the id category
     * @return the category recipe by id
     * @throws SQLException the sql exception
     */
    Category getCategoryRecipeById(int idCategory) throws SQLException;
}
