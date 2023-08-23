package com.admarv.saas.fb.model.leadgen;

import java.io.Serializable;

public class Value implements Serializable {

	private static final long serialVersionUID = -751310539116988801L;
	
	private String adId;
	private String formId;
	private String leadgenId;
	private Integer createdTime;
	private String pageId;
	private String adgroupId;

	public String getAdId() {
		return this.adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getLeadgenId() {
		return this.leadgenId;
	}

	public void setLeadgenId(String leadgenId) {
		this.leadgenId = leadgenId;
	}

	public Integer getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Integer createdTime) {
		this.createdTime = createdTime;
	}

	public String getPageId() {
		return this.pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getAdgroupId() {
		return this.adgroupId;
	}

	public void setAdgroupId(String adgroupId) {
		this.adgroupId = adgroupId;
	}

	@Override
	public String toString() {
		return "Value [adId=" + adId + ", formId=" + formId + ", leadgenId=" + leadgenId + ", createdTime="
				+ createdTime + ", pageId=" + pageId + ", adgroupId=" + adgroupId + "]";
	}

}
