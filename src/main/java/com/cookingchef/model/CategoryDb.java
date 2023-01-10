package com.cookingchef.model;

public enum CategoryDb {
  INGREDIENT("ingredient_category"),
  RECIPE("recipe_category"),
  SUGGESTION("suggestion_category");

  public final String value;

  CategoryDb(String key) {
    this.value = key;
  }

  public String categoryName() {
    return switch (this) {
      case INGREDIENT -> "Ingredient";
      case RECIPE -> "Recipe";
      case SUGGESTION -> "Suggestion";
    };
  }
}
