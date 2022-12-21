package com.cookingchef.dao;

import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;
import java.util.Optional;



public class PostgresUserDAO extends UserDAO {

	private static volatile PostgresUserDAO instance;

	private PostgresUserDAO() {}
	public static PostgresUserDAO getPostgresUserDAO() {
		if (instance == null) {
			instance = new PostgresUserDAO();
		}
		return instance;
	}

	@Override
	public User getUserByEmailPwd(String email,String password) throws SQLException {
		Optional<Connection> conn = ConnectionManager.getConnection("postgres", "postgres", "postgres", 5432);

		String sql = "SELECT * FROM users WHERE email = ?";
		PreparedStatement stmt = conn.get().prepareStatement(sql);
		stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			String hashed = rs.getString("password");
			if (BCrypt.checkpw(password, hashed)) {
				return new User(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("phone"),
						rs.getDate("birthdate"),
						rs.getString("question"),
						rs.getString("answer"),
						rs.getBoolean("isAdmin"));
			}
		}
		return null;
	}
}
