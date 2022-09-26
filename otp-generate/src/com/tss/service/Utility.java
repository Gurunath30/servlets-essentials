package com.tss.service;

import java.util.regex.Pattern;

public class Utility{
	/**
	 * This method checks whether the String has valid mobile number or not.
	 * 
	 * @param String
	 * @return boolean
	 */
	public static boolean phoneValidation(String phone) {
		Pattern pattern = Pattern.compile("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{5,14}$");
		return phone == null ? false : pattern.matcher(phone).matches();
	}
}