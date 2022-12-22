package com.cookingchef.dao;

import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.User;
import com.cookingchef.model.UserDbFields;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;

public class PostgresUserDAO implements UserDAO {

	private static volatile PostgresUserDAO instance;

	private PostgresUserDAO() {
	}

	public static PostgresUserDAO getPostgresUserDAO() {
		if (instance == null) {
			instance = new PostgresUserDAO();
		}
		return instance;
	}

	@Override
	public User getUserByEmailPwd(String email, String password) {
		var query = "SELECT * FROM users WHERE email = ?";
		
		try {
			var conn = ConnectionManager.getConnection();
			var stmt = conn.prepareStatement(query);
			stmt.setString(1, email);

			var rs = stmt.executeQuery();

			if (rs.next()) {
				String hashed = rs.getString(UserDbFields.PASSWORD.value);
				if (BCrypt.checkpw(password, hashed)) {
					return new User(
							rs.getInt(UserDbFields.ID.value),
							rs.getString(UserDbFields.NAME.value),
							rs.getString(UserDbFields.EMAIL.value),
							rs.getString(UserDbFields.PASSWORD.value),
							rs.getString(UserDbFields.PHONE.value),
							rs.getDate(UserDbFields.BIRTHDATE.value),
							rs.getString(UserDbFields.QUESTION.value),
							rs.getString(UserDbFields.ANSWER.value),
							rs.getBoolean(UserDbFields.IS_ADMIN.value));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
