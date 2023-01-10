package com.cookingchef.facade;

import com.cookingchef.dao.CategoryDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Category;
import com.cookingchef.model.CategoryDb;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CategoryFacade {
    private static final AtomicReference<CategoryFacade> instance = new AtomicReference<>();
    private final CategoryDAO categoryDAO;

    private CategoryFacade() {
        var factory = new PostgresFactory();
        this.categoryDAO = factory.getCategoryDAO();
    }

    public static CategoryFacade getCategoryFacade() {
        instance.compareAndSet(null, new CategoryFacade());
        return instance.get();
    }

    public List<Pair<CategoryDb, Category>> getAllCategories() throws SQLException {
        return this.categoryDAO.getAllCategories();
    }

    public boolean createCategory(CategoryDb tableCategory, String nameCategory) throws SQLException {
        return this.categoryDAO.createCategory(tableCategory, nameCategory);
    }

    public void deleteCategory(CategoryDb tableCategory, int idCategory) throws SQLException {
        this.categoryDAO.deleteCategory(tableCategory, idCategory);
    }

    public boolean updateCategory(CategoryDb tableCategory, int idCategory, String nameCategory) throws SQLException {
        return this.categoryDAO.updateCategory(tableCategory, idCategory, nameCategory);
    }

    public List<Integer> getCategoriesIdByNames(List<String> categories) throws SQLException{
        return this.categoryDAO.getCategoriesIdByNames(categories);
    }

    public Category getCategoryRecipeById(int idCategory) throws SQLException {
        return this.categoryDAO.getCategoryRecipeById(idCategory);
    }
}
