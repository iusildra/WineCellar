package com.cookingchef.model;

import java.sql.SQLException;
import java.util.Optional;

import com.cookingchef.dbutils.DbEntity;
import com.cookingchef.factory.PostgresFactory;

public class Partner implements DbEntity {
  private Optional<Integer> id;
  private String name;
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
  public Optional<Integer> createInDb() throws SQLException {
    var newId = PostgresFactory.getPostgresFactory().getPartnerDAO().registerPartnerInDb(this);
    this.id = newId;
    return newId;
  }

  @Override
  public void updateInDb() throws SQLException {
    PostgresFactory.getPostgresFactory().getPartnerDAO().updatePartnerInDb(this);

  }

  @Override
  public void removeFromDb() throws SQLException {
    PostgresFactory.getPostgresFactory().getPartnerDAO().removePartnerFromDb(this);

  }

  @Override
  public int hashCode() {
    return this.id.hashCode() + this.name.hashCode() + this.description.hashCode() + this.website.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Partner) {
      var other = (Partner) obj;
      return this.id.equals(other.id)
          && this.name.equals(other.name)
          && this.description.equals(other.description)
          && this.website.equals(other.website);
    }
    return false;
  }
}
