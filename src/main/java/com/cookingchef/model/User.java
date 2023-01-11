package com.cookingchef.model;

import javafx.beans.property.*;

import java.util.Date;
import java.util.Optional;


public class User {
	private Optional<Integer> id = Optional.empty();
	private String name;
	private String email;
	private String password;
	private String phone;
	private final Date birthdate;
	private String question;
	private String answer;
	private Boolean isAdmin;

	/**
	 * Constructor for the User class.
	 * @param name
	 * @param email
	 * @param password
	 * @param phone
	 * @param birthdate
	 * @param question
	 * @param answer
	 */
	public User(String name, String email, String password, String phone, Date birthdate, String question,
			String answer) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.birthdate = birthdate;
		this.question = question;
		this.answer = answer;
		this.isAdmin = false;
	}

	/**
	 * Constructor for the User class.
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param phone
	 * @param birthdate
	 * @param question
	 * @param answer
	 * @param isAdmin
	 */
	public User(int id, String name, String email, String password, String phone, Date birthdate, String question,
			String answer, Boolean isAdmin) {
		this.id = Optional.of(id);
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
	 * Constructor for the User class.
	 * @param user
	 */
	public User(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.phone = user.getPhone();
		this.birthdate = user.getBirthdate();
		this.question = user.getQuestion();
		this.answer = user.getAnswer();
		this.isAdmin = user.getIsAdmin();
	}

	/**
	 * @return the id
	 */
	public Optional<Integer> getId() {
		return id;
	}

	/**
	 * Set the ID only once, if it is not already set.
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		if (this.id.isEmpty())
			this.id = Optional.of(id);
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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


	@Override
	public int hashCode() {
		return id.hashCode() + name.hashCode() + email.hashCode() + password.hashCode() + phone.hashCode() + question.hashCode() + answer.hashCode() + isAdmin.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			var user = (User) obj;
			return this.hashCode() == user.hashCode();

		}
		return false;
	}

	public IntegerProperty idProperty() {
		return new SimpleIntegerProperty(id.orElse(-1));
	}

	public StringProperty nameProperty() {
		return new SimpleStringProperty(name);
	}

	public StringProperty emailProperty() {
		return new SimpleStringProperty(email);
	}


	public StringProperty phoneProperty() {
		return new SimpleStringProperty(phone);
	}

	public StringProperty birthdateProperty() {
		return new SimpleStringProperty(birthdate.toString());
	}

	public BooleanProperty isAdminProperty() {
		return new SimpleBooleanProperty(isAdmin);
	}

	/**
	 * Set if the user is an admin or not.
	 * @param isAdmin
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
