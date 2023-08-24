package com.admarv.saas.mail.constant;

import java.util.Arrays;

/**
 * 
 * @author liuliu
 *
 */
public enum SMTPEnum {

	SMTP163_SEND("163", "smtp.163.com", 25, 465, new String[] {"163.com"}),
    GMAIL_SEND("Gmail", "smtp.gmail.com", 25, 587, new String[]{"gmail.com"}),
	NETEASE_SEND("NetEase", "smtphz.qiye.163.com", 25, 465, new String[] {"admarv.com"}),
	HOTMAIL_SEND("Hotmail", "smtp.live.com", 25, 465, new String[] {"hotmail.com"}),
	PUTLOOK_SEND("Outlook", "smtp.live.com", 25, 465, new String[] {"outlook.com"}),
    QQ_SEND("Tencent QQ", "smtp.qq.com", 587, 465, new String[]{"qq.com"}),
    ALIYUN_SEND("Alibaba", "smtp.mxhichina.com", 465, 465, new String[]{"aliyun.com"}),
    ICLOUD_SEND("iCloud", "smtp.mail.me.com", 587, 587, new String[]{"icloud.com"});

    private final String platform;
    private final String smtpHost;
    private final int smtpPort;
    private final int smtpSslPort;
    private final String[] domainNames;

    SMTPEnum(String platform, String smtpHost, int smtpPort, int smtpSslPort, String[] domainNames) {
        this.platform = platform;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.smtpSslPort = smtpSslPort;
        this.domainNames = domainNames;
    }

    public String getPlatform() {
        return platform;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public int getSmtpSslPort() {
        return smtpSslPort;
    }

    public String[] getDomainNames() {
        return domainNames;
    }

    public static SMTPEnum getByEmailFormat(String email) {
        String domain = email.substring(email.lastIndexOf("@") + 1).toLowerCase();
        return Arrays.stream(values())
                .filter(emailServer -> Arrays.asList(emailServer.domainNames).contains(domain))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No platform found for email: " + email));
    }
}
