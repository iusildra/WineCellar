package com.cookingchef.dao;

import com.cookingchef.model.Category;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CategoryDAO {

    public ArrayList<Category> getAllCategories() throws SQLException;
    public void createCategory(String nameCategory) throws SQLException;
    public void deleteCategory(int idCategory) throws SQLException;
    public void updateCategory(int idCategory, String nameCategory) throws SQLException;

}
