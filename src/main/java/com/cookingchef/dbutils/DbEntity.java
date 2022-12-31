package com.cookingchef.dbutils;

import java.sql.SQLException;
import java.util.Optional;

public interface DbEntity {

  /**
   * Create an entity in the database and return it's new ID
   * @return the id of the newly created entity. Returns an empty option if it failed
   * @throws SQLException
   */
  public Optional<Integer> createInDb() throws SQLException;

  /**
   * Update an entity in the database
   * @throws SQLException
   */
  public void updateInDb() throws SQLException;

  /**
   * Update an entity in the database
   * @throws SQLException
   */
  public void removeFromDb() throws SQLException;
}
