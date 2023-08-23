package com.admarv.saas.fb.model.ads;

/**
 * 
 * @author liuliu
 *
 */
public class Data {

	private String id;
	
	private String name;
	
	private String adsId;

	private String dailyBudget;

	private String status;

	private String campaignId;

	private String adsetId;

	private String adAccountId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdsId() {
		return adsId;
	}

	public void setAdsId(String adsId) {
		this.adsId = adsId;
	}

	public String getDailyBudget() {
		return dailyBudget;
	}

	public void setDailyBudget(String dailyBudget) {
		this.dailyBudget = dailyBudget;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getAdsetId() {
		return adsetId;
	}

	public void setAdsetId(String adsetId) {
		this.adsetId = adsetId;
	}

	public String getAdAccountId() {
		return adAccountId;
	}

	public void setAdAccountId(String adAccountId) {
		this.adAccountId = adAccountId;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", name=" + name + ", adsId=" + adsId + ", dailyBudget=" + dailyBudget + ", status="
				+ status + ", campaignId=" + campaignId + ", adsetId=" + adsetId + ", adAccountId=" + adAccountId + "]";
	}

}
