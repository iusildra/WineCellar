package com.cookingchef.dao;

import com.cookingchef.model.Category;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CategoryDAO {

    ArrayList<Pair<String, Category>> getAllCategories() throws SQLException;
    boolean createCategory(String tableCategory, String nameCategory) throws SQLException;
    void deleteCategory(String tableCategory, int idCategory) throws SQLException;
    boolean updateCategory(String tableCategory, int idCategory, String nameCategory) throws SQLException;
    boolean isAlreadyExist(String tableCategory, String nameCategory) throws SQLException;

}
