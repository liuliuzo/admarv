package com.admarv.saas.fbcentre.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class Header {
	
	/**
	 * 今日询盘数量
	 */
	private int leadToday;
	
	/**
	 * 广告费上限
	 */
	private String spendCap;
	
	/**
	 * 每日上限
	 */
	private String dailyBudget;
	
	/**
	 * 花费
	 */
	private String spend;
	
	/**
	 * 点击数
	 */
	private String clicks;
	
	/**
	 * 点击率
	 */
	private String clickRate;
	
	/**
	 * 单次点击费用
	 */
	private String cpc;
	
	/**
	 * 展示次数
	 */
	private String impression;
	
	/**
	 * 独立点击次数 unique clicks
	 */
	private String unqCount;
	
	/**
	 * 覆盖人数
	 */
	private String reach;
	
	public String getSpend() {
		return spend;
	}

	public void setSpend(String spend) {
		this.spend = spend;
	}

	public String getClicks() {
		return clicks;
	}

	public void setClicks(String clicks) {
		this.clicks = clicks;
	}

	public String getClickRate() {
		return clickRate;
	}

	public void setClickRate(String clickRate) {
		this.clickRate = clickRate;
	}

	public String getCpc() {
		return cpc;
	}

	public void setCpc(String cpc) {
		this.cpc = cpc;
	}

	public String getUnqCount() {
		return unqCount;
	}

	public void setUnqCount(String unqCount) {
		this.unqCount = unqCount;
	}

	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}

	public int getLeadToday() {
		return leadToday;
	}

	public void setLeadToday(int leadToday) {
		this.leadToday = leadToday;
	}

	public String getSpendCap() {
		return spendCap;
	}

	public void setSpendCap(String spendCap) {
		this.spendCap = spendCap;
	}

	public String getDailyBudget() {
		return dailyBudget;
	}

	public void setDailyBudget(String dailyBudget) {
		this.dailyBudget = dailyBudget;
	}

	public String getReach() {
		return reach;
	}

	public void setReach(String reach) {
		this.reach = reach;
	}

	@Override
	public String toString() {
		return "Header [leadToday=" + leadToday + ", spendCap=" + spendCap + ", dailyBudget=" + dailyBudget + ", spend="
				+ spend + ", clicks=" + clicks + ", clickRate=" + clickRate + ", cpc=" + cpc + ", impression="
				+ impression + ", unqCount=" + unqCount + ", reach=" + reach + "]";
	}
	
}
