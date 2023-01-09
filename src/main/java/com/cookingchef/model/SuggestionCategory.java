package com.cookingchef.model;

import java.util.Optional;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SuggestionCategory {
  private Optional<Integer> id = Optional.empty();
  private String name;

  public SuggestionCategory(String name) {
    this.name = name;
  }

  public SuggestionCategory(Optional<Integer> id, String name) {
    this.id = id;
    this.name = name;
  }

  public IntegerProperty idProperty() {
    return new SimpleIntegerProperty(this.id.orElse(-1));
  }

  public StringProperty nameProperty() {
    return new SimpleStringProperty(this.name);
  }

  public Optional<Integer> getId() {
    return id;
  }

  public void setId(Optional<Integer> id) {
    if (this.id.isEmpty())
      this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id.isEmpty()) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;

    SuggestionCategory other = (SuggestionCategory) obj;

    if (this.id.isPresent() && other.id.isPresent() && this.id.get() != other.id.get())
      return false;

    return this.name.equals(other.name);
  }

  @Override
  public String toString() {
    return name;
  }

  
}
