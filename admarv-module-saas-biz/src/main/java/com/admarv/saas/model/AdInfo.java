package com.admarv.saas.model;

import java.io.Serializable;
import java.util.Date;

public class AdInfo implements Serializable {
    private Integer id;

    private String adId;

    private String dailyBudget;

    private String name;

    private String status;

    private String campaignId;

    private String adsetId;

    private String adAccountId;

    private Boolean delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId == null ? null : adId.trim();
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

    public String getAdsetId() {
        return adsetId;
    }

    public void setAdsetId(String adsetId) {
        this.adsetId = adsetId == null ? null : adsetId.trim();
    }

    public String getAdAccountId() {
        return adAccountId;
    }

    public void setAdAccountId(String adAccountId) {
        this.adAccountId = adAccountId == null ? null : adAccountId.trim();
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", adId=").append(adId);
        sb.append(", dailyBudget=").append(dailyBudget);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", campaignId=").append(campaignId);
        sb.append(", adsetId=").append(adsetId);
        sb.append(", adAccountId=").append(adAccountId);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}