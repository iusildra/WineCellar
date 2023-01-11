package com.cookingchef.utils;

import java.sql.Date;

public class UserUtils {
	/**
	 * Return true if the email is valid, otherwise return false.
	 * @param email
	 * @return
	 */
	public static boolean isEmailValid(String email) {
		return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
	}

	/**
	 * Return true if the phone is valid, otherwise return false.
	 * @param phone
	 * @return
	 */
	public static boolean isPhoneValid(String phone) {
		return phone.matches("^[0-9]{10}$");
	}

	/**
	 * return true if the date is valid, otherwise return false.
	 * @param date
	 * @return
	 */
	public static boolean isDateValid(Date date) {
		return date.before(new Date(System.currentTimeMillis()));
	}
}
