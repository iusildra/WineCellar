package com.cookingchef.recipelist;

import com.cookingchef.dao.Postgres.PostgresRecipeListDAO;
import com.cookingchef.dao.RecipeListDAO;
import com.cookingchef.model.RecipeList;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

class DAOTests {
    private RecipeListDAO recipeListDAO;

    public DAOTests() {
        this.recipeListDAO = PostgresRecipeListDAO.getPostgresRecipeListDAO();
    }

    @Test
    void createRecipeListInDb() throws SQLException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("name", "desc", "summary", "src"));
        recipes.add(new Recipe("name2", "desc", "summary", "src"));
        var recipeList = new RecipeList("Name", recipes);
        recipeListDAO.createRecipeListInDb(1, recipeList);

        var newRecipeList = recipeListDAO.getRecipeListById(recipeList.getId().get());
        assert newRecipeList.get().equals(recipeList);

        recipeListDAO.removeRecipeListFromDb(recipeList);
    }

    @Test
    void removeRecipeListFromDb() throws SQLException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("name", "desc", "summary", "src"));
        recipes.add(new Recipe("name2", "desc", "summary", "src"));
        var recipeList = new RecipeList("Name", recipes);
        recipeListDAO.createRecipeListInDb(1, recipeList);

        recipeListDAO.removeRecipeListFromDb(recipeList);

        var newRecipeList = recipeListDAO.getRecipeListById(recipeList.getId().get());

        assert newRecipeList.isEmpty();
    }
}