package com.cookingchef.model;

import java.util.Date;

public class User {
	private String name;
	private String email;
	private String password;
	private String phone;
	private Date birthdate;
	private String question;
	private String answer;
	private Boolean isAdmin;

	public User(String name, String email, String password, String phone, Date birthdate, String question, String answer, Boolean isAdmin) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.birthdate = birthdate;
		this.question = question;
		this.answer = answer;
		this.isAdmin = isAdmin;
	}

}
