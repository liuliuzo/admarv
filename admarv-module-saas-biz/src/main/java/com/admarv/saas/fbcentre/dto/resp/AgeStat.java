package com.admarv.saas.fbcentre.dto.resp;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class AgeStat implements Serializable {

	private static final long serialVersionUID = -4379394415945499236L;

	private String ageRange;
	private int count;
	
	public AgeStat(String ageRange, int count) {
		super();
		this.ageRange = ageRange;
		this.count = count;
	}

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "AgeStat [ageRange=" + ageRange + ", count=" + count + "]";
	}
	
}
