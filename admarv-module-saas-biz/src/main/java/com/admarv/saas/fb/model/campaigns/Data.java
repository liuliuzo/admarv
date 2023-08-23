package com.admarv.saas.fb.model.campaigns;

/**
 * 
 * @author liuliu
 *
 */
public class Data {

    private String id;

    private String adAccountId;

    private String campaignId;

    private String dailyBudget;

    private String name;

    private String status;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdAccountId() {
		return adAccountId;
	}

	public void setAdAccountId(String adAccountId) {
		this.adAccountId = adAccountId;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getDailyBudget() {
		return dailyBudget;
	}

	public void setDailyBudget(String dailyBudget) {
		this.dailyBudget = dailyBudget;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", adAccountId=" + adAccountId + ", campaignId=" + campaignId + ", dailyBudget="
				+ dailyBudget + ", name=" + name + ", status=" + status + "]";
	}
	
}
