package com.admarv.saas.tk.auth.domain;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.admarv.saas.mapper.SysUserMapper;
import com.admarv.saas.mapper.UserAuthsMapper;
import com.admarv.saas.model.SysUser;
import com.admarv.saas.model.UserAuths;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.common.collect.Maps;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;

/**
 * 
 * @author liuliu
 *
 */
@Service
public class TikTokAuthService {

    private static final Logger log = LoggerFactory.getLogger(TikTokAuthService.class);

    @Value("${admarv.tiktok.client.key}")
    private String clientId;
    
    @Value("${admarv.tiktok.client.secret}")
    private String clientSecret;
    
    @Value("${admarv.tiktok.scope}")
    private String tiktokScope;
    
    @Value("${admarv.tiktok.url.callback}")
    private String tiktokUrlCallback;
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private UserAuthsMapper userAuthsMapper;
    
    // KEY secretState, Value OAuth20Service
    private Map<String, OAuth20Service> stateOAuthMaps = Maps.newHashMap();
    
    public Map<String, OAuth20Service> getCacheMap() {
        return stateOAuthMaps;
    }
    
    public void setCacheMap(Map<String, OAuth20Service> cacheMap) {
        this.stateOAuthMaps = cacheMap;
    }
    
    public String genAuthorizationUrl(String userId) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String keyState = userId + "_" + uuid;
        final OAuth20Service service = new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .callback(tiktokUrlCallback)
                .defaultScope(tiktokScope)
                .build(TikTokApi.customVersion("2"));
        log.info("=== TikTok OAuth Workflow ===");
        final String authorizationUrl = service.getAuthorizationUrl(keyState);
        log.info("Got the Authorization URL! :{}", authorizationUrl);
        stateOAuthMaps.put(keyState, service);
        return authorizationUrl;
    }
    
    public String callBack(String code, String state, String error, String errorReason, String errorDescription) {
        OAuth20Service oAuth20Service = stateOAuthMaps.remove(state);
        log.info("oAuth20Service:{}", oAuth20Service);
        //如果是失败的callback
        if(StringUtils.isNotBlank(error)) {
            return String.format("error %s,error_reason %s, error_description  %s", error, errorReason, errorDescription);
        }
        //如果是成功的callback
        else {
            OAuth2AccessToken accessToken;
            try {
                log.info("Trading the Authorization Code for an Access Token...");
                accessToken = oAuth20Service.getAccessToken(code);
                String token = accessToken.getAccessToken();
                Integer expires = accessToken.getExpiresIn();
                String refreshToken = accessToken.getRefreshToken();
                String scope = accessToken.getScope();
                log.info("access Token :{}", token);
                log.info("expires:{}", expires);
                log.info("refreshToken:{}", refreshToken);
                log.info("scope:{}", scope);
                log.info("init access token client");
                String userId = state.split("_")[0];
                SysUser selectEntity = new SysUser();;
                selectEntity.setUserId(userId);
                SysUser updateSysUser = sysUserMapper.selectOneByEntity(selectEntity);
                updateSysUser.setFbToken(token);
                int row0 = sysUserMapper.updateByPrimaryKey(updateSysUser);
                log.info("success update SysUser row:{}", row0);
                UserAuths selectUserAuths = new UserAuths();
                selectUserAuths.setUserId(userId);
                UserAuths userAuthsUpdate = userAuthsMapper.selectOneByEntity(selectUserAuths);
                userAuthsUpdate.setToken(token);
                userAuthsUpdate.setPltfrm("FB");
                int row1 = userAuthsMapper.updateByPrimaryKey(userAuthsUpdate);
                log.info("success update SysUser row:{}", row1);
                return token;
            } catch (Exception e) {
                log.error("oauth callback error", e);
                return e.getMessage();
            }
        }
    }
}
