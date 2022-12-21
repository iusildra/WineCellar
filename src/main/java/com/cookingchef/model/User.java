package com.cookingchef.model;

import java.util.Date;

public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private Date birthdate;
	private String question;
	private String answer;
	private Boolean isAdmin;

	public User(int id,String name, String email, String password, String phone, Date birthdate, String question, String answer, Boolean isAdmin) {
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
}
