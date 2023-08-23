package com.admarv.saas.fbcentre.dto.req;

/**
 * 
 * @author liuliu
 *
 */
public class ReqGetAdsAdcreatives {
	
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
