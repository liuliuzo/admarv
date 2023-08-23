package com.admarv.saas.dashboard.dto.resp;

import java.util.List;

/**
 * 月趋势，询盘数量
 * 
 * @author liuliu
 *
 */
public class MonthlyTrend {
    
    private List<MonthlyData> monthlyDataList;

    public List<MonthlyData> getMonthlyDataList() {
        return monthlyDataList;
    }

    public void setMonthlyDataList(List<MonthlyData> monthlyDataList) {
        this.monthlyDataList = monthlyDataList;
    }

    @Override
    public String toString() {
        return "MonthlyTrend [monthlyDataList=" + monthlyDataList + "]";
    }
}
