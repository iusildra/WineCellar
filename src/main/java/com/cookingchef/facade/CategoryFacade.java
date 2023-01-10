package com.cookingchef.facade;

import com.cookingchef.dao.CategoryDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Category;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryFacade {
    private static CategoryFacade instance;
    private final CategoryDAO categoryDAO;

    private CategoryFacade() {
        var factory = new PostgresFactory();
        this.categoryDAO = factory.getCategoryDAO();
    }

    public static CategoryFacade getCategoryFacade() {
        if (instance == null) {
            instance = new CategoryFacade();
        }
        return instance;
    }

    public ArrayList<Pair<String, Category>> getAllCategories() throws SQLException {
        return this.categoryDAO.getAllCategories();
    }

    public boolean createCategory(String tableCatgory, String nameCategory) throws SQLException {
        return this.categoryDAO.createCategory(tableCatgory, nameCategory);
    }

    public void deleteCategory(String tableCategory, int idCategory) throws SQLException {
        this.categoryDAO.deleteCategory(tableCategory, idCategory);
    }

    public boolean updateCategory(String tableCategory, int idCategory, String nameCategory) throws SQLException {
        return this.categoryDAO.updateCategory(tableCategory, idCategory, nameCategory);
    }

}
