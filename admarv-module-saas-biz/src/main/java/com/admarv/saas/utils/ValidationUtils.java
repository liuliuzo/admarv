package com.admarv.saas.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author liuliu
 *
 */
public class ValidationUtils {
	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
	private static final String WHATSAPP_REGEX = "^[0-9]{9,15}$";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
	private static final Pattern WHATSAPP_PATTERN = Pattern.compile(WHATSAPP_REGEX);

	public static boolean isValidEmail(String email) {
		if (email == null) {
			return false;
		}
		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}

	public static boolean isValidWhatsappNumber(String whatsappNumber) {
		if (whatsappNumber == null) {
			return false;
		}
		whatsappNumber = whatsappNumber.replace("+", "");
		Matcher matcher = WHATSAPP_PATTERN.matcher(whatsappNumber);
		return matcher.matches();
	}
}
