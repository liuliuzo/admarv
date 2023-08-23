package com.admarv.saas.fb.lead.constant;

import java.util.Arrays;

/**
 * 广告账户状态
 *
 */
public enum AccountStatusEnumCN {

    ACTIVE("1", "账户正常运行，可以发布广告"),
    DISABLED("2", "账户已被禁用，无法发布广告。可能是由于违反了 Facebook 广告政策或服务条款"),
    UNSETTLED("3", "账户存在未解决的问题，导致广告活动受到限制"),
    PENDING_RISK_REVIEW("7", "账户正在等待风险审核，该审核可能是由于账户活动异常或存在安全风险"),
    PENDING_SETTLEMENT("8", "账户正在等待结算审核，可能是因为需要验证付款信息或结算方式"),
    IN_GRACE_PERIOD("9", "账户处于宽限期，即在禁用前的一段时间内允许用户纠正问题"),
    PENDING_CLOSURE("100", "账户正在等待关闭，可能是用户提交了关闭请求"),
    CLOSED("101", "账户已关闭，不能再使用"),
    ANY_ACTIVE("201", "任何激活状态"),
    ANY_CLOSED("202", "任何关闭状态");

    private final String code;

    private final String value;

    private AccountStatusEnumCN(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static AccountStatusEnumCN getByValue(final String value) {
        return Arrays.stream(values()).filter(e -> e.getValue().equals(value)).findFirst().orElse(ACTIVE);
    }

    public static AccountStatusEnumCN getByValueOrElseThrows(String value) {
        return Arrays.stream(values()).filter(e -> e.getValue().equals(value)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static AccountStatusEnumCN getByCode(final String code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findFirst().orElse(ACTIVE);
    }

    public static AccountStatusEnumCN getByCodeOrElseThrows(String code) {
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
