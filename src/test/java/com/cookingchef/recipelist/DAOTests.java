package com.cookingchef.recipelist;

import com.cookingchef.dao.Postgres.PostgresRecipeListDAO;
import com.cookingchef.dao.RecipeListDAO;
import com.cookingchef.model.RecipeList;
import com.cookingchef.model.Recipe;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

class DAOTests {
    private RecipeListDAO recipeListDAO;

    public DAOTests() {
        this.recipeListDAO = PostgresRecipeListDAO.getPostgresRecipeListDAO();
    }

    // @Test
    // void createRecipeListInDb() throws SQLException {
    //     ArrayList<Recipe> recipes = new ArrayList<>();
    //     recipes.add(new Recipe("name", "desc", "summary", 2));
    //     recipes.add(new Recipe("name2", "desc", "summary", 2));
    //     var recipeList = new RecipeList("Name", recipes);
    //     recipeListDAO.createRecipeListInDb(0, recipeList);

    //     var newRecipeList = recipeListDAO.getRecipeListById(recipeList.getId().get());
    //     assert newRecipeList.get().equals(recipeList);

    //     recipeListDAO.removeRecipeListFromDb(recipeList);
    // }

    @Test
    void removeRecipeListFromDb() throws SQLException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("name", "desc", "summary", 2));
        recipes.add(new Recipe("name2", "desc", "summary", 2));
        var recipeList = new RecipeList("Name", recipes);
        recipeListDAO.createRecipeListInDb(0, recipeList);

        recipeListDAO.removeRecipeListFromDb(recipeList);

        var newRecipeList = recipeListDAO.getRecipeListById(recipeList.getId().get());

        assert newRecipeList.isEmpty();
    }
}