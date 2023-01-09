package com.cookingchef.model;

public enum SuggestionDbFields {
  ID("id"),
  TITLE("title"),
  DESCRIPTION("description"),
  CATEGORY("category"),
  CATEGORY_LABEL("name"),
  AUTHOR("user_id");

  public final String value;

  private SuggestionDbFields(String value) {
    this.value = value;
  }
}
