package com.cookingchef.dbutils;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ConnectionManagerTests {

  @Test
  void validateConnection() throws SQLException {
    var connection = ConnectionManager.getConnection("postgres", "postgres", "postgres", 5432);
    assert connection.isPresent();
    var conn = connection.get();
    var statement = conn.createStatement();

    statement.execute("create table tests(id serial primary key, name varchar(100));");
    statement.execute("insert into tests(name) values ('toto'), ('titi'), ('tata');");
    statement.execute("select * from tests;");
    var resultSet = statement.getResultSet();
    assert resultSet.next() == true;
    statement.execute("drop table tests;");
    ConnectionManager.closeConnectionPool();
  }

  @Test
  void crashWhenWrongConnection() throws SQLException {
    var connection = ConnectionManager.getConnection("nonExisting", "postgres", "postgres", 0000);
    assert connection.isEmpty();
    ConnectionManager.closeConnectionPool();
  }
}
