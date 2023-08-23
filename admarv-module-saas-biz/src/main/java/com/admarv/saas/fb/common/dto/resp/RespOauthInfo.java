package com.admarv.saas.fb.common.dto.resp;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class RespOauthInfo implements Serializable {
	
	private static final long serialVersionUID = -2798732957512390483L;

	private String userId;

	private String pltfrm;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPltfrm() {
		return pltfrm;
	}

	public void setPltfrm(String pltfrm) {
		this.pltfrm = pltfrm;
	}

	@Override
	public String toString() {
		return "RespOauthInfo [userId=" + userId + ", pltfrm=" + pltfrm + "]";
	}
	
}
