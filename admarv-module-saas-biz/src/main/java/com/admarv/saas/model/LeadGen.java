package com.admarv.saas.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 询盘单实体类
 * 
 * @author liuliu
 *
 */
public class LeadGen implements Serializable {
    
    private static final long serialVersionUID = -8187470259614523213L;

    /**
     * 询盘单ID自增主键
     */
    private Integer id;
    
    /**
     * 广告系列ID
     */
    private String campaignId;
    
    /**
     * 广告ID
     */
    private String adId;
    
    /**
     * 广告组ID
     */
    private String adsetId;
    
    /**
     * 广告线索询盘Id
     */
    private String leadId;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 邮件
     */
    private String email;
    
    /**
     * 联系方式
     */
    private String cntct;

    /**
     * 创建时间
     */
    private String crteTm;
    
    /**
     * 创建日期
     */
    private String crteDt;
    
    /**
     * 阅读状态：01-已读，02-未读
     */
    private String leadStat;
    
    /**
     * 国家
     */
    private String regn;
    
    /**
     * 跟踪状态：01-待跟进，02-已沟通，03-已报价，04-已成交
     */
    private String flwpStat;
    
    /**
     * 来源
     */
    private String rsrc;
    
    /**
     * 表单编号
     */
    private String formId;
    
    /**
     * 公共主页Id
     */
    private String pageId;
    
    /**
     * 跟单员ID
     */
    private String ownerId;
    
    /**
     * 跟单员姓名
     */
    private String ownerName;
    
    private String userId;
    
    /**
     * 询盘所属的sass平台用户名称
     */
    private String userName;
    
    /**
     * 询盘质量
     */
    private String leadAuality;
    
    private String customer;

    private Boolean delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;
    
    private String startDate;
    
    private String endDate;
    
    private String otherFields;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId == null ? null : leadId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCntct() {
        return cntct;
    }

    public void setCntct(String cntct) {
        this.cntct = cntct == null ? null : cntct.trim();
    }

    public String getCrteTm() {
        return crteTm;
    }

    public void setCrteTm(String crteTm) {
        this.crteTm = crteTm == null ? null : crteTm.trim();
    }

    public String getCrteDt() {
        return crteDt;
    }

    public void setCrteDt(String crteDt) {
        this.crteDt = crteDt == null ? null : crteDt.trim();
    }

    public String getLeadStat() {
        return leadStat;
    }

    public void setLeadStat(String leadStat) {
        this.leadStat = leadStat == null ? null : leadStat.trim();
    }

    public String getRegn() {
        return regn;
    }

    public void setRegn(String regn) {
        this.regn = regn == null ? null : regn.trim();
    }

    public String getFlwpStat() {
        return flwpStat;
    }

    public void setFlwpStat(String flwpStat) {
        this.flwpStat = flwpStat == null ? null : flwpStat.trim();
    }

    public String getRsrc() {
        return rsrc;
    }

    public void setRsrc(String rsrc) {
        this.rsrc = rsrc == null ? null : rsrc.trim();
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId == null ? null : formId.trim();
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getLeadAuality() {
        return leadAuality;
    }

    public void setLeadAuality(String leadAuality) {
        this.leadAuality = leadAuality == null ? null : leadAuality.trim();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId == null ? null : campaignId.trim();
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId == null ? null : adId.trim();
    }

    public String getAdsetId() {
        return adsetId;
    }

    public void setAdsetId(String adsetId) {
        this.adsetId = adsetId == null ? null : adsetId.trim();
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

	public String getOtherFields() {
		return otherFields;
	}

	public void setOtherFields(String otherFields) {
		this.otherFields = otherFields;
	}

	@Override
	public String toString() {
		return "LeadGen [id=" + id + ", campaignId=" + campaignId + ", adId=" + adId + ", adsetId=" + adsetId
				+ ", leadId=" + leadId + ", name=" + name + ", email=" + email + ", cntct=" + cntct + ", crteTm="
				+ crteTm + ", crteDt=" + crteDt + ", leadStat=" + leadStat + ", regn=" + regn + ", flwpStat=" + flwpStat
				+ ", rsrc=" + rsrc + ", formId=" + formId + ", pageId=" + pageId + ", ownerId=" + ownerId
				+ ", ownerName=" + ownerName + ", userId=" + userId + ", userName=" + userName + ", leadAuality="
				+ leadAuality + ", customer=" + customer + ", delFlag=" + delFlag + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", updateBy=" + updateBy + ", updateTime=" + updateTime
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", otherFields=" + otherFields + "]";
	}
}