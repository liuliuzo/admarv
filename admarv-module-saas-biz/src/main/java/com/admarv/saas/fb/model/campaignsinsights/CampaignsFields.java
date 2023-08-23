package com.admarv.saas.fb.model.campaignsinsights;

import java.io.Serializable;

/**
 * 广告系列成效
 * 
 * @author liuliu
 *
 */
public class CampaignsFields implements Serializable {

	private static final long serialVersionUID = -2773435376172314897L;

	private String dailyBudget;
	private String name;
	private String status;
	private String id;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CampaignsInsights [dailyBudget=" + dailyBudget + ", name=" + name + ", status=" + status + ", id=" + id
				+ "]";
	}

}
