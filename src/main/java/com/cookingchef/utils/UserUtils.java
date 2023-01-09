package com.cookingchef.utils;

import java.sql.Date;

public class UserUtils {
	public static boolean isEmailValid(String email) {
		return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
	}

	public static boolean isPhoneValid(String phone) {
		return phone.matches("^[0-9]{10}$");
	}

	public static boolean isDateValid(Date date) {
		return date.before(new Date(System.currentTimeMillis()));
	}
}
