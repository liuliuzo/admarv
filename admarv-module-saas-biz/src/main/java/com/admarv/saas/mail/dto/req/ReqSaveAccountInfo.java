package com.admarv.saas.mail.dto.req;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class ReqSaveAccountInfo implements Serializable {
	
	private static final long serialVersionUID = -7615766886269983230L;
	
	private String userId;
	private String email;
	private String authCode;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@Override
	public String toString() {
		return "ReqSaveAccountInfo [userId=" + userId + ", email=" + email + ", authCode=" + authCode + "]";
	}
}
