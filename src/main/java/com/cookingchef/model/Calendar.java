package com.cookingchef.model;

import java.sql.Date;

public class Calendar {
    private int userId;
    private int recipeId;
    private int mealCategoryId;
    private Date date;

    /**
     * Constructor for a new Calendar.
     *
     * @param userId The Id of the user.
     * @param recipeId The Id of the recipe.
     * @param mealCategoryId The Id of the meal category.
     * @param date The date of the meal.
     */
    public Calendar(int userId, int recipeId, int mealCategoryId, Date date) {
        this.userId = userId;
        this.recipeId = recipeId;
        this.mealCategoryId = mealCategoryId;
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getMealCategoryId() {
        return mealCategoryId;
    }

    public void setMealCategoryId(int mealCategoryId) {
        this.mealCategoryId = mealCategoryId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return this.date.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Calendar) {
            var other = (Calendar) obj;
            return this.hashCode() == other.hashCode();
        }
        return false;
    }
}
