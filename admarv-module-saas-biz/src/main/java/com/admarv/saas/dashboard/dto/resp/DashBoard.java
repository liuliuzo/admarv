package com.admarv.saas.dashboard.dto.resp;

/**
 * 数据中心响应对象
 * 
 * @author liuliu
 *
 */
public class DashBoard {
    
    /**
     * 核心指标
     */
    private KeyData keyData;
    
    /**
     * 广告数据概览
     */
    private AdsOverview adsOverview;
    
    
    /**
     * FB资产情况
     */
    private FBAssetsSituation fbAssetsSituation;
    
    /**
     * 周趋势
     */
    private WeekTrend weekTrend;
    
    /**
     * 月趋势
     */
    private MonthlyTrend monthlyTrend;
    
    /**
     * 季度趋势
     */
    private QuarterTrend quarterTrend;

    public KeyData getKeyData() {
        return keyData;
    }

    public void setKeyData(KeyData keyData) {
        this.keyData = keyData;
    }

    public AdsOverview getAdsOverview() {
        return adsOverview;
    }

    public void setAdsOverview(AdsOverview adsOverview) {
        this.adsOverview = adsOverview;
    }

    public WeekTrend getWeekTrend() {
        return weekTrend;
    }

    public void setWeekTrend(WeekTrend weekTrend) {
        this.weekTrend = weekTrend;
    }

    public MonthlyTrend getMonthlyTrend() {
        return monthlyTrend;
    }

    public void setMonthlyTrend(MonthlyTrend monthlyTrend) {
        this.monthlyTrend = monthlyTrend;
    }

    public QuarterTrend getQuarterTrend() {
        return quarterTrend;
    }

    public void setQuarterTrend(QuarterTrend quarterTrend) {
        this.quarterTrend = quarterTrend;
    }

    public FBAssetsSituation getFbAssetsSituation() {
        return fbAssetsSituation;
    }

    public void setFbAssetsSituation(FBAssetsSituation fbAssetsSituation) {
        this.fbAssetsSituation = fbAssetsSituation;
    }

    @Override
    public String toString() {
        return "DashBoard [keyData=" + keyData + ", adsOverview=" + adsOverview + ", weekTrend=" + weekTrend
                + ", monthlyTrend=" + monthlyTrend + ", quarterTrend=" + quarterTrend + ", fbAssetsSituation="
                + fbAssetsSituation + "]";
    }

}
