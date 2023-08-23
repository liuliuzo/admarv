package com.admarv.saas.fbcentre.dto.resp;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class GenderStat implements Serializable {

	private static final long serialVersionUID = -4379394415945499236L;

	private String gender;
	private int count;
	
	public GenderStat(String gender, int count) {
		super();
		this.gender = gender;
		this.count = count;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "GenderStat [gender=" + gender + ", count=" + count + "]";
	}

}
