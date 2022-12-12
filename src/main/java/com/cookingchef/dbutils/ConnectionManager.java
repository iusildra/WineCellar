package com.cookingchef.dbutils;

import java.util.Optional;

import javax.sql.PooledConnection;

import org.postgresql.ds.PGConnectionPoolDataSource;

public class ConnectionManager {
  private static volatile PGConnectionPoolDataSource dataSource;

  private ConnectionManager() {
  }

  public static synchronized Optional<PooledConnection> openConnectionPool(String dbName, String user, String password,
      int port) {
    dataSource = new PGConnectionPoolDataSource();
    dataSource.setDatabaseName(dbName);
    dataSource.setUser(user);
    dataSource.setPassword(password);
    dataSource.setPortNumbers(new int[] { port });

    return getConnection();
  }

  // TODO: awful, but works for now
  public static synchronized void closeConnectionPool() {
    dataSource = null;
  }

  public static synchronized Optional<PooledConnection> getConnection() {
    try {
      return Optional.of(dataSource.getPooledConnection());
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
