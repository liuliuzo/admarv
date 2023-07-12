package com.weiyou.ads.auth;

import com.advich.adcloud.common.dto.RequestFBDto;
import com.advich.adcloud.common.utils.FastJsonUtil;
import com.advich.adcloud.common.utils.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OpenApiFBService {

    //引入日志
    private static Logger logger = LoggerFactory.getLogger(OpenApiFBService.class);

    @Value("${facebook.client.id}")
    private String facebookClintId;
    @Value("${facebook.client.secret}")
    private String facebookClientSecret;
    @Value("${facebook.scope}")
    private String facebookScope;
    @Value("${facebook.url.callback}")
    private String facebookUrlCallback;

    /**
     *
     * @return
     */
    public OAuth20Service initFaceBookOAuth(RequestFBDto requestFBDto) {
        final OAuth20Service service = new ServiceBuilder(facebookClintId)
                .apiSecret(facebookClientSecret)
                .defaultScope(facebookScope)
//                .callback(facebookUrlCallback)
                .callback(requestFBDto.getRequestUrl())
                .build(FacebookApi.instance());
        return service;
    }

    public String getPageAccessToken(String pageId, String accessToken) {
        logger.info("accessToken：" + accessToken);
        String testUrl = "https://graph.facebook.com/v5.0/me?fields=access_token";
        String testUrl2 = testUrl.replace("me", pageId);
        Map<String, String> param = new HashMap<>(0);
        param.put("access_token", accessToken);
        String fbResult = HttpClientUtil.doGet(testUrl2, param);
        logger.info("fbResult：" + fbResult);
        RequestFBDto requestFBDto = FastJsonUtil.parseObject(fbResult, RequestFBDto.class);
        return requestFBDto.getAccess_token();
    }

    /**
     * 获取FB用户accessToken信息
     *
     * @param accessToken
     * @return
     */
    public String getAccessToken(String accessToken) {
        String testUrl2 = "https://graph.facebook.com/v5.0/me/accounts";
        Map<String, String> param = new HashMap<>(0);
        param.put("access_token", accessToken);
        logger.info("accessToken：" + accessToken);
        String fbResult = HttpClientUtil.doGet(testUrl2, param);
        logger.info("fbResult：" + fbResult);
        JSONObject jsonObject = JSONObject.parseObject(fbResult);
        logger.info("jsonObject.get(\"data\").toString()：" + jsonObject.get("data").toString());
        List<RequestFBDto> data = FastJsonUtil.parseList(jsonObject.get("data").toString(), RequestFBDto.class);
        logger.info("data:" + data.get(0).getAccess_token());
        return data.get(0).getAccess_token();
    }

    public String getRefreshToken(String accessToken) {
        String testUrl2 = "https://graph.facebook.com/v5.0/oauth/access_token";
        Map<String, String> param = new HashMap<>(0);
//        param.put("access_token", accessToken);
        param.put("grant_type", "fb_exchange_token");
        param.put("client_id", facebookClintId);
        param.put("client_secret", facebookClientSecret);
        param.put("fb_exchange_token", accessToken);
        String fbResult = HttpClientUtil.doGet(testUrl2, param);
        logger.info("fbResult：" + fbResult);
        JSONObject jsonObject = JSONObject.parseObject(fbResult);
        logger.info("jsonObject.get(\"access_token\").toString()：" + jsonObject.get("access_token").toString());
        return jsonObject.get("access_token").toString();
    }

}
