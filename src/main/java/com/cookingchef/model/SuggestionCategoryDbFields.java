package com.cookingchef.model;

public enum SuggestionCategoryDbFields {
  ID("id"),
  NAME("name");

  public final String value;

  private SuggestionCategoryDbFields(String value) {
    this.value = value;
  }
}
