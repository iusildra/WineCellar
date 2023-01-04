package com.cookingchef.model;

public enum SuggestionDbFields {
  ID("id"),
  TITLE("title"),
  DESCRIPTION("description"),
  CATEGORY_ID("category"),
  AUTHOR_ID("user_id");

  public final String value;

  private SuggestionDbFields(String value) {
    this.value = value;
  }
}
