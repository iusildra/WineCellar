package com.cookingchef.facade;

import com.cookingchef.dao.CategoryDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Category;
import com.cookingchef.model.CategoryDb;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The type Category facade.
 */
public class CategoryFacade {
    private static final AtomicReference<CategoryFacade> instance = new AtomicReference<>();
    private final CategoryDAO categoryDAO;

    private CategoryFacade() {
        var factory = new PostgresFactory();
        this.categoryDAO = factory.getCategoryDAO();
    }

    /**
     * Gets category facade.
     *
     * @return the category facade
     */
    public static CategoryFacade getCategoryFacade() {
        instance.compareAndSet(null, new CategoryFacade());
        return instance.get();
    }

    /**
     * Gets all categories.
     *
     * @return the all categories
     * @throws SQLException the sql exception
     */
    public List<Pair<CategoryDb, Category>> getAllCategories() throws SQLException {
        return this.categoryDAO.getAllCategories();
    }

    /**
     * Create category boolean.
     *
     * @param tableCategory the table category
     * @param nameCategory  the name category
     * @return the boolean
     * @throws SQLException the sql exception
     */
    public boolean createCategory(CategoryDb tableCategory, String nameCategory) throws SQLException {
        return this.categoryDAO.createCategory(tableCategory, nameCategory);
    }

    /**
     * Delete category.
     *
     * @param tableCategory the table category
     * @param idCategory    the id category
     * @throws SQLException the sql exception
     */
    public void deleteCategory(CategoryDb tableCategory, int idCategory) throws SQLException {
        this.categoryDAO.deleteCategory(tableCategory, idCategory);
    }

    /**
     * Update category boolean.
     *
     * @param tableCategory the table category
     * @param idCategory    the id category
     * @param nameCategory  the name category
     * @return the boolean
     * @throws SQLException the sql exception
     */
    public boolean updateCategory(CategoryDb tableCategory, int idCategory, String nameCategory) throws SQLException {
        return this.categoryDAO.updateCategory(tableCategory, idCategory, nameCategory);
    }

    /**
     * Gets categories id by names.
     *
     * @param categories the categories
     * @return the categories id by names
     * @throws SQLException the sql exception
     */
    public List<Integer> getCategoriesIdByNames(List<String> categories) throws SQLException{
        return this.categoryDAO.getCategoriesIdByNames(categories);
    }

    /**
     * Gets category recipe by id.
     *
     * @param idCategory the id category
     * @return the category recipe by id
     * @throws SQLException the sql exception
     */
    public Category getCategoryRecipeById(int idCategory) throws SQLException {
        return this.categoryDAO.getCategoryRecipeById(idCategory);
    }
}
