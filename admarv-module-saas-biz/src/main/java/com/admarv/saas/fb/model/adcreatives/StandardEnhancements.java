package com.admarv.saas.fb.model.adcreatives;

import java.io.Serializable;

public class StandardEnhancements implements Serializable {

	private static final long serialVersionUID = 7154496061046471180L;

	private String enrollStatus;

	public String getEnrollStatus() {
		return this.enrollStatus;
	}

	public void setEnrollStatus(String enrollStatus) {
		this.enrollStatus = enrollStatus;
	}

	@Override
	public String toString() {
		return "StandardEnhancements [enrollStatus=" + enrollStatus + "]";
	}

}
