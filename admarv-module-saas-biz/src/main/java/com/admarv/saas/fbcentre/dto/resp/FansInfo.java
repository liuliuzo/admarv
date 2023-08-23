package com.admarv.saas.fbcentre.dto.resp;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author liuliu
 *
 */
public class FansInfo implements Serializable {

	private static final long serialVersionUID = 3502675356511172024L;

	/**
	 * 粉丝总数
	 */
	private int fansCount;

	/**
	 * 月增长粉丝
	 */
	private int growFans;

	/**
	 * 月流失粉丝
	 */
	private int unlikeFans;

	/**
	 * 付费粉丝浏览贴文次数
	 */
	private int paidImpressions;

	/**
	 * 自然粉丝浏览贴文次数
	 */
	private int orgaincImpressions;
	
	/**
	 * 性别分布
	 */
	private List<GenderStat> listGenderStat;
	
	/**
	 * 年龄分布
	 */
	private List<AgeStat> listAgeStat;
	
	
	public int getFansCount() {
		return fansCount;
	}

	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}

	public int getGrowFans() {
		return growFans;
	}

	public void setGrowFans(int growFans) {
		this.growFans = growFans;
	}

	public int getUnlikeFans() {
		return unlikeFans;
	}

	public void setUnlikeFans(int unlikeFans) {
		this.unlikeFans = unlikeFans;
	}

	public int getPaidImpressions() {
		return paidImpressions;
	}

	public void setPaidImpressions(int paidImpressions) {
		this.paidImpressions = paidImpressions;
	}

	public int getOrgaincImpressions() {
		return orgaincImpressions;
	}

	public void setOrgaincImpressions(int orgaincImpressions) {
		this.orgaincImpressions = orgaincImpressions;
	}

	public List<GenderStat> getListGenderStat() {
		return listGenderStat;
	}

	public void setListGenderStat(List<GenderStat> listGenderStat) {
		this.listGenderStat = listGenderStat;
	}

	public List<AgeStat> getListAgeStat() {
		return listAgeStat;
	}

	public void setListAgeStat(List<AgeStat> listAgeStat) {
		this.listAgeStat = listAgeStat;
	}

	@Override
	public String toString() {
		return "FansInfo [fansCount=" + fansCount + ", growFans=" + growFans + ", unlikeFans=" + unlikeFans
				+ ", paidImpressions=" + paidImpressions + ", orgaincImpressions=" + orgaincImpressions
				+ ", listGenderStat=" + listGenderStat + ", listAgeStat=" + listAgeStat + "]";
	}

}
