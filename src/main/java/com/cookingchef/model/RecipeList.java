package com.cookingchef.model;

import java.util.ArrayList;
import java.util.Optional;

public class RecipeList {
    private Optional<Integer> id = Optional.empty();
    private String name;
    private ArrayList<Recipe> recipeList;
    private boolean isFav = false;

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
     * @param id The ID of the RecipeList.
     */
    public RecipeList(int id, String name) {
        this.id = Optional.of(id);
        this.name = name;
    }

    /**
     * Constructor for a new RecipeList.
     * @param name The name of the RecipeList
     * @param recipeList An existant list of recipes.
     * @param isFav Whether the RecipeList is favorite or not.
     * @param id The ID of the RecipeList.
     */
    public RecipeList(int id, String name, ArrayList<Recipe> recipeList, boolean isFav) {
        this.id = Optional.of(id);
        this.name = name;
        this.recipeList = recipeList;
        this.isFav = isFav;
    }

    /**
     * Constructor for a new RecipeList.
     * @param name The name of the RecipeList
     * @param recipeList An existant list of recipes.
     * @param isFav Whether the RecipeList is favorite or not.
     */
    public RecipeList(String name, ArrayList<Recipe> recipeList, boolean isFav) {
        this.name = name;
        this.recipeList = recipeList;
        this.isFav = isFav;
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
     * Add a recipe into the RecipeList.
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

    @Override
    public int hashCode() {
        return this.id.hashCode() + this.name.hashCode() + this.recipeList.hashCode() /*+ this.isFav.hashCode()*/; //TODO: idk if an hashCode exist for a boolean
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RecipeList) {
            var other = (RecipeList) obj;
            return this.hashCode() == other.hashCode();
        }
        return false;
    }
}
