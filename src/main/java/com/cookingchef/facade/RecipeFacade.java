package com.cookingchef.facade;

import com.cookingchef.dao.RecipeDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Recipe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class RecipeFacade {
    private static AtomicReference<RecipeFacade> instance = new AtomicReference<>();
    private final RecipeDAO recipeDAO;

    protected RecipeFacade() {
        var factory = new PostgresFactory();
        this.recipeDAO = factory.getRecipeDAO();
    }

    public static RecipeFacade getRecipeFacade() {
        instance.compareAndSet(null, new RecipeFacade());
        return instance.get();
    }

    public Optional<Recipe> addRecipe(Recipe recipe) throws SQLException {
        var newId = recipeDAO.registerRecipeInDb(recipe);

        if (newId.isPresent())
            return recipeDAO.getRecipeById(newId.get());

        return Optional.empty();
    }

    public void deleteRecipe(Recipe ad) throws SQLException {
        recipeDAO.removeRecipeFromDb(ad);
    }

    public void updateRecipe(Recipe ad) throws SQLException {
        recipeDAO.updateRecipeInDb(ad);
    }

    public List<Recipe> getRecipesByName(String name) throws SQLException {
        return recipeDAO.getRecipesByName(name);
    }

    public Optional<Recipe> getRecipeById(int id) throws SQLException {
        return recipeDAO.getRecipeById(id);
    }

    public List<Recipe> getAllRecipes() throws SQLException {
        return recipeDAO.getRecipes();
    }

    public List<Recipe> getRecipesByCategories(List<Integer> categoryId) throws SQLException {
        return recipeDAO.getRecipesByCategories(categoryId);
    }

    public List<Recipe> getRecipesByIngredients(List<Integer> ingredientId) throws SQLException {
        return recipeDAO.getRecipesByIngredients(ingredientId);
    }
}
