package com.admarv.saas.fb.model.adcreatives;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class AdvantagePlusCreative implements Serializable {

	private static final long serialVersionUID = -4679527692661863474L;
	
	private String enrollStatus;

	public String getEnrollStatus() {
		return this.enrollStatus;
	}

	public void setEnrollStatus(String enrollStatus) {
		this.enrollStatus = enrollStatus;
	}

	@Override
	public String toString() {
		return "AdvantagePlusCreative [enrollStatus=" + enrollStatus + "]";
	}

}
