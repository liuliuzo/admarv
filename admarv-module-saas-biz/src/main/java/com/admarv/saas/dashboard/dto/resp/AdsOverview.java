package com.admarv.saas.dashboard.dto.resp;

/**
 * 广告成效数据概览
 * 
 * @author liuliu
 *
 *  spend,impressions,clicks,cpc,cost_per_conversion,conversions
 *
 *
 */
public class AdsOverview {
    //花费
    private String spend;
    
    //广告展示次数
    private String impressions;
    
    //点击量
    private String clicks;
    
    //单次点击费用, cpc平均每次点击的成本
    private String cpc;
    
    //单次成交费用 = 总费用/询盘数量      spend/lead
    private String costPerConv;
    
    //点击率=用点击次数除展示次数呢        clicks/impressions
    private String clicksImpressions;
    
    //转化率 = 询盘数量/展示次数         lead/impressions
    private String leadImpressions;
    
    //转化率 = 询盘数量/点击次数
    private String leadClicks;

    public String getSpend() {
        return spend;
    }

    public void setSpend(String spend) {
        this.spend = spend;
    }

    public String getImpressions() {
        return impressions;
    }

    public void setImpressions(String impressions) {
        this.impressions = impressions;
    }

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getCpc() {
        return cpc;
    }

    public void setCpc(String cpc) {
        this.cpc = cpc;
    }

    public String getCostPerConv() {
        return costPerConv;
    }

    public void setCostPerConv(String costPerConv) {
        this.costPerConv = costPerConv;
    }

    public String getClicksImpressions() {
        return clicksImpressions;
    }

    public void setClicksImpressions(String clicksImpressions) {
        this.clicksImpressions = clicksImpressions;
    }

    public String getLeadImpressions() {
        return leadImpressions;
    }

    public void setLeadImpressions(String leadImpressions) {
        this.leadImpressions = leadImpressions;
    }

    public String getLeadClicks() {
        return leadClicks;
    }

    public void setLeadClicks(String leadClicks) {
        this.leadClicks = leadClicks;
    }

    @Override
    public String toString() {
        return "AdsOverview [spend=" + spend + ", impressions=" + impressions + ", clicks=" + clicks + ", cpc=" + cpc
                + ", costPerConv=" + costPerConv + ", clicksImpressions=" + clicksImpressions + ", leadImpressions="
                + leadImpressions + ", leadClicks=" + leadClicks + "]";
    }

}
