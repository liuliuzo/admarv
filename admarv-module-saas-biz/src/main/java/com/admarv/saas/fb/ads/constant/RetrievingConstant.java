package com.admarv.saas.fb.ads.constant;

/**
 * 广告线索（询盘）
 * https://developers.facebook.com/docs/marketing-api/guides/lead-ads/retrieving
 * 
 * @author liuliu
 * 
 * 权限：ads_management、
 *     pages_read_engagement、
 *     pages_show_list、
 *     pages_manage_metadata、
 *     leads_retrieval
 *     pages_manage_ads
 *
 */
public class RetrievingConstant {
    
    private RetrievingConstant() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * 广告线索下载
     * curl -i -X GET "https://www.facebook.com/ads/lead_gen/export_csv/?id=<FORM_ID>&type=form&from_date=1482698431&to_date=1482784831"
     */
    public static final String EXPORT_CSV_URL ="https://www.facebook.com/ads/lead_gen/export_csv/?id=%s&type=form&from_date=%s&to_date=%s";
    
    /**
     * 广告线索下载
     * curl -X GET -d 'access_token=<ACCESS_TOKEN>' https://graph.facebook.com/v17.0/{lead-id}/
     */
    public static final String LEADID_URL ="https://www.facebook.com/ads/lead_gen/export_csv/?id=%s&type=form&from_date=%s&to_date=%s";


}