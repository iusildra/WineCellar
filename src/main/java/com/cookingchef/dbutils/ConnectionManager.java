package com.cookingchef.dbutils;

import java.sql.Connection;
import java.sql.SQLException;

import org.postgresql.ds.PGConnectionPoolDataSource;

public class ConnectionManager {
  private static volatile PGConnectionPoolDataSource dataSource;

  private ConnectionManager() {
  }

  /**
   * Open a connection pool to the database.
   * 
   * @param dbName   The name of the database you want to connect to.
   * @param user     The user name to use when connecting to the database.
   * @param password The password for the user.
   * @param port     The port number of the PostgreSQL server.
   */
  public static synchronized void openConnectionPool(String dbName, String user, String password,
      int port) {
    if (dataSource == null) {
      dataSource = new PGConnectionPoolDataSource();
      dataSource.setDatabaseName(dbName);
      dataSource.setUser(user);
      dataSource.setPassword(password);
      dataSource.setPortNumbers(new int[] { port });
    }
  }

  /**
   * If the connection pool is open, close it.
   */
  public static synchronized void closeConnectionPool() {
    // TODO: correct this shit
    dataSource = null;
  }

  /**
   * If the connection pool is not open, open it, then get a connection from the
   * pool.
   * 
   * @param dbName   The name of the database you want to connect to.
   * @param user     The user name to use when connecting to the database.
   * @param password The password for the database
   * @param port     The port number of the database.
   * @return A connection to the database.
   */
  public static synchronized Connection getConnection() throws SQLException {
    return dataSource.getPooledConnection().getConnection();
  }
}
