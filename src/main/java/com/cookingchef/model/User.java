package com.cookingchef.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import com.cookingchef.dbutils.DbEntity;
import com.cookingchef.factory.PostgresFactory;

public class User implements DbEntity {
	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private final Date birthdate;
	private String question;
	private String answer;
	private Boolean isAdmin;

	public User(String name, String email, String password, String phone, Date birthdate, String question,
			String answer, Boolean isAdmin) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.birthdate = birthdate;
		this.question = question;
		this.answer = answer;
		this.isAdmin = isAdmin;
	}

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
	public Optional<Integer> createInDb() throws SQLException {
		var newId = PostgresFactory.getPostgresFactory().getUserDAO().registerUserInDb(this);
		if (newId.isPresent())
			this.id = newId.get();
		return newId;
	}

	@Override
	public void updateInDb() throws SQLException {
		PostgresFactory.getPostgresFactory().getUserDAO().updateUserInDb(this);
	}

	@Override
	public void removeFromDb() throws SQLException {
		PostgresFactory.getPostgresFactory().getUserDAO().removeUserFromDb(this);
	}
}
