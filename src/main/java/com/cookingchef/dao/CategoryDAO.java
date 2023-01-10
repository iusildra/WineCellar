package com.cookingchef.dao;

import com.cookingchef.model.Category;
import com.cookingchef.model.CategoryDb;

import javafx.util.Pair;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {

    List<Pair<CategoryDb, Category>> getAllCategories() throws SQLException;
    boolean createCategory(CategoryDb tableCategory, String nameCategory) throws SQLException;
    void deleteCategory(CategoryDb tableCategory, int idCategory) throws SQLException;
    boolean updateCategory(CategoryDb tableCategory, int idCategory, String nameCategory) throws SQLException;
    boolean isAlreadyExist(CategoryDb tableCategory, String nameCategory) throws SQLException;

}
