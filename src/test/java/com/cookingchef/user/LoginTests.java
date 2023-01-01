package com.cookingchef.user;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;

import org.junit.jupiter.api.Test;

import com.cookingchef.dao.UserDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.facade.UserFacade;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.User;

public class LoginTests {

  private UserDAO userDAO;

  public LoginTests() {
    ConnectionManager.openConnectionPool("postgres", "postgres", "postgres", 5432);
    var factory = new PostgresFactory();
    this.userDAO = factory.getUserDAO();
  }

  @Test
  void validateLogin() throws SQLException {
    var user = new User("abc", "abc", "$2y$10$QdCYs/d73sagv5Lm13ZJ8.lRAS0lT51fS9TsRa9zO6Kw5QOEIlNe6", "abc",
        Date.from(Instant.now()), "none", "none", false);

    userDAO.registerUserInDb(user);

    UserFacade userFacade = UserFacade.getUserFacade();
    var abc = userFacade.login("abc", "abc");

    userDAO.removeUserFromDb(user);

    assert abc.get() != null;
  }
}
