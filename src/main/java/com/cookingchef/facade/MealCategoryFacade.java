package com.cookingchef.facade;

import com.cookingchef.dao.MealCategoryDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.MealCategory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class MealCategoryFacade {
    private static AtomicReference<MealCategoryFacade> instance = new AtomicReference<>();
    private final MealCategoryDAO mealCategoryDAO;

    protected MealCategoryFacade() {
        var factory = new PostgresFactory();
        this.mealCategoryDAO = factory.getMealCategoryDAO();
    }

    public static MealCategoryFacade getMealCategoryFacade() {
        instance.compareAndSet(null, new MealCategoryFacade());
        return instance.get();
    }

    public List<Optional<MealCategory>> getAllMealCategories() throws SQLException {
        return mealCategoryDAO.getAllMealCategories();
    }

    public Optional<MealCategory> getMealCategoryById(int id) throws SQLException {
        return mealCategoryDAO.getMealCategoryById(id);
    }

    public Optional<MealCategory> getMealCategoryByName(String name) throws SQLException {
        return mealCategoryDAO.getMealCategoryByName(name);
    }
}
