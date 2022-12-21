package com.cookingchef.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.dbutils.DbEntity;

public class User implements DbEntity {
	private final int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private final Date birthdate;
	private String question;
	private String answer;
	private Boolean isAdmin;

	public User(int id, String name, String email, String password, String phone, Date birthdate, String question,
			String answer, Boolean isAdmin) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.birthdate = birthdate;
		this.question = question;
		this.answer = answer;
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the isAdmin
	 */
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", phone='" + phone + '\'' +
				", birthdate=" + birthdate +
				", question='" + question + '\'' +
				", answer='" + answer + '\'' +
				", isAdmin=" + isAdmin +
				'}';
	}

	public String getName() {
		return name;
	}

	@Override
	public PreparedStatement create() throws SQLException {
		var query = "INSERT INTO users (name, email, phone, birthdate, question, answer, is_admin, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
		var conn = ConnectionManager.getConnection();
		var stmt = conn.prepareStatement(query);
		stmt.setString(1, this.name);
		stmt.setString(2, this.email);
		stmt.setString(3, this.phone);
		stmt.setTimestamp(4, new java.sql.Timestamp(this.birthdate.getTime()));
		stmt.setString(5, this.question);
		stmt.setString(6, this.answer);
		stmt.setBoolean(7, this.isAdmin);
		stmt.setString(8, this.password);
		return stmt;
	}

	@Override
	public PreparedStatement update() throws SQLException {
		var query = "UPDATE users SET name=?, email=?, phone=?, birthdate=?, question=?, answer=?, is_admin=?, password=? WHERE email=?";
		var conn = ConnectionManager.getConnection();
		var stmt = conn.prepareStatement(query);
		stmt.setString(1, this.name);
		stmt.setString(2, this.email);
		stmt.setString(3, this.phone);
		stmt.setTimestamp(4, new java.sql.Timestamp(this.birthdate.getTime()));
		stmt.setString(5, this.question);
		stmt.setString(6, this.answer);
		stmt.setBoolean(7, this.isAdmin);
		stmt.setString(8, this.password);
		stmt.setString(9, this.email);
		return stmt;
	}

	@Override
	public PreparedStatement delete() throws SQLException {
		var query = "DELETE FROM users WHERE email=?";
		var conn = ConnectionManager.getConnection();
		var stmt = conn.prepareStatement(query);
		stmt.setString(1, this.email);
		return stmt;
	}

	@Override
	public PreparedStatement read() {
		// TODO Auto-generated method stub
		return null;
	}
}
