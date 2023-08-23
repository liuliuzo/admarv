package com.admarv.saas.dashboard.dto.resp;

import java.util.List;

/**
 * 季度趋势，询盘数量
 * 
 * @author liuliu
 *
 */
public class QuarterTrend {
    
    private List<QuarterData> quarterDataList;

    public List<QuarterData> getQuarterDataList() {
        return quarterDataList;
    }

    public void setQuarterDataList(List<QuarterData> quarterDataList) {
        this.quarterDataList = quarterDataList;
    }

    @Override
    public String toString() {
        return "QuarterTrend [quarterDataList=" + quarterDataList + "]";
    }
}
