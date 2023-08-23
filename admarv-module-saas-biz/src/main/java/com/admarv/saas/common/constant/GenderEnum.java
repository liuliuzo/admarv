package com.admarv.saas.common.constant;

import java.util.Arrays;
/**
 * 
 * @author liuliu
 *
 */
public enum GenderEnum {

	MALE("1", "男"), 
	FEMALE("2", "女"), 
	OTHER("3", "其他"), 
	NULL("NULL", "NULL");

	private final String code;
	private final String value;

	GenderEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	public static GenderEnum getByCode(String code) {
		return Arrays.stream(GenderEnum.values()).filter(gender -> gender.getCode().equals(code)).findFirst().orElse(NULL);
	}
}