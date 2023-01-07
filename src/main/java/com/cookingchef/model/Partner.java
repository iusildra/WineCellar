package com.cookingchef.model;

import java.util.Optional;

public class Partner {
  private Optional<Integer> id = Optional.empty();
  private String name;
  private String img = "";
  private String description;
  private String website;

  /**
   * @param name
   * @param description
   * @param website
   */
  public Partner(String name, String description, String website) {
    this.name = name;
    this.description = description;
    this.website = website;
  }

  /**
   * @param id
   * @param name
   * @param description
   * @param website
   */
  public Partner(int id, String name, String description, String website) {
    this.id = Optional.of(id);
    this.name = name;
    this.description = description;
    this.website = website;
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
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the img
   */
  public String getImg() {
    return img;
  }

  /**
   * @param img the img to set
   */
  public void setImg(String img) {
    this.img = img;
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
   * @return the website
   */
  public String getWebsite() {
    return website;
  }

  /**
   * @param website the website to set
   */
  public void setWebsite(String website) {
    this.website = website;
  }

  @Override
  public int hashCode() {
    return this.id.hashCode() + this.name.hashCode() + this.description.hashCode() + this.website.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Partner) {
      var other = (Partner) obj;
      return this.hashCode() == other.hashCode();
    }
    return false;
  }
}
