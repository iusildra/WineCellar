package com.cookingchef.model;

import java.util.Optional;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Partner {
  private Optional<Integer> id = Optional.empty();
  private String name;
  private Optional<String> img = Optional.empty();
  private String description;
  private String website;

  public Partner() {
    this.name = "";
    this.description = "";
    this.website = "";
  }

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

  public IntegerProperty idProperty() {
    return new SimpleIntegerProperty(this.id.orElse(-1));
  }

  public StringProperty nameProperty() {
    return new SimpleStringProperty(this.name);
  }

  public StringProperty imgProperty() {
    return new SimpleStringProperty(this.img.orElse("No image"));
  }

  public StringProperty descriptionProperty() {
    return new SimpleStringProperty(this.description);
  }

  public StringProperty websiteProperty() {
    return new SimpleStringProperty(this.website);
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
  public Optional<String> getImg() {
    return img;
  }

  /**
   * @param img the img to set
   */
  public void setImg(String img) {
    this.img = Optional.of(img);
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

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Partner partner = (Partner) o;

    if (this.id.isPresent() && partner.id.isPresent() && !this.id.get().equals(partner.id.get()))
      return false;
    if (this.img.isPresent() && partner.img.isPresent() && !this.img.get().equals(partner.img.get()))
      return false;


    if (!name.equals(partner.name))
      return false;
    if (!description.equals(partner.description))
      return false;

    return website.equals(partner.website);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + name.hashCode();
    result = 31 * result + img.hashCode();
    result = 31 * result + description.hashCode();
    result = 31 * result + website.hashCode();
    return result;
  }
}
