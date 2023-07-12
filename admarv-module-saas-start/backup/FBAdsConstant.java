package org.jeecg.admarv.saas.fb.ads;

/**
 * 
 * @author liuliu
 * 
 * Ads Management Standard Access
 * 
 * permission
 *   ads_read
 *   ads_management
 *   ads_management
 *   
 *   https://developers.facebook.com/docs/marketing-api/campaign-structure
 * 
 */
public class FBAdsConstant {
    
    private FBAdsConstant() {
        throw new IllegalStateException("Constacnt class");
    }
    
    
    
    
    
    
    /**
     * Facebook Ads API 提供了 ads_volume 接口，可以获取针对目标受众的预估覆盖范围和展示次数。这个接口帮助广告主在启动广告活动之前了解潜在的广告覆盖范围。要使用 ads_volume 接口
     */
    public static final String ADS_VOLUME_URL="https://graph.facebook.com/v17.0/act_%s/ads_volume";
    
    /**
     * 使用 Facebook Ads API，您可以通过 Ad Account Ad Creatives 接口来管理广告账户中的广告创意
     * 
     */
    public static final String ADCREATIVES_URL="https://graph.facebook.com/v17.0/{ad_%s}/adcreatives";
    
    /**
     * Campaign
     * 
     */
    
    
    curl -X POST -F 'name="My campaign"' \-F 'objective="OUTCOME_TRAFFIC"' \ -F 'status="PAUSED"' \ -F 'special_ad_categories=[]' \ -F 'access_token=<ACCESS_TOKEN>' \
    https://graph.facebook.com/v17.0/act_<AD_ACCOUNT_ID>/campaigns
    
    
    /**
     * Ad Set
     */
    
    /**
     * Ad
     */
    
    /**
     * Creative
     */
    
    /**
     * 广告线索
     *  curl -i -X GET "https://www.facebook.com/ads/lead_gen/export_csv/?id=<FORM_ID>&type=form&from_date=1482698431&to_date=1482784831"
     * 
     */
    public static final String LEAD_GEN_EXPORT_CSV_URL="https://www.facebook.com/ads/lead_gen/export_csv/?id=%s&type=form&from_date=%s&to_date=%s";
    
    
    
    
    
}
