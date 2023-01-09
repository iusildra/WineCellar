package com.cookingchef.facade;

import com.cookingchef.dao.RecipeListDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.RecipeList;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class RecipeListFacade {
    private static AtomicReference<RecipeListFacade> instance = new AtomicReference<>();
    private final RecipeListDAO recipeListDAO;

    protected RecipeListFacade() {
        var factory = new PostgresFactory();
        this.recipeListDAO = factory.getRecipeListDAO();
    }

    public static RecipeListFacade getRecipeListFacade() {
        instance.compareAndSet(null, new RecipeListFacade());
        return instance.get();
    }

    public Optional<RecipeList> addRecipeList(int userID, RecipeList recipeList) throws SQLException {
        var newId = recipeListDAO.createRecipeListInDb(userID, recipeList);

        if (newId.isPresent())
            return recipeListDAO.getRecipeListById(newId.get());

        return Optional.empty();
    }

    public void deleteRecipeList(RecipeList recipeList) throws SQLException {
        recipeListDAO.removeRecipeListFromDb(recipeList);
    }

    public Optional<RecipeList> getRecipeListById(int id) throws SQLException {
        return recipeListDAO.getRecipeListById(id);
    }

    public List<Optional<RecipeList>> getAllRecipeLists(int userID) throws SQLException {
        return recipeListDAO.getAllRecipeListByUserId(userID);
    }
}
