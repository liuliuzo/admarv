package com.admarv.saas.mail.constant;

import java.util.Arrays;

/**
 * 
 * @author liuliu
 *
 */
public enum POP3Enum {
	
	P0P3_163_RECEIVE("163", "pop.163.com", 110, 995, new String[] { "163.com" }),
	HOTMAIL_RECEIVE("Hotmail", "pop3.live.com", 110, 995, new String[] { "outlook.com" }),
	OUTLOOK_RECEIVE("outlook", "pop3.live.com", 110, 995, new String[] { "hotmail.com" }),
	GMAIL_RECEIVE("gmail", "pop.gmail.com", 110, 995, new String[] { "gamil.com" });
	
//	NETEASE_RECEIVE("NetEase", "smtphz.qiye.163.com", 25, 465, new String[] {"admarv.com"}),
//  QQ_RECEIVE("Tencent QQ", "smtp.qq.com", 587, 465, new String[]{"qq.com"}),
//  ALIYUN_RECEIVE("Alibaba", "smtp.mxhichina.com", 465, 465, new String[]{"aliyun.com"}),
//  GMAIL_RECEIVE("Google Gmail", "smtp.gmail.com", 587, 465, new String[]{"gmail.com"}),
//  ICLOUD_RECEIVE("iCloud", "smtp.mail.me.com", 587, 587, new String[]{"icloud.com"});
	
    private final String platform;
    private final String popHost;
    private final int popPort;
    private final int popSslPort;
    private final String[] domainNames;
    
    private POP3Enum(String platform, String popHost, int popPort, int popSslPort, String[] domainNames) {
		this.platform = platform;
		this.popHost = popHost;
		this.popPort = popPort;
		this.popSslPort = popSslPort;
		this.domainNames = domainNames;
	}

	public String getPlatform() {
		return platform;
	}



	public String getPopHost() {
		return popHost;
	}



	public int getPopPort() {
		return popPort;
	}



	public int getPopSslPort() {
		return popSslPort;
	}



	public String[] getDomainNames() {
		return domainNames;
	}

	public static POP3Enum getByEmailFormat(String email) {
        String domain = email.substring(email.lastIndexOf("@") + 1).toLowerCase();
        return Arrays.stream(values())
                .filter(emailServer -> Arrays.asList(emailServer.domainNames).contains(domain))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No platform found for email: " + email));
    }
}
