package com.admarv.saas.fb.lead.constant;

import java.util.Arrays;

/**
 * 广告账户状态
 *
 */
public enum AccountStatusEnum {

    ACTIVE("1", "ACTIVE"), 
    DISABLED("2", "DISABLED"), 
    UNSETTLED("3", "UNSETTLED"),
    PENDING_RISK_REVIEW("7", "PENDING_RISK_REVIEW"), 
    PENDING_SETTLEMENT("8", "PENDING_SETTLEMENT"),
    IN_GRACE_PERIOD("9", "IN_GRACE_PERIOD"),
    PENDING_CLOSURE("100", "PENDING_CLOSURE"), 
    CLOSED("101", "CLOSED"),
    ANY_ACTIVE("201", "ANY_ACTIVE"),
    ANY_CLOSED("201", "ANY_CLOSED"),
    ;

    private final String code;

    private final String value;

    private AccountStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static AccountStatusEnum getByValue(final String value) {
        return Arrays.stream(values()).filter(e -> e.getValue().equals(value)).findFirst().orElse(ACTIVE);
    }

    public static AccountStatusEnum getByValueOrElseThrows(String value) {
        return Arrays.stream(values()).filter(e -> e.getValue().equals(value)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static AccountStatusEnum getByCode(final String code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findFirst().orElse(ACTIVE);
    }

    public static AccountStatusEnum getByCodeOrElseThrows(String code) {
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
