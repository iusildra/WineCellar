package com.cookingchef.dbutils;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;

import org.junit.jupiter.api.Test;

import com.cookingchef.model.User;

public class DbEntitiesTests {

  public DbEntitiesTests() {
    ConnectionManager.openConnectionPool("postgres", "postgres", "postgres", 5432);
  }

  @Test
  void createUserTest() throws SQLException {
    var user = new User(-1, "abc", "abc", "$2y$10$QdCYs/d73sagv5Lm13ZJ8.lRAS0lT51fS9TsRa9zO6Kw5QOEIlNe6", "abc",
        Date.from(Instant.now()), "none", "none", false);
    var stmt = user.create();
    stmt.executeQuery();

    assert stmt.getResultSet().next();

    user.delete().executeUpdate();
  }

  @Test
  void updateUserTest() throws SQLException {
    var user = new User(-1, "abc", "abc", "$2y$10$QdCYs/d73sagv5Lm13ZJ8.lRAS0lT51fS9TsRa9zO6Kw5QOEIlNe6", "abc",
        Date.from(Instant.now()), "none", "none", false);
    user.create().executeQuery();

    user.setName("def");
    user.update().executeUpdate();

    var query = "SELECT * FROM users WHERE name = 'def'";
    var rs = ConnectionManager.getConnection().prepareStatement(query).executeQuery();
    rs.next();

    assert rs.getString(2).equals("def");

    user.delete().executeUpdate();
  }
}
