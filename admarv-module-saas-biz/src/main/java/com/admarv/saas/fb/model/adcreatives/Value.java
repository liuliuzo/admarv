package com.admarv.saas.fb.model.adcreatives;

import java.io.Serializable;

public class Value implements Serializable {

	private static final long serialVersionUID = 5281150381743276694L;
	private String leadGenFormId;
	private String link;

	public String getLeadGenFormId() {
		return this.leadGenFormId;
	}

	public void setLeadGenFormId(String leadGenFormId) {
		this.leadGenFormId = leadGenFormId;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Value [leadGenFormId=" + leadGenFormId + ", link=" + link + "]";
	}

}
