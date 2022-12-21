package com.cookingchef.dbutils;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;

import org.junit.jupiter.api.Test;

import com.cookingchef.facade.UserFacade;
import com.cookingchef.model.User;

public class LoginTests {

  public LoginTests() {
    ConnectionManager.openConnectionPool("postgres", "postgres", "postgres", 5432);
  }

  @Test
  void validateLogin() throws SQLException {
    var user = new User(-1, "abc", "abc", "$2y$10$QdCYs/d73sagv5Lm13ZJ8.lRAS0lT51fS9TsRa9zO6Kw5QOEIlNe6", "abc",
        Date.from(Instant.now()), "none", "none", false);
    var stmt = user.create();
    stmt.executeQuery();

    UserFacade userFacade = UserFacade.getUserFacade();
    var abc = userFacade.login("abc", "abc");

    stmt = user.delete();
    stmt.executeUpdate();

    assert abc != null;
  }
}
