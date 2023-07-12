package com.admarv.saas.fb.page.constant;

/**
 * 
 * @author liuliu
 * 
 * https://developers.facebook.com/docs/pages/
 * 需要权限：manage_pages,pages_manage_ads,pages_manage_metadata,pages_read_engagement,pages_read_user_content
 * 
 * 1.获取公共主页访问口令  https://developers.facebook.com/docs/pages/access-tokens#get-a-page-access-token
 *                   permission: page_show_list
 *                   
 * 2.发布公共主页帖子     https://developers.facebook.com/docs/pages/publishing
 *                   permission: pages_manage_posts, pages_read_engagement
 *                   
 * 3.管理公共主页内容     https://developers.facebook.com/docs/pages/managing
 *                   permission: page_show_list
 *                   
 * 4.获取公共主页成效分析  https://developers.facebook.com/docs/platforminsights/page
 *                   permission:pages_read_engagement, read_insights
 *                   
 * 5.搜索公共主页        https://developers.facebook.com/docs/pages/searching
 *
 */
public class FBPageAccessTokenConstant {
    
    private FBPageAccessTokenConstant() {
        throw new IllegalStateException("FaceBook Constacnt Class");
    }
    
    /**
     * 生成长期用户访问口令,此口令的有效期为60天
     * curl -i -X GET "/oauth/access_token?grant_type=fb_exchange_token&client_id=APP-ID&client_secret=APP-SECRET&fb_exchange_token=SHORT-LIVED-USER-ACCESS-TOKEN"
     */
    public static final String LONG_ACCESS_TOKEN_URL = "/oauth/access_token?grant_type=fb_exchange_token&client_id=%s&client_secret=%s&fb_exchange_token=%s";
    
    /**
     * 短期用户访问口令，则公共主页访问口令的有效期为1小时
     * curl -i -X GET "/PAGE-ID?fields=access_token&access_token=USER-ACCESS-TOKEN"
     */
    public static final String SHORT_ACCESS_TOKEN_URL = "/PAGE-ID?fields=access_token&access_token=%s";
    
    /**
     * 获取公共主页清单
     * curl -i -X GET "/{user-id}/accounts?access_token={user-access-token}"
     * 
     * 权限 pages_show_list
     */
    public static final String ACCOUNTS_URL = "/%s/accounts?access_token=%s";
    
    /**
     * 发布公共主页帖子
     * curl -i -X POST "/{page-id}/feed?message=Hello Fans!&access_token={page-access-token}"
     * 
     * 权限pages_manage_posts,pages_read_engagement
     * 
     */
    public static final String PAGE_FEED_URL = "/%s/feed?message=%s&access_token=%s";
    
    /**
     * 搜索公共主页
     */
    public static final String SEARCH_URL = "/pages/search?q=%s&fields=id,name,location,link&access_token=%s";
    
}
