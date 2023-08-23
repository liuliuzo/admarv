package com.admarv.saas.fbcentre.dto.req;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class ReqGetAllAdsAdcreatives implements Serializable  {
	
	private static final long serialVersionUID = 6367565909102241523L;
	
	private String userId;
	private String adsId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAdsId() {
		return adsId;
	}

	public void setAdsId(String adsId) {
		this.adsId = adsId;
	}

	@Override
	public String toString() {
		return "ReqGetAdsAdcreatives [userId=" + userId + ", adsId=" + adsId + "]";
	}
}
