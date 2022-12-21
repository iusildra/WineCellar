package com.cookingchef.model;

public enum UserDbFields {
  ID("id"),
  NAME("name"),
  EMAIL("email"),
  PASSWORD("password"),
  PHONE("phone"),
  BIRTHDATE("birthdate"),
  QUESTION("question"),
  ANSWER("answer"),
  IS_ADMIN("is_admin");

  public final String value;

  private UserDbFields(String key) {
    this.value = key;
  }
}
