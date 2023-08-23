package com.admarv.saas.fbcentre.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class WeekData {

	private String date;
	private String spend;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSpend() {
		return spend;
	}

	public void setSpend(String spend) {
		this.spend = spend;
	}

	@Override
	public String toString() {
		return "WeekData [date=" + date + ", spend=" + spend + "]";
	}

}
