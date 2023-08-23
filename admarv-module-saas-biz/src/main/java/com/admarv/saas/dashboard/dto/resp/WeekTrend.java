package com.admarv.saas.dashboard.dto.resp;

import java.util.List;

/**
 * 周数据趋势, 询盘数量
 * 
 * @author liuliu
 *
 */
public class WeekTrend {

    private List<WeekTrendData> weekTrendDataList;

    public List<WeekTrendData> getWeekTrendDataList() {
        return weekTrendDataList;
    }

    public void setWeekTrendDataList(List<WeekTrendData> weekTrendDataList) {
        this.weekTrendDataList = weekTrendDataList;
    }

    @Override
    public String toString() {
        return "WeekTrend [weekTrendDataList=" + weekTrendDataList + "]";
    }
}
