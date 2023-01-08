package com.cookingchef.facade;

import com.cookingchef.dao.UserDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.User;

import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class UserFacade {
	private static AtomicReference<UserFacade> instance = new AtomicReference<>();
	private UserDAO userDAO;

	private UserFacade() {
		var factory = new PostgresFactory();
		this.userDAO = factory.getUserDAO();
	}

	public static UserFacade getUserFacade() {
		instance.compareAndSet(null, new UserFacade());
		return instance.get();
	}

	public Optional<User> login(String email,String password) throws SQLException {
		return this.userDAO.getUserByEmailPwd(email,password);
	}

	public Optional<User> register(User user) throws SQLException {
		if(this.userDAO.getUserByEmailPwd(user.getEmail(), user.getPassword()).isEmpty()) {
			return this.userDAO.registerUserInDb(user);
		}else{
			return Optional.empty();
		}
	}
}
