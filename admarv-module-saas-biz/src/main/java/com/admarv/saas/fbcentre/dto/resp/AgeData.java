package com.admarv.saas.fbcentre.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class AgeData {

	private String ageRang;
	private int count;

	public String getAgeRang() {
		return ageRang;
	}

	public void setAgeRang(String ageRang) {
		this.ageRang = ageRang;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "AgeData [ageRang=" + ageRang + ", count=" + count + "]";
	}
	
}
