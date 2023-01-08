package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.UserDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.User;
import com.cookingchef.model.UserDbFields;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class PostgresUserDAO implements UserDAO {

	private static AtomicReference<PostgresUserDAO> instance = new AtomicReference<>();

	private PostgresUserDAO() {
	}

	public static PostgresUserDAO getPostgresUserDAO() {
		instance.compareAndSet(null, new PostgresUserDAO());
		return instance.get();
	}

	@Override
	public Optional<User> getUserById(int id) throws SQLException {
		var query = "SELECT * FROM users WHERE id = ?";
		var conn = ConnectionManager.getConnection();

		try (var stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, id);

			var rs = stmt.executeQuery();

			if (rs.next()) {
				return Optional.of(new User(
						rs.getInt(UserDbFields.ID.value),
						rs.getString(UserDbFields.NAME.value),
						rs.getString(UserDbFields.EMAIL.value),
						rs.getString(UserDbFields.PASSWORD.value),
						rs.getString(UserDbFields.PHONE.value),
						rs.getDate(UserDbFields.BIRTHDATE.value),
						rs.getString(UserDbFields.QUESTION.value),
						rs.getString(UserDbFields.ANSWER.value),
						rs.getBoolean(UserDbFields.IS_ADMIN.value)));
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<User> getUserByEmailPwd(String email, String password) throws SQLException {
		var query = "SELECT * FROM users WHERE email = ?";
		var conn = ConnectionManager.getConnection();

		try (var stmt = conn.prepareStatement(query)) {
			stmt.setString(1, email);

			var rs = stmt.executeQuery();

			if (rs.next()) {
				String hashed = rs.getString(UserDbFields.PASSWORD.value);
				if (BCrypt.checkpw(password, hashed)) {
					return Optional.of(new User(
							rs.getInt(UserDbFields.ID.value),
							rs.getString(UserDbFields.NAME.value),
							rs.getString(UserDbFields.EMAIL.value),
							rs.getString(UserDbFields.PASSWORD.value),
							rs.getString(UserDbFields.PHONE.value),
							rs.getDate(UserDbFields.BIRTHDATE.value),
							rs.getString(UserDbFields.QUESTION.value),
							rs.getString(UserDbFields.ANSWER.value),
							rs.getBoolean(UserDbFields.IS_ADMIN.value)));
				}
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<User> registerUserInDb(User user) throws SQLException {
		var query = "INSERT INTO users (name, email, phone, birthdate, question, answer, is_admin, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
		var conn = ConnectionManager.getConnection();

		try (var stmt = conn.prepareStatement(query)) {
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPhone());
			stmt.setTimestamp(4, new Timestamp(user.getBirthdate().getTime()));
			stmt.setString(5, user.getQuestion());
			stmt.setString(6, user.getAnswer());
			stmt.setBoolean(7, user.getIsAdmin());
			stmt.setString(8, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

			var rs = stmt.executeQuery();
			rs.next();
			var newId = rs.getInt(UserDbFields.ID.value);
			user.setId(newId);
			return Optional.of(user);
		}
	}

	@Override
	public void updateUserInDb(User user) throws SQLException {
		var query = "UPDATE users SET name=?, email=?, phone=?, birthdate=?, question=?, answer=?, is_admin=?, password=? WHERE id=?";
		var conn = ConnectionManager.getConnection();

		try (var stmt = conn.prepareStatement(query)) {
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPhone());
			stmt.setTimestamp(4, new Timestamp(user.getBirthdate().getTime()));
			stmt.setString(5, user.getQuestion());
			stmt.setString(6, user.getAnswer());
			stmt.setBoolean(7, user.getIsAdmin());
			stmt.setString(8, user.getPassword());
			stmt.setInt(9, user.getId().get());

			stmt.executeUpdate();
		}
	}

	@Override
	public void removeUserFromDb(User user) throws SQLException {
		var query = "DELETE FROM users WHERE id=?";
		var conn = ConnectionManager.getConnection();

		try (var stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, user.getId().get());

			stmt.executeUpdate();
		}
	}

	@Override
	public List<User> getUsersFromDb() throws SQLException {
		var query = "SELECT * FROM users";
		var list = new ArrayList<User>();
		var conn = ConnectionManager.getConnection();

		try (var stmt = conn.prepareStatement(query)) {
			var rs = stmt.executeQuery();
			while (rs.next()) {
				var user = new User(
						rs.getInt(UserDbFields.ID.value),
						rs.getString(UserDbFields.NAME.value),
						rs.getString(UserDbFields.EMAIL.value),
						rs.getString(UserDbFields.PASSWORD.value),
						rs.getString(UserDbFields.PHONE.value),
						rs.getDate(UserDbFields.BIRTHDATE.value),
						rs.getString(UserDbFields.QUESTION.value),
						rs.getString(UserDbFields.ANSWER.value),
						rs.getBoolean(UserDbFields.IS_ADMIN.value));
				list.add(user);
			}
		}

		return list;
	}
}
