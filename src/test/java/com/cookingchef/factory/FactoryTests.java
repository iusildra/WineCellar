package com.cookingchef.factory;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class FactoryTests {

  @Test
  void validatePostgresFactory() throws SQLException {
    var connection = AbstractFactory.getPostgresFactory("postgres", "postgres", "postgres", 5432);
    assert connection.isPresent();
    assert connection.get().isValid(2);
  }
  
}
