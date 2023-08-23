package com.admarv.saas.model;

import java.io.Serializable;

public class AdsetInfo implements Serializable {
    private Integer id;

    private String adAccountId;

    private String adsetId;

    private String dailyBudget;

    private String name;

    private String status;

    private String campaignId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdAccountId() {
        return adAccountId;
    }

    public void setAdAccountId(String adAccountId) {
        this.adAccountId = adAccountId == null ? null : adAccountId.trim();
    }

    public String getAdsetId() {
        return adsetId;
    }

    public void setAdsetId(String adsetId) {
        this.adsetId = adsetId == null ? null : adsetId.trim();
    }

    public String getDailyBudget() {
        return dailyBudget;
    }

    public void setDailyBudget(String dailyBudget) {
        this.dailyBudget = dailyBudget == null ? null : dailyBudget.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId == null ? null : campaignId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", adAccountId=").append(adAccountId);
        sb.append(", adsetId=").append(adsetId);
        sb.append(", dailyBudget=").append(dailyBudget);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", campaignId=").append(campaignId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}