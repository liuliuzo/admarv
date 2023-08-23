package com.admarv.saas.mail.dto.resp;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class RespGetEmailInfo implements Serializable {
	
	private static final long serialVersionUID = -5565424958850774724L;
	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "RespGetAccountInfo [email=" + email + "]";
	}

}
