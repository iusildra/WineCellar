package com.cookingchef.model;

/**
 * The enum Category db.
 */
public enum CategoryDb {
    /**
     * Ingredient category db.
     */
    INGREDIENT("ingredient_category"),
    /**
     * Recipe category db.
     */
    RECIPE("recipe_category"),
    /**
     * Suggestion category db.
     */
    SUGGESTION("suggestion_category");

    /**
     * The Value.
     */
    public final String value;

  CategoryDb(String key) {
    this.value = key;
  }

    /**
     * Category name string.
     *
     * @return the string
     */
    public String categoryName() {
    return switch (this) {
      case INGREDIENT -> "Ingredient";
      case RECIPE -> "Recipe";
      case SUGGESTION -> "Suggestion";
    };
  }
}
