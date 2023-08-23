package com.admarv.saas.fbcentre.dto.resp;

import java.util.List;

/**
 * 
 * @author liuliu
 *
 */
public class AccountInfo {
	
	private String totalSpend;
	
	private String totalSpendCn;
	
	private String balance;
	
	private String balanceCn;
	
	/**
	 * 近7天的花费
	 */
	private List<WeekData> listWeekData;

	public String getTotalSpend() {
		return totalSpend;
	}

	public void setTotalSpend(String totalSpend) {
		this.totalSpend = totalSpend;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	public String getTotalSpendCn() {
		return totalSpendCn;
	}

	public void setTotalSpendCn(String totalSpendCn) {
		this.totalSpendCn = totalSpendCn;
	}

	public String getBalanceCn() {
		return balanceCn;
	}

	public void setBalanceCn(String balanceCn) {
		this.balanceCn = balanceCn;
	}

	public List<WeekData> getListWeekData() {
		return listWeekData;
	}

	public void setListWeekData(List<WeekData> listWeekData) {
		this.listWeekData = listWeekData;
	}

	@Override
	public String toString() {
		return "AccountInfo [totalSpend=" + totalSpend + ", totalSpendCn=" + totalSpendCn + ", balance=" + balance
				+ ", balanceCn=" + balanceCn + ", listWeekData=" + listWeekData + "]";
	}
}
