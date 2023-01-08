package com.cookingchef.facade;

import com.cookingchef.dao.CategoryDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Category;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryFacade {
    private static CategoryFacade instance;
    private CategoryDAO categoryDAO;

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

    public ArrayList<Category> getAllCategories() throws SQLException {
        return this.categoryDAO.getAllCategories();
    }

    public void createCategory(String nameCategory) throws SQLException {
        this.categoryDAO.createCategory(nameCategory);
    }

    public void deleteCategory(int idCategory) throws SQLException {
        this.categoryDAO.deleteCategory(idCategory);
    }

    public void updateCategory(int idCategory, String nameCategory) throws SQLException {
        this.categoryDAO.updateCategory(idCategory, nameCategory);
    }

}
