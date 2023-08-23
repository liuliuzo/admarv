package com.admarv.saas.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author liuliu
 *
 */
public class CheckURLInTextUtils {
	public static boolean containsURL(String text) {
		String urlPattern = "http[s]?://(?:[a-zA-Z]|[0-9]|[$-_@.&+]|[!*\\\\(\\\\),]|(?:%[0-9a-fA-F][0-9a-fA-F]))+";
		Pattern pattern = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		return matcher.find();
	}
}
