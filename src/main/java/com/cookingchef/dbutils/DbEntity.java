package com.cookingchef.dbutils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DbEntity {
  public PreparedStatement create() throws SQLException;

  public PreparedStatement update() throws SQLException;

  public PreparedStatement delete() throws SQLException;

  public PreparedStatement read() throws SQLException;
}
