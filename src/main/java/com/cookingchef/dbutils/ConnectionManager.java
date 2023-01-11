package com.cookingchef.dbutils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

import org.postgresql.ds.PGConnectionPoolDataSource;

public class ConnectionManager {
  private static AtomicReference<PGConnectionPoolDataSource> dataSource = new AtomicReference<>();

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
  public static void openConnectionPool(String dbName, String user, String password,
      int port) {
    var ds = new PGConnectionPoolDataSource();

    ds.setDatabaseName(dbName);
    ds.setUser(user);
    ds.setPassword(password);
    ds.setPortNumbers(new int[] { port });

    dataSource.compareAndSet(null, ds);
  }

  /**
   * If the connection pool is open, close it.
   */
  public static void setDataSource(PGConnectionPoolDataSource source) {
    dataSource.set(source);
  }

  /**
   * If the connection pool is not open, open it, then get a connection from the pool.
   * @return
   */
  public static Connection getConnection() {
    try {
      return dataSource.get().getPooledConnection().getConnection();
    } catch (SQLException e) {
      return null;
    }
  }
}
