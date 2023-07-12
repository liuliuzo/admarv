package com.admarv.saas.fb.ads.constant;

/**
 * https://developers.facebook.com/docs/graph-api/webhooks/getting-started/webhooks-for-leadgen
 * 
 * 权限：lead_retrieval,pages_manage_metadata,pages_show_list
 * 
 * @author liuliu
 *
 */
public class WebhooksConstant {
    
    /**
     * curl -i -X POST "https://graph.facebook.com/{page-id}/subscribed_apps?subscribed_fields=leadgen&access_token={page-access-token}"
     * 
     */
    public static final String WEBHOOK_URL = "https://graph.facebook.com/%s/subscribed_apps?subscribed_fields=leadgen&access_token=%s";
}