package com.cookingchef.dbutils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
    ConnectionManager.closeConnectionPool();
    assertThrows(SQLException.class, () -> {
      ConnectionManager.openConnectionPool("nonExisting", "postgres", "postgres", 0000);
      ConnectionManager.getConnection();
    });
    ConnectionManager.closeConnectionPool();
    ConnectionManager.openConnectionPool("postgres", "postgres", "postgres", 5432);
  }
}
