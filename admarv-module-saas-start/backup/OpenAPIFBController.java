package com.weiyou.ads.auth;

import com.advich.adcloud.common.domain.OpenApiRequest;
import com.advich.adcloud.common.domain.OpenApiResponse;
import com.advich.adcloud.common.dto.RequestFBDto;
import com.advich.adcloud.common.utils.*;
import com.advich.google.ads.controller.BaseController;
import com.advich.sm.facebook.service.OpenApiFBService;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RequestMapping("api/sm/fb")
@RestController
public class OpenAPIFBController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(OpenAPIFBController.class);
    @Resource
    private OpenApiFBService openApiFBService;
    
    @Value("${facebook.client.pwd}")
    private String facebookClientPwd;

    @RequestMapping(value = "userInfo")
    public OpenApiResponse userInfo(@RequestBody OpenApiRequest apiRequest, OpenApiResponse apiResponse) throws InterruptedException, ExecutionException, IOException {
        logger.info(FastJsonUtil.toJSONString(apiRequest));
        RequestFBDto requestData = getRequestData(apiRequest, RequestFBDto.class);
        String testUrl2 = "https://graph.facebook.com/v5.0/me/accounts";
        Map<String, String> param = new HashMap<>(0);
        param.put("access_token", requestData.getAccess_token());
        logger.info("accessToken：" + requestData.getAccess_token());
        String fbResult = HttpClientUtil.doGet(testUrl2, param);
        logger.info("fbResult：" + fbResult);
        apiResponse.setData(fbResult);
        return apiResponse;
    }

    @RequestMapping(value = "getRefreshToken")
    public OpenApiResponse getRefreshToken(@RequestBody OpenApiRequest apiRequest, OpenApiResponse apiResponse) {
        logger.info(FastJsonUtil.toJSONString(apiRequest));
        RequestFBDto requestData = getRequestData(apiRequest, RequestFBDto.class);
        String accessToken = openApiFBService.getRefreshToken(requestData.getAccess_token());
        apiResponse.setData(accessToken);
        return apiResponse;
    }

    @RequestMapping(value = "publishPost")
    public OpenApiResponse publishPost(@RequestBody OpenApiRequest apiRequest, OpenApiResponse apiResponse) throws Exception {
        logger.info(FastJsonUtil.toJSONString(apiRequest));
        RequestFBDto requestData = getRequestData(apiRequest, RequestFBDto.class);
        Map<String, Object> result = new HashMap<>();

        String testUrl3 = "";
        List<String> mediaFbids = requestData.getMediaFbid();
        if (ListUtil.notEmpty(mediaFbids)) {
            testUrl3 = "https://graph.facebook.com/v5.0/me/feed";
        } else {
            if (StringUtil.notEmpty(requestData.getImgPath())) {
                testUrl3 = "https://graph.facebook.com/v5.0/me/photos";
            } else {
                testUrl3 = "https://graph.facebook.com/v5.0/me/feed";
            }
        }
        logger.info("testUrl3：" + testUrl3);
        OAuth20Service service = openApiFBService.initFaceBookOAuth(requestData);
        final OAuthRequest requestFeed = new OAuthRequest(Verb.POST, testUrl3);
        if (ListUtil.notEmpty(mediaFbids)) {
            for (int i = 0; i < mediaFbids.size(); i++) {
                requestFeed.addParameter("attached_media[" + i + "]", "{\"media_fbid\":\"" + mediaFbids.get(i) + "\"}");
            }
        }
        requestFeed.addParameter("message", requestData.getMessage());
        String accessToken = openApiFBService.getPageAccessToken(requestData.getPageId(), requestData.getAccess_token());
//        String appsecretProof = MD5.getSignature("HmacSHA256",requestData.getAccess_token(),facebookClientPwd);
//        logger.info("appsecretProof："+appsecretProof);
        String appsecretProof1 = Hashing.hmacSha256(facebookClientPwd.getBytes()).hashString(requestData.getAccess_token(), StandardCharsets.UTF_8).toString();
        logger.info("appsecretProof1：" + appsecretProof1);
        requestFeed.addParameter("appsecret_proof", appsecretProof1);
        logger.info("accessToken：" + accessToken);
        if (StringUtil.notEmpty(requestData.getAddLink())) {
            requestFeed.addParameter("link", requestData.getAddLink());
        }

        if (StringUtil.notEmpty(requestData.getImgPath())) {
            logger.info("admpFacebookPost.getImgPath()：" + requestData.getImgPath());
            requestFeed.addParameter("url", requestData.getImgPath());
            requestFeed.addParameter("caption", "photo");
        }

        if(StringUtil.notEmpty(requestData.getTiming())){
            logger.info("当前时间戳："+System.currentTimeMillis()/1000);
            Date timing = DateUtil.getFormatDate(requestData.getTiming(), DateUtil.DATE_FORMAT_FIVE);
            logger.info("timing:" + timing);
            long timingTimestamp = timing.getTime() / 1000;
            logger.info("timingTimestamp:" + timingTimestamp);
            if(System.currentTimeMillis()/1000 > timingTimestamp){
                logger.info("大");
                result.put("code",1001);
                result.put("content","bad choose time");
                apiResponse.setData(FastJsonUtil.toJSONString(result));
            }else{
                requestFeed.addParameter("published", "false");
                requestFeed.addParameter("scheduled_publish_time", String.valueOf(timingTimestamp));
                service.signRequest(accessToken, requestFeed);
                try (Response responseFeed = service.execute(requestFeed)) {
                    logger.info("--------开始发帖--------------");
                    logger.info("responseFeed.getCode()：" + responseFeed.getCode());
                    logger.info("responseFeed.getBody()：" + responseFeed.getBody());
                    logger.info("--------结束--------------");
                    result.put("code", responseFeed.getCode());
                    result.put("content", responseFeed.getBody());
                    apiResponse.setData(FastJsonUtil.toJSONString(result));
                } catch (IOException | InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }else{
            service.signRequest(accessToken, requestFeed);
            try (Response responseFeed = service.execute(requestFeed)) {
                logger.info("--------开始发帖--------------");
                logger.info("responseFeed.getCode()：" + responseFeed.getCode());
                logger.info("responseFeed.getBody()：" + responseFeed.getBody());
                logger.info("--------结束--------------");
                result.put("code", responseFeed.getCode());
                result.put("content", responseFeed.getBody());
                apiResponse.setData(FastJsonUtil.toJSONString(result));
            } catch (IOException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return apiResponse;
    }

    @RequestMapping(value = "deletePost")
    public OpenApiResponse deletePost(@RequestBody OpenApiRequest apiRequest, OpenApiResponse apiResponse) throws InterruptedException, ExecutionException, IOException {
        logger.info(FastJsonUtil.toJSONString(apiRequest));
        RequestFBDto requestData = getRequestData(apiRequest, RequestFBDto.class);
        String accessToken = openApiFBService.getAccessToken(requestData.getAccess_token());
        String testUrl2 = "https://graph.facebook.com/post-id";
        String testUrl3 = testUrl2.replace("post-id", requestData.getPostId());
        logger.info("testUrl3：" + testUrl3);
        OAuth20Service service = openApiFBService.initFaceBookOAuth(requestData);
        final OAuthRequest requestFeed = new OAuthRequest(Verb.DELETE, testUrl3);
        service.signRequest(accessToken, requestFeed);
        try (Response responseFeed = service.execute(requestFeed)) {
            logger.info("--------开始删帖--------------");
            logger.info("responseFeed.getCode()：" + responseFeed.getCode());
            logger.info("responseFeed.getBody()：" + responseFeed.getBody());
            logger.info("--------结束--------------");
            Map<String, Object> result = new HashMap<>();
            result.put("code", responseFeed.getCode());
            result.put("content", responseFeed.getBody());
            apiResponse.setData(FastJsonUtil.toJSONString(result));
        }
        return apiResponse;
    }

    @RequestMapping(value = "getPostTotal")
    public OpenApiResponse getPostTotal(@RequestBody OpenApiRequest apiRequest, OpenApiResponse apiResponse) {
        logger.info(FastJsonUtil.toJSONString(apiRequest));
        RequestFBDto requestData = getRequestData(apiRequest, RequestFBDto.class);
        String testUrl = "https://graph.facebook.com/v5.0/me/posts?fields=id";
        String testUrl2 = testUrl.replace("me", requestData.getPageId());
        Map<String, String> param = new HashMap<>(0);
        param.put("access_token", requestData.getAccess_token());
        logger.info("accessToken：" + requestData.getAccess_token());
        String fbResult = HttpClientUtil.doGet(testUrl2, param);
        logger.info("fbResult："+fbResult);
        apiResponse.setData(fbResult);
        return apiResponse;
    }

    @RequestMapping(value = "getLikes")
    public OpenApiResponse getLikes(@RequestBody OpenApiRequest apiRequest, OpenApiResponse apiResponse) {
//        logger.info(FastJsonUtil.toJSONString(apiRequest));
//        RequestFBDto requestData = getRequestData(apiRequest, RequestFBDto.class);
//        String testUrl = "https://graph.facebook.com/v5.0/me/likes";
//        String testUrl2 = testUrl.replace("me", requestData.getPageId());
//        Map<String, String> param = new HashMap<>(0);
//        param.put("access_token", requestData.getAccess_token());
//        logger.info("accessToken：" + requestData.getAccess_token());
//        String likeResult = HttpClientUtil.doGet(testUrl2, param);
//        logger.info("likeResult："+likeResult);
//        apiResponse.setData(likeResult);
        return apiResponse;
    }

    @RequestMapping(value = "callBackInfo")
    public OpenApiResponse callBackInfo(@RequestBody OpenApiRequest apiRequest, OpenApiResponse apiResponse) throws InterruptedException, ExecutionException, IOException {
        logger.info(FastJsonUtil.toJSONString(apiRequest));
        logger.info("----------开始授权------------");
        RequestFBDto requestData = getRequestData(apiRequest, RequestFBDto.class);
        OAuth20Service service = openApiFBService.initFaceBookOAuth(requestData);
        final OAuth2AccessToken accessToken = service.getAccessToken(requestData.getCode());
        String getUserInfo = "https://graph.facebook.com/v5.0/me";
        final OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, getUserInfo);
        oAuthRequest.addParameter("fields", "id,name,email");
        service.signRequest(accessToken, oAuthRequest);
        try (Response response = service.execute(oAuthRequest)) {
            logger.info("response.getCode()：" + response.getCode());
            logger.info("response.getBody()：" + response.getBody());
            Map<String, Object> result = new HashMap<>();
            result.put("code", response.getCode());
            result.put("content", response.getBody());
            result.put("token", accessToken.getAccessToken());
            result.put("expiresIn", accessToken.getExpiresIn());
            apiResponse.setData(FastJsonUtil.toJSONString(result));
            logger.info("--------授权结束--------------");
        }
        return apiResponse;
    }

    /**
     * @param apiRequest
     * @param apiResponse
     * @return
     */
    @RequestMapping(value = "multiUpload")
    public OpenApiResponse multiUpload(@RequestBody OpenApiRequest apiRequest, OpenApiResponse apiResponse) {
        logger.info("========= multiUpload =========");
        logger.info(FastJsonUtil.toJSONString(apiRequest));
        RequestFBDto requestData = getRequestData(apiRequest, RequestFBDto.class);
        Map<String, Object> result = new HashMap<>();
        String testUrl3 = "https://graph.facebook.com/v5.0/me/photos";
        OAuth20Service service = openApiFBService.initFaceBookOAuth(requestData);
        final OAuthRequest requestFeed = new OAuthRequest(Verb.POST, testUrl3);
        String accessToken = openApiFBService.getPageAccessToken(requestData.getPageId(), requestData.getAccess_token());
        String appsecretProof1 = Hashing.hmacSha256(facebookClientPwd.getBytes()).hashString(requestData.getAccess_token(), StandardCharsets.UTF_8).toString();
        logger.info("appsecretProof1：" + appsecretProof1);
        requestFeed.addParameter("appsecret_proof", appsecretProof1);
        logger.info("accessToken：" + accessToken);

        if (StringUtil.notEmpty(requestData.getImgPath())) {
            logger.info("admpFacebookPost.getImgPath()：" + requestData.getImgPath());
            requestFeed.addParameter("url", requestData.getImgPath());
//            requestFeed.addParameter("caption", "photo");
            requestFeed.addParameter("published", "false");
        }

        service.signRequest(accessToken, requestFeed);
        try (Response responseFeed = service.execute(requestFeed)) {
            logger.info("--------multiUpload 结束--------------");
            logger.info("responseFeed.getCode()：" + responseFeed.getCode());
            logger.info("responseFeed.getBody()：" + responseFeed.getBody());
            result.put("code", responseFeed.getCode());
            result.put("content", responseFeed.getBody());
            apiResponse.setData(FastJsonUtil.toJSONString(result));
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

    @RequestMapping(value = "requestPageUrl")
    public OpenApiResponse requestPageUrl(@RequestBody OpenApiRequest apiRequest, OpenApiResponse apiResponse) {
        logger.info(FastJsonUtil.toJSONString(apiRequest));
        RequestFBDto requestData = getRequestData(apiRequest, RequestFBDto.class);
        logger.info("requestData.getPageUrl():{}", requestData.getPageUrl());
        String requestPageUrlRes = "";
        if (StringUtil.notEmpty(requestData.getPageUrl())) {
            requestPageUrlRes = HttpClientUtil.doGet(requestData.getPageUrl());
        }
        logger.info("requestPageUrlRes：{}", requestPageUrlRes);
        apiResponse.setData(requestPageUrlRes);
        return apiResponse;
    }

    @RequestMapping(value = "getPagePostUrl")
    public OpenApiResponse getPagePostUrl(@RequestBody OpenApiRequest apiRequest, OpenApiResponse apiResponse) {
        logger.info(FastJsonUtil.toJSONString(apiRequest));
        RequestFBDto requestData = getRequestData(apiRequest, RequestFBDto.class);
        String testUrl = "https://graph.facebook.com/v10.0/pageId/feed";
        String testUrl2 = testUrl.replace("pageId", requestData.getPageId());
        logger.info("testUrl2:{}", testUrl2);
        Map<String, String> param = new HashMap<>(0);
        String pageAccessToken = openApiFBService.getPageAccessToken(requestData.getPageId(), requestData.getAccess_token());
        logger.info("pageAccessToken:{}", pageAccessToken);
        param.put("access_token", pageAccessToken);
        String requestPageUrlRes = HttpClientUtil.doGet(testUrl2, param);
        logger.info("requestPageUrlRes：{}", requestPageUrlRes);
        apiResponse.setData(requestPageUrlRes);
        return apiResponse;
    }

    @RequestMapping(value = "getPagePostDetail")
    public OpenApiResponse getPagePostDetail(@RequestBody OpenApiRequest apiRequest, OpenApiResponse apiResponse) {
        logger.info(FastJsonUtil.toJSONString(apiRequest));
        RequestFBDto requestData = getRequestData(apiRequest, RequestFBDto.class);
        String testUrl = "https://graph.facebook.com/v10.0/postId?fields=likes{name,pic,link,profile_type},comments{like_count,created_time,user_likes,message,from}";
        String testUrl2 = testUrl.replace("postId", requestData.getPostId());
        logger.info("testUrl2:{}", testUrl2);
        Map<String, String> param = new HashMap<>(0);
        String pageAccessToken = openApiFBService.getPageAccessToken(requestData.getPageId(), requestData.getAccess_token());
        logger.info("pageAccessToken:{}", pageAccessToken);
        param.put("access_token", pageAccessToken);
        String requestPageUrlRes = HttpClientUtil.doGet(testUrl2, param);
        logger.info("requestPageUrlRes：{}", requestPageUrlRes);
        apiResponse.setData(requestPageUrlRes);
        return apiResponse;
    }
}
