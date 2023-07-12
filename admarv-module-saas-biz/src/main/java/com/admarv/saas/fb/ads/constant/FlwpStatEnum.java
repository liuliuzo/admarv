package com.admarv.saas.fb.ads.constant;

import java.util.Arrays;

/**
 * 询盘跟进状态
 * 
 * @author liuliu
 *
 */
public enum FlwpStatEnum {

    TO_BE_FOLLOWED_UP("01", "待跟进"),
    QUOTED_THE_PRICE("02", "已报价"),
    HAVE_COMMUNICATED("03", "已沟通"),
    DONE_DEAL("04", "已成交");

    private final String code;

    private final String value;

    private FlwpStatEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static FlwpStatEnum getByValue(final String value) {
        return Arrays.stream(values()).filter(e -> e.getValue().equals(value)).findFirst().orElse(TO_BE_FOLLOWED_UP);
    }

    public static FlwpStatEnum getByValueOrElseThrows(String value) {
        return Arrays.stream(values()).filter(e -> e.getValue().equals(value)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static FlwpStatEnum getByCode(final String code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findFirst().orElse(TO_BE_FOLLOWED_UP);
    }

    public static FlwpStatEnum getByCodeOrElseThrows(String code) {
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
