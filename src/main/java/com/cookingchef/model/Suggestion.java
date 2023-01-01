package com.cookingchef.model;

import java.util.Optional;

public class Suggestion {
  private Optional<Integer> id = Optional.empty();
  private String title;
  private String description;
  private int categoryId;
  private int authorId;

  /**
   * @param title
   * @param description
   * @param categoryId
   * @param authorId
   */
  public Suggestion(String title, String description, int categoryId, int authorId) {
    this.title = title;
    this.description = description;
    this.categoryId = categoryId;
    this.authorId = authorId;
  }

  /**
   * @param id
   * @param title
   * @param description
   * @param categoryId
   * @param authorId
   */
  public Suggestion(Optional<Integer> id, String title, String description, int categoryId, int authorId) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.categoryId = categoryId;
    this.authorId = authorId;
  }

  /**
   * @return the id
   */
  public Optional<Integer> getId() {
    return id;
  }

  /**
   * Set the ID only once, if it is not already set.
   * 
   * @param id The id of the object.
   */
  public void setId(int id) {
    if (this.id.isEmpty())
      this.id = Optional.of(id);
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the categoryId
   */
  public int getCategoryId() {
    return categoryId;
  }

  /**
   * @param categoryId the categoryId to set
   */
  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  /**
   * @return the authorId
   */
  public int getAuthorId() {
    return authorId;
  }

  /**
   * @param authorId the authorId to set
   */
  public void setAuthorId(int authorId) {
    this.authorId = authorId;
  }

  @Override
  public int hashCode() {
    return this.id.hashCode() + this.title.hashCode() + this.description.hashCode() + this.categoryId + this.authorId;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Suggestion) {
      Suggestion other = (Suggestion) obj;
      return this.hashCode() == other.hashCode();
    }
    return false;
  }

}
