package com.admarv.saas.chat.whatsapp.model.webhook;

import java.io.Serializable;

public class Pricing implements Serializable {
	
	private static final long serialVersionUID = -2163731530804734219L;
	
	private String billable;
	private String pricingModel;
	private String category;

	public String getBillable() {
		return this.billable;
	}

	public void setBillable(String billable) {
		this.billable = billable;
	}

	public String getPricingModel() {
		return this.pricingModel;
	}

	public void setPricingModel(String pricingModel) {
		this.pricingModel = pricingModel;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Pricing [billable=" + billable + ", pricingModel=" + pricingModel + ", category=" + category + "]";
	}
	
}
