package com.admarv.saas.utils;

/**
 * 
 * @author liuliu
 *
 */
public class IntegerUtils {

	public static int parseInt(String value, int defaultValue) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public static int parseInt(String value) throws NumberFormatException {
		return Integer.parseInt(value);
	}

	public static String toString(int value) {
		return Integer.toString(value);
	}

	public static String toHexString(int value) {
		return Integer.toHexString(value);
	}

	public static String toBinaryString(int value) {
		return Integer.toBinaryString(value);
	}
}
