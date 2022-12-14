package com.cookingchef.factory;

import java.sql.Connection;
import java.util.Optional;

import com.cookingchef.dao.UserDAO;
import com.cookingchef.dbutils.ConnectionManager;

public interface AbstractFactory {
  public static UserDAO getUserDAO() {
    return null;
  }

  /**
   * If the connection is successful, return the connection, otherwise return an
   * empty Optional.
   * 
   * @param dbName   The name of the database you want to connect to.
   * @param user     The user name to connect to the database.
   * @param password The password for the database
   * @param port     The port number of the database.
   * @return An Optional object.
   */
  public static Optional<Connection> getPostgresFactory(String dbName, String user, String password,
      int port) {
    return ConnectionManager.getConnection(dbName, user, password, port);
  }
}