package com.cookingchef.user;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;

import org.junit.jupiter.api.Test;

import com.cookingchef.dao.UserDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.User;

public class DAOTests {

  private UserDAO userDAO;
  
  public DAOTests() {
    ConnectionManager.openConnectionPool("postgres", "postgres", "postgres", 5432);
    var factory = new PostgresFactory();
    this.userDAO = factory.getUserDAO();
  }

  @Test
  void createUser() throws SQLException {
    var user = new User("abc", "abc", "$2y$10$QdCYs/d73sagv5Lm13ZJ8.lRAS0lT51fS9TsRa9zO6Kw5QOEIlNe6", "abc",
        Date.from(Instant.now()), "none", "none");
    userDAO.registerUserInDb(user);
    var newUser = userDAO.getUserById(user.getId().get());

    assert newUser != null;

    userDAO.removeUserFromDb(user);
  }

  @Test
  void updateUser() throws SQLException {
    var user = new User("abc", "abc", "$2y$10$QdCYs/d73sagv5Lm13ZJ8.lRAS0lT51fS9TsRa9zO6Kw5QOEIlNe6", "abc",
        Date.from(Instant.now()), "none", "none");

    userDAO.registerUserInDb(user);

    user.setName("def");
    userDAO.updateUserInDb(user);

    var newUser = userDAO.getUserById(user.getId().get());

    assert newUser.get().equals(user);

    userDAO.removeUserFromDb(user);
  }

  @Test
  void updateUserInDbAfterMulitpleEmailUpdates() throws SQLException {
    var user = new User("abc", "abc", "$2y$10$QdCYs/d73sagv5Lm13ZJ8.lRAS0lT51fS9TsRa9zO6Kw5QOEIlNe6", "abc",
        Date.from(Instant.now()), "none", "none");

    userDAO.registerUserInDb(user);

    user.setName("def");
    user.setEmail("def");
    user.setEmail("ghi"); // This update shouldn't change the old email
    userDAO.updateUserInDb(user);

    var updatedUser = userDAO.getUserById(user.getId().get());

    assert updatedUser.get().equals(user);

    userDAO.removeUserFromDb(user);
  }
}
