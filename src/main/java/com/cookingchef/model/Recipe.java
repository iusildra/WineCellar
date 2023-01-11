package com.cookingchef.model;

import java.util.ArrayList;
import java.util.List;
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

    public Recipe(String name, String description, String summary, byte[] src, int servings, ArrayList<IngredientRecipe> listOfIngredients, ArrayList<CategoryRecipe> listofCategories) {
        this.name = name;
        this.description = description;
        this.summary = summary;
        this.src = src;
        this.servings = servings;
        this.listOfIngredients = listOfIngredients;
        this.listofCategories = listofCategories;
    }

    public Optional<Integer> getId() {
        return id;
    }

    public void setId(int id) {
        if (this.id.isEmpty())
            this.id = Optional.of(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public byte[] getSrc() {
        return src;
    }

    public void setSrc(byte[] src) {
        this.src = src;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public ArrayList<IngredientRecipe> getListOfIngredients() {
        return listOfIngredients;
    }

    public void setListOfIngredients(ArrayList<IngredientRecipe> listOfIngredients) {
        this.listOfIngredients = listOfIngredients;
    }

    public ArrayList<CategoryRecipe> getListofCategories() {
        return listofCategories;
    }

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
