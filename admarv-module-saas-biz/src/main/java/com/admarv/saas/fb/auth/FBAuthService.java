package com.admarv.saas.fb.auth;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.mapper.SysUserMapper;
import com.admarv.saas.model.SysUser;
import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.common.collect.Maps;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;

/**
 * 
 * @author liuliu
 *
 */
@Service
public class FBAuthService implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(FBAuthService.class);
    
    private static final String FACEBOOK_API_VERSION = "17.0";
    
    @Value("${admarv.facebook.url.callback}")
    private String facebookUrlCallback;
    
    @Value("${admarv.facebook.clientId}")
    private String clientId;
    
    @Value("${admarv.facebook.client.secret}")
    private String clientSecret;
    
    @Value("${admarv.facebook.scope}")
    private String facebookScope;
    
    @Autowired
    private FacebookClientService facebookClientService;
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    // KEY secretState, Value OAuth20Service
    private Map<String, OAuth20Service> stateOAuthMaps = Maps.newHashMap();
    
    public Map<String, OAuth20Service> getCacheMap() {
        return stateOAuthMaps;
    }
    
    public void setCacheMap(Map<String, OAuth20Service> cacheMap) {
        this.stateOAuthMaps = cacheMap;
    }
    
    public String genAuthorizationUrl(String user) {
        ScopeBuilder build = new ScopeBuilder();
        String[] scopes = facebookScope.split(",");
        for (String scope : scopes) {
            FacebookPermissions[] permissions = FacebookPermissions.values();
            for (FacebookPermissions permission : permissions) {
                String scopePermission = permission.getPermissionString();
                if (scopePermission.equals(scope)) {
                    build.addPermission(permission);
                }
            }
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String keyState = user + "_" + uuid;
        final OAuth20Service service = new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .callback(facebookUrlCallback)
                .defaultScope(build.toString())
                .build(FacebookApi.customVersion(FACEBOOK_API_VERSION));
        log.info("=== FaceBook OAuth Workflow ===");
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
                // 生成对应的facebook client
                log.info("init access token client");
                FacebookClient facebookClient = new DefaultFacebookClient(token, Version.VERSION_17_0);
                String user = state.split("_")[0];
                log.info("init user:{}, facebookClient:{}", user, facebookClient);
                facebookClientService.setClient(user, facebookClient);
                //持久化user access token
                SysUser selectEntity = new SysUser();
                selectEntity.setUserName(user);
                SysUser updateSysUser = sysUserMapper.selectOneByEntity(selectEntity);
                updateSysUser.setFbToken(token);
                int row = sysUserMapper.updateByPrimaryKey(updateSysUser);
                log.info("success update row:{}", row);
                return token;
            } catch (Exception e) {
                log.error("oauth callback error", e);
                return e.getMessage();
            }
        }
    }
    
    /**
     * 启动的时自动加载已经Oauth过的用户FacebookClient
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<SysUser> listSysUser = sysUserMapper.selectAll();
        for (SysUser sysUser : listSysUser) {
            String userName = sysUser.getUserName();
            String accessToken = sysUser.getFbToken();
            if (StringUtils.isNotBlank(accessToken)) {
                FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_17_0);
                log.info("init user:{}, facebookClient:{}", userName, facebookClient);
                facebookClientService.setClient(userName, facebookClient);
            }
        }
    }
}
