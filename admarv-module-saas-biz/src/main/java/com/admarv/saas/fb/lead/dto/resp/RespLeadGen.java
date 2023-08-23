package com.admarv.saas.fb.lead.dto.resp;

import java.util.Date;
import java.util.Map;

import com.admarv.saas.fb.lead.domain.model.LeadCust;

/**
 * 广告线索询盘响应
 * 
 * @author liuliu
 *
 */
public class RespLeadGen {
	
	private Integer id;
	
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 邮件
	 */
	private String email;

	/**
	 * 表单创建时间
	 */
	private String crteTm;

	/**
	 * 询盘单状态
	 */
	private String leadStat;

	/**
	 * 地区
	 */
	private String regn;

	/**
	 * 跟进状态
	 */
	private String flwpStat;

	/**
	 * 来源
	 */
	private String rsrc;

	private LeadCust leadCust;

	/**
	 * 是否归档
	 */
	private boolean mgrCRM;

	private String campaignId;
	private String adId;
	private String adsetId;
	private String leadId;
	private String cntct;
	private String crteDt;
	private String formId;
	private String pageId;
	private String ownerId;
	private String ownerName;
	private String userId;
	private String userName;
	private String leadAuality;
    private String customer;
    private Boolean delFlag;
    private String createBy;
    private String updateBy;
    private String startDate;
    private String endDate;
    private Map<String,String> otherFieldsMap;
    
	private boolean validPhoneNum;
	private boolean validWhatsapp;
	private boolean validEmail;
	
	private Date createTime;
    private Date updateTime;
    
    private String whatsApp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCrteTm() {
		return crteTm;
	}

	public void setCrteTm(String crteTm) {
		this.crteTm = crteTm;
	}

	public String getLeadStat() {
		return leadStat;
	}

	public void setLeadStat(String leadStat) {
		this.leadStat = leadStat;
	}

	public String getRegn() {
		return regn;
	}

	public void setRegn(String regn) {
		this.regn = regn;
	}

	public String getFlwpStat() {
		return flwpStat;
	}

	public void setFlwpStat(String flwpStat) {
		this.flwpStat = flwpStat;
	}

	public String getRsrc() {
		return rsrc;
	}

	public void setRsrc(String rsrc) {
		this.rsrc = rsrc;
	}

	public LeadCust getLeadCust() {
		return leadCust;
	}

	public void setLeadCust(LeadCust leadCust) {
		this.leadCust = leadCust;
	}

	public boolean isMgrCRM() {
		return mgrCRM;
	}

	public void setMgrCRM(boolean mgrCRM) {
		this.mgrCRM = mgrCRM;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getAdsetId() {
		return adsetId;
	}

	public void setAdsetId(String adsetId) {
		this.adsetId = adsetId;
	}

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getCntct() {
		return cntct;
	}

	public void setCntct(String cntct) {
		this.cntct = cntct;
	}

	public String getCrteDt() {
		return crteDt;
	}

	public void setCrteDt(String crteDt) {
		this.crteDt = crteDt;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLeadAuality() {
		return leadAuality;
	}

	public void setLeadAuality(String leadAuality) {
		this.leadAuality = leadAuality;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
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
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
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
	
	public Map<String, String> getOtherFieldsMap() {
		return otherFieldsMap;
	}

	public void setOtherFieldsMap(Map<String, String> otherFieldsMap) {
		this.otherFieldsMap = otherFieldsMap;
	}

	public boolean isValidPhoneNum() {
		return validPhoneNum;
	}

	public void setValidPhoneNum(boolean validPhoneNum) {
		this.validPhoneNum = validPhoneNum;
	}

	public boolean isValidWhatsapp() {
		return validWhatsapp;
	}

	public void setValidWhatsapp(boolean validWhatsapp) {
		this.validWhatsapp = validWhatsapp;
	}

	public boolean isValidEmail() {
		return validEmail;
	}

	public void setValidEmail(boolean validEmail) {
		this.validEmail = validEmail;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getWhatsApp() {
		return whatsApp;
	}

	public void setWhatsApp(String whatsApp) {
		this.whatsApp = whatsApp;
	}

	@Override
	public String toString() {
		return "RespLeadGen [id=" + id + ", name=" + name + ", email=" + email + ", crteTm=" + crteTm + ", leadStat="
				+ leadStat + ", regn=" + regn + ", flwpStat=" + flwpStat + ", rsrc=" + rsrc + ", leadCust=" + leadCust
				+ ", mgrCRM=" + mgrCRM + ", campaignId=" + campaignId + ", adId=" + adId + ", adsetId=" + adsetId
				+ ", leadId=" + leadId + ", cntct=" + cntct + ", crteDt=" + crteDt + ", formId=" + formId + ", pageId="
				+ pageId + ", ownerId=" + ownerId + ", ownerName=" + ownerName + ", userId=" + userId + ", userName="
				+ userName + ", leadAuality=" + leadAuality + ", customer=" + customer + ", delFlag=" + delFlag
				+ ", createBy=" + createBy + ", updateBy=" + updateBy + ", startDate=" + startDate + ", endDate="
				+ endDate + ", otherFieldsMap=" + otherFieldsMap + ", validPhoneNum=" + validPhoneNum
				+ ", validWhatsapp=" + validWhatsapp + ", validEmail=" + validEmail + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", whatsApp=" + whatsApp + "]";
	}
	
}
