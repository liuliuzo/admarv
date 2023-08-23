package com.admarv.saas.fbcentre.dto.resp;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class LeadInfo implements Serializable {
	
	private static final long serialVersionUID = 2522621339096442159L;

	/**
	 * 累计客户数量
	 */
	private Integer customerCount;
	
	/**
	 * 累计线索数量
	 */
	private Integer leadCount;

	/**
	 * 已成交订单数量
	 */
	private Integer orderCount;

	public Integer getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(Integer customerCount) {
		this.customerCount = customerCount;
	}

	public Integer getLeadCount() {
		return leadCount;
	}

	public void setLeadCount(Integer leadCount) {
		this.leadCount = leadCount;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	@Override
	public String toString() {
		return "LeadInfo [customerCount=" + customerCount + ", leadCount=" + leadCount + ", orderCount=" + orderCount
				+ "]";
	}
}
