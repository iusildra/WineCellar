package com.cookingchef.model;

import java.util.ArrayList;
import java.util.Optional;

public class RecipeList {
    private Optional<Integer> id = Optional.empty();
    private String name;
    private ArrayList<Recipe> recipeList;
    private boolean isFav = false;
    // TODO: rajouter la liste de recettes ? + l'ajouter dans la query createRLInDb ?

    /**
     * Constructor for a new RecipeList.
     * @param name The name of the RecipeList
     */
    public RecipeList(String name) {
        this.name = name;
        this.recipeList = new ArrayList<Recipe>();
    }

    /**
     * Constructor for a new RecipeList.
     * @param name The name of the RecipeList
     * @param recipeList An existant list of recipes.
     */
    public RecipeList(String name, ArrayList<Recipe> recipeList) {
        this.name = name;
        this.recipeList = recipeList;
    }

    /**
     * @return the id
     */
    public Optional<Integer> getId() {
        return id;
    }

    /**
     * Set the ID only once, if it is not already set.
     *
     * @param id The id of the object.
     */
    public void setId(int id) {
        if (this.id.isEmpty())
            this.id = Optional.of(id);
    }

    /**
     * @return the name of the RecipeList
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the list of recipes
     */
    public ArrayList<Recipe> getRecipeList() {
        return recipeList;
    }

    /**
     * @param recipeList the RecipeList to set
     */
    public void setRecipeList(ArrayList<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    /**
     *
     */
    public void addARecipe(Recipe recipe) {
        this.recipeList.add(recipe);
    }

    /**
     * @return true if this RecipeList is favorite, false otherwise.
     */
    public boolean isFav() {
        return isFav;
    }

    /**
     * @param fav true if this RecipeList is favorite, false otherwise.
     */
    public void setFav(boolean fav) {
        isFav = fav;
    }
}
