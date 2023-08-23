package com.admarv.saas.fb.lead.constant;

/**
 * https://developers.facebook.com/docs/marketing-api/reference/v17.0
 * 
 * 广告帐户
 * 
 * /adcreatives 定义广告的展示效果和内容
 * /adimages 在广告创意中使用的图片库。可以单独上传和管理
 * /ads 广告数据，如创意元素和成效衡量信息
 * /adsets 包含使用同一预算、排期、竞价和定位数据的所有广告
 * /advideos 在广告创意中使用的视频库。可以单独上传和管理
 * /campaigns 定义广告系列目标，包含一个或多个广告组
 * /customaudiences 此广告帐户拥有的自定义受众/与之共享的自定义受众
 * /insights 成效分析界面。删除子对象的重复结果，提供整理好的异步报告。
 * /users 与广告帐户关联的用户列表
 * 
 * @author liuliu
 *
 */
public class AdAccountConstant {

    /**
     * curl GET https://graph.facebook.com/v17.0/<USER_ID>/adaccounts
     */
    public static final String ADACCOUNTS_URL="https://graph.facebook.com/v17.0/%s/adaccounts";
    
    /**
     * /adsets 包含使用同一预算、排期、竞价和定位数据的所有广告
     * 
     * curl GET https://graph.facebook.com/v17.0/{ad-account-id}/adsets
     */
    public static final String ADSETS_URL="https://graph.facebook.com/v17.0/{ad-account-id}/adsets";
}


