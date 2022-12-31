package com.cookingchef.dbutils;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ConnectionManagerTests {

  public ConnectionManagerTests() {
    ConnectionManager.openConnectionPool("postgres", "postgres", "postgres", 5432);
  }

  @Test
  void validateConnection() throws SQLException {
    var connection = ConnectionManager.getConnection();
    assert connection != null;
    var statement = connection.createStatement();

    statement.execute("create table tests(id serial primary key, name varchar(100));");
    statement.execute("insert into tests(name) values ('toto'), ('titi'), ('tata');");
    statement.execute("select * from tests;");
    var resultSet = statement.getResultSet();
    assert resultSet.next();
    statement.execute("drop table tests;");
  }

  @Test
  void crashWhenWrongConnection() throws SQLException {
    ConnectionManager.setDataSource(null);
    ConnectionManager.openConnectionPool("nonExisting", "postgres", "postgres", 0000);
    assert ConnectionManager.getConnection() == null;
    ConnectionManager.setDataSource(null);
    ConnectionManager.openConnectionPool("postgres", "postgres", "postgres", 5432);
  }
}
