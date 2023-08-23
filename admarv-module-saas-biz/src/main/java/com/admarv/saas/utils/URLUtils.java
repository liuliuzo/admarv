package com.admarv.saas.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author liuliu
 *
 */
public class URLUtils {
	public static Map<String, String> parseQueryParameters(String query) {
		Map<String, String> queryParams = new HashMap<>();
		if (query != null) {
			String[] pairs = query.split("&");
			for (String pair : pairs) {
				String[] keyValue = pair.split("=");
				if (keyValue.length == 2) {
					String key = keyValue[0];
					String value = keyValue[1];
					queryParams.put(key, value);
				}
			}
		}
		return queryParams;
	}
}
