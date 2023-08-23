package com.admarv.saas.fb.lead.constant;

import java.util.Arrays;

/**
 * 询盘状态
 * 
 * @author liuliu
 *
 */
public enum SourceEnum {

    FACEBOOK("01", "FaceBook"), 
    TIKTOK("02", "TikTok"), 
	NULL("99", "");

    private final String code;

    private final String value;

    private SourceEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static SourceEnum getByValue(final String value) {
        return Arrays.stream(values()).filter(e -> e.getValue().equals(value)).findFirst().orElse(NULL);
    }

    public static SourceEnum getByValueOrElseThrows(String value) {
        return Arrays.stream(values()).filter(e -> e.getValue().equals(value)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static SourceEnum getByCode(final String code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findFirst().orElse(NULL);
    }

    public static SourceEnum getByCodeOrElseThrows(String code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
