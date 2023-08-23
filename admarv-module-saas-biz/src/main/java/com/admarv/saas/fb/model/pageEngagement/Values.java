package com.admarv.saas.fb.model.pageEngagement;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Values implements Serializable {
	
	private static final long serialVersionUID = -5429271611736980935L;
	
	private Object value;
	private String endTime;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Values [value=" + value + ", endTime=" + endTime + "]";
	}

}
