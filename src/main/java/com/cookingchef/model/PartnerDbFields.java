package com.cookingchef.model;

public enum PartnerDbFields {
  ID("id"),
  NAME("name"),
  DESCRIPTION("description"),
  WEBSITE("website");
  
  public final String value;

  private PartnerDbFields(String key) {
    this.value = key;
  }
}
