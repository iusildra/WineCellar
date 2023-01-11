package com.cookingchef.model;

import com.cookingchef.facade.RecipeFacade;

import java.util.ArrayList;
import java.util.Optional;

public class Recipe {
    private Optional<Integer> id = Optional.empty();
    private String name;
    private String description;
    private String summary;
    private byte[] src;
    private int servings;
    private ArrayList<IngredientRecipe> listOfIngredients;
    private ArrayList<CategoryRecipe> listofCategories;

    public Recipe(String name, String description, String summary, int servings) {
        this.name = name;
        this.description = description;
        this.summary = summary;
        this.servings = servings;
    }

    /**
     * Constructor for Recipe
     * @param id
     * @param name
     * @param description
     * @param summary
     * @param src
     * @param servings
     * @param listOfIngredients
     * @param listofCategories
     */
    public Recipe(int id, String name, String description, String summary, byte[] src, int servings, ArrayList<IngredientRecipe> listOfIngredients, ArrayList<CategoryRecipe> listofCategories) {
        this.id = Optional.of(id);
        this.name = name;
        this.description = description;
        this.summary = summary;
        this.src = src;
        this.servings = servings;
        this.listOfIngredients = listOfIngredients;
        this.listofCategories = listofCategories;
    }

    /**
     * Constructor for Recipe
     * @param name
     * @param description
     * @param summary
     * @param src
     * @param servings
     * @param listOfIngredients
     * @param listofCategories
     */
    public Recipe(String name, String description, String summary, byte[] src, int servings, ArrayList<IngredientRecipe> listOfIngredients, ArrayList<CategoryRecipe> listofCategories) {
        this.name = name;
        this.description = description;
        this.summary = summary;
        this.src = src;
        this.servings = servings;
        this.listOfIngredients = listOfIngredients;
        this.listofCategories = listofCategories;
    }

    /**
     *
     * @return the id of the recipe
     */
    public Optional<Integer> getId() {
        return id;
    }

    /**
     * set the id of the recipe
     * @param id
     */
    public void setId(int id) {
        if (this.id.isEmpty())
            this.id = Optional.of(id);
    }

    /**
     *
     * @return the name of the recipe
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the recipe
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the description of the recipe
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the recipe
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return the summary of the recipe
     */
    public String getSummary() {
        return summary;
    }

    /**
     * set the summary of the recipe
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     *
     * @return the src of the picture of the recipe
     */
    public byte[] getSrc() {
        return src;
    }

    /**
     * set the src of the picture of the recipe
     * @param src
     */
    public void setSrc(byte[] src) {
        this.src = src;
    }

    /**
     *
     * @return the number of servings of the recipe
     */
    public int getServings() {
        return servings;
    }

    /**
     * set the number of servings of the recipe
     * @param servings
     */
    public void setServings(int servings) {
        this.servings = servings;
    }

    /**
     *
     * @return the list of ingredients of the recipe
     */
    public ArrayList<IngredientRecipe> getListOfIngredients() {
        return listOfIngredients;
    }

    /**
     * set the list of ingredients of the recipe
     * @param listOfIngredients
     */
    public void setListOfIngredients(ArrayList<IngredientRecipe> listOfIngredients) {
        this.listOfIngredients = listOfIngredients;
    }

    /**
     *
     * @return the list of categories of the recipe
     */
    public ArrayList<CategoryRecipe> getListofCategories() {
        return listofCategories;
    }

    /**
     * set the list of categories of the recipe
     * @param listofCategories
     */
    public void setListofCategories(ArrayList<CategoryRecipe> listofCategories) {
        this.listofCategories = listofCategories;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Recipe) {
            var other = (Recipe) obj;
            return this.hashCode() == other.hashCode();
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
