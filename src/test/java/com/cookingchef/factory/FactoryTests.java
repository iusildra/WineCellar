package com.cookingchef.factory;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class FactoryTests {

  @Test
  void validatePostgresFactory() throws SQLException {
    PostgresFactory factory = PostgresFactory.getPostgresFactory();
    var connection = factory.getPostgresFactory("postgres", "postgres", "postgres", 5432);
    assert connection.isPresent();
    assert connection.get().isValid(2);
  }
  
}
