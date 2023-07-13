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
    // 全网粉丝数量
    private Integer allFansCount;
    // 近7日询盘
    private Integer sevenDayLeads;
    // 近30日询盘
    private Integer thirtyDayLeads;

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

    public Integer getAllFansCount() {
        return allFansCount;
    }

    public void setAllFansCount(Integer allFansCount) {
        this.allFansCount = allFansCount;
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

    @Override
    public String toString() {
        return "KeyData [todayLeads=" + todayLeads + ", lastDayLeads=" + lastDayLeads + ", allFansCount=" + allFansCount
                + ", sevenDayLeads=" + sevenDayLeads + ", thirtyDayLeads=" + thirtyDayLeads + "]";
    }

}
