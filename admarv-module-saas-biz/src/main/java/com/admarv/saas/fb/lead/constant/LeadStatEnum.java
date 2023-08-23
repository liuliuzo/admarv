package com.admarv.saas.fb.lead.constant;

import java.util.Arrays;

/**
 * 询盘状态
 * 
 * @author liuliu
 *
 */
public enum LeadStatEnum {

    UNREAD("01", "未读"), 
    READ("02", "已读");

    private final String code;

    private final String value;

    private LeadStatEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static LeadStatEnum getByValue(final String value) {
        return Arrays.stream(values()).filter(e -> e.getValue().equals(value)).findFirst().orElse(null);
    }

    public static LeadStatEnum getByValueOrElseThrows(String value) {
        return Arrays.stream(values()).filter(e -> e.getValue().equals(value)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static LeadStatEnum getByCode(final String code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findFirst().orElse(null);
    }

    public static LeadStatEnum getByCodeOrElseThrows(String code) {
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
