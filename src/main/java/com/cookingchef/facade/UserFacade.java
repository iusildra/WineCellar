package com.cookingchef.facade;

import com.cookingchef.dao.UserDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.User;

import java.sql.SQLException;
import java.util.List;
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

	public User update(User user) throws SQLException {
		return this.userDAO.updateUserInDb(user);
	}

	public List<User> getUsers() throws SQLException {
		return this.userDAO.getUsersFromDb();
	}

	public void deleteUser(User user) {
		try {
			this.userDAO.removeUserFromDb(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User user) {
		try {
			this.userDAO.updateUserInDb(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Optional<User> getUser(Optional<Integer> userId) {
		try {
			return this.userDAO.getUserById(userId.get());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public void updateUser(Integer id, String name, String email, String phone, boolean isAdmin) {
		try {
			var user = this.userDAO.getUserById(id).get();
			user.setName(name);
			user.setEmail(email);
			user.setPhone(phone);
			user.setAdmin(isAdmin);
			this.userDAO.updateUserInDb(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
