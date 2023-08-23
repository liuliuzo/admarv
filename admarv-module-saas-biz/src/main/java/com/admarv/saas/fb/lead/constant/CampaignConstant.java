package com.admarv.saas.fb.lead.constant;

/**
 *  广告系列相关
 *  
 *  https://developers.facebook.com/docs/marketing-api/campaign-structure
 *  https://developers.facebook.com/docs/marketing-api/reference/v17.0
 *  
 *  Oauth权限：ads_read, ads_management 
 *  
 *  @author liuliu
 *
 */
public class CampaignConstant {
    
    private CampaignConstant() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * 广告系列
     * 
     * curl -X GET -d 'effective_status=["ACTIVE","PAUSED"]' -d 'fields="name,objective"' -d 'access_token=<ACCESS_TOKEN>' https://graph.facebook.com/v17.0/act_<AD_ACCOUNT_ID>/campaigns
     * 
     */
    public static final String CAMPAIGNS_URL = "https://graph.facebook.com/v17.0/act_%s/campaigns";
            
    /** 
     * 广告系列详情
     *  curl -G 
     *   -d 'end_time=1517287567' 
     *   -d 'fields=impressions,inline_link_clicks,spend' 
     *   -d 'access_token=<ACCESS_TOKEN>' 
     *    https://graph.facebook.com/v2.11/<CAMPAIGN_ID>/insights
     * 
     */
    public static final String CAMPAIGNS_INSIGHTS_URL = "https://graph.facebook.com/v17.0/%s/insights";
            
    
    /**
     * 广告组
     * 
     * curl -X GET \
     *  -d 'fields="name,start_time,end_time,daily_budget,lifetime_budget"' \
     *  -d 'access_token=<ACCESS_TOKEN>' \
     *   https://graph.facebook.com/v17.0/<AD_CAMPAIGN_ID>/adsets
     * 
     */
    public static final String ADSETS_URL = "https://graph.facebook.com/v17.0/%s/adsets";
}
