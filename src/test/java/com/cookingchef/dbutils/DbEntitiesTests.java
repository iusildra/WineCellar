package com.cookingchef.dbutils;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;

import org.junit.jupiter.api.Test;

import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.User;

public class DbEntitiesTests {

  public DbEntitiesTests() {
    ConnectionManager.openConnectionPool("postgres", "postgres", "postgres", 5432);
  }

  @Test
  void createUser() throws SQLException {
    var user = new User("abc", "abc", "$2y$10$QdCYs/d73sagv5Lm13ZJ8.lRAS0lT51fS9TsRa9zO6Kw5QOEIlNe6", "abc",
        Date.from(Instant.now()), "none", "none", false);
    var userId = user.createInDb().get();
    var newUser = PostgresFactory.getPostgresFactory().getUserDAO().getUserById(userId);

    assert newUser != null;

    user.removeFromDb();
  }

  @Test
  void updateUser() throws SQLException {
    var user = new User("abc", "abc", "$2y$10$QdCYs/d73sagv5Lm13ZJ8.lRAS0lT51fS9TsRa9zO6Kw5QOEIlNe6", "abc",
        Date.from(Instant.now()), "none", "none", false);

    var newId = user.createInDb();

    user.setName("def");
    user.updateInDb();

    var newUser = PostgresFactory.getPostgresFactory().getUserDAO().getUserById(newId.get());

    assert newUser.isPresent();
    assert newUser.get().getEmail().equals("abc");

    user.removeFromDb();
  }

  @Test
  void updateUserInDbAfterMulitpleEmailUpdates() throws SQLException {
    var user = new User("abc", "abc", "$2y$10$QdCYs/d73sagv5Lm13ZJ8.lRAS0lT51fS9TsRa9zO6Kw5QOEIlNe6", "abc",
        Date.from(Instant.now()), "none", "none", false);

    var newId = user.createInDb();

    user.setName("def");
    user.setEmail("def");
    user.setEmail("ghi"); // This update shouldn't change the old email
    user.updateInDb();

    var updatedUser = PostgresFactory.getPostgresFactory().getUserDAO().getUserById(newId.get());

    assert updatedUser.isPresent();
    assert updatedUser.get().getEmail().equals("ghi");

    user.removeFromDb();
  }
}
