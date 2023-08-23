package com.admarv.saas.fbcentre.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class GenderData {
	
	private String gender;
	private int count;

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
		return "GenderData [gender=" + gender + ", count=" + count + "]";
	}

}
