package com.cookingchef.model;

import java.util.ArrayList;
import java.util.Optional;

public class RecipeList {
    private Optional<Integer> id = Optional.empty();
    private String name;
    private ArrayList<Recipe> recipeList = new ArrayList<>();
    private boolean isFav = false;

    /**
     * Constructor for a new RecipeList.
     * @param name The name of the RecipeList
     */
    public RecipeList(String name) {
        this.name = name;
        this.recipeList = new ArrayList<>();
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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id.isEmpty()) ? 0 : id.hashCode());
        result = prime * result + name.hashCode();
        result = prime * result + recipeList.hashCode();
        result = prime * result + (isFav ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        RecipeList other = (RecipeList) obj;
        if (id.isPresent() && other.id.isPresent() && id.get() != other.id.get())
            return false;

        if (!name.equals(other.name))
            return false;
        if (!name.equals(other.name))
            return false;
        if (!recipeList.equals(other.recipeList))
            return false;

        return isFav == other.isFav;
    }   
}
