package com.admarv.saas.dashboard.dto.resp;

/**
 * 核心指标
 * 
 * @author liuliu
 *
 */
public class KeyData {
    // 今日询盘
    private Integer todayLeads;
    // 昨日询盘
    private Integer lastDayLeads;
    // 近7日询盘
    private Integer sevenDayLeads;
    // 近30日询盘
    private Integer thirtyDayLeads;
    // 累计询盘数
    private Integer count;
    // 全网粉丝数量
    private Integer allFansCount;
    // 同比今日询盘
    private String todayLeadsGrowth;
    // 同比昨日询盘
    private String lastDayLeadsGrowth;
    // 同比近7日询盘
    private String sevenDayLeadsGrowth;
    // 同比近30日询盘
    private String thirtyDayLeadsGrowth;
    
    //访客数
    
    //客户数
    
    //询盘数
    
    public Integer getTodayLeads() {
        return todayLeads;
    }

    public void setTodayLeads(Integer todayLeads) {
        this.todayLeads = todayLeads;
    }

    public Integer getLastDayLeads() {
        return lastDayLeads;
    }

    public void setLastDayLeads(Integer lastDayLeads) {
        this.lastDayLeads = lastDayLeads;
    }

    public Integer getSevenDayLeads() {
        return sevenDayLeads;
    }

    public void setSevenDayLeads(Integer sevenDayLeads) {
        this.sevenDayLeads = sevenDayLeads;
    }

    public Integer getThirtyDayLeads() {
        return thirtyDayLeads;
    }

    public void setThirtyDayLeads(Integer thirtyDayLeads) {
        this.thirtyDayLeads = thirtyDayLeads;
    }

    public Integer getAllFansCount() {
        return allFansCount;
    }

    public void setAllFansCount(Integer allFansCount) {
        this.allFansCount = allFansCount;
    }

    public String getTodayLeadsGrowth() {
        return todayLeadsGrowth;
    }

    public void setTodayLeadsGrowth(String todayLeadsGrowth) {
        this.todayLeadsGrowth = todayLeadsGrowth;
    }

    public String getLastDayLeadsGrowth() {
        return lastDayLeadsGrowth;
    }

    public void setLastDayLeadsGrowth(String lastDayLeadsGrowth) {
        this.lastDayLeadsGrowth = lastDayLeadsGrowth;
    }

    public String getSevenDayLeadsGrowth() {
        return sevenDayLeadsGrowth;
    }

    public void setSevenDayLeadsGrowth(String sevenDayLeadsGrowth) {
        this.sevenDayLeadsGrowth = sevenDayLeadsGrowth;
    }

    public String getThirtyDayLeadsGrowth() {
        return thirtyDayLeadsGrowth;
    }

    public void setThirtyDayLeadsGrowth(String thirtyDayLeadsGrowth) {
        this.thirtyDayLeadsGrowth = thirtyDayLeadsGrowth;
    }

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "KeyData [todayLeads=" + todayLeads + ", lastDayLeads=" + lastDayLeads + ", sevenDayLeads="
				+ sevenDayLeads + ", thirtyDayLeads=" + thirtyDayLeads + ", count=" + count + ", allFansCount="
				+ allFansCount + ", todayLeadsGrowth=" + todayLeadsGrowth + ", lastDayLeadsGrowth=" + lastDayLeadsGrowth
				+ ", sevenDayLeadsGrowth=" + sevenDayLeadsGrowth + ", thirtyDayLeadsGrowth=" + thirtyDayLeadsGrowth
				+ "]";
	}
}
