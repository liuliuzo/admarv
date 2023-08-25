package com.admarv.saas.fb.common;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.mapper.SysUserMapper;
import com.admarv.saas.mapper.UserAuthsMapper;
import com.admarv.saas.model.SysUser;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.model.UserAuths;
import com.google.common.collect.Maps;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.Page;
import com.restfb.types.User;

/**
 * 
 * @author liuliu
 *
 */
@Service
public class FacebookClientService implements InitializingBean {
    
    private static final Logger log = LoggerFactory.getLogger(FacebookClientService.class);
    
    // user and FacebookClient
    private Map<String, FacebookClient> clientMap = Maps.newHashMap();
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private UserAuthsMapper userAuthsMapper;
    
	@Autowired
	private SysUserFbBindMapper sysUserFbBindMapper;
    
    public FacebookClient getClientByUserId(String userId) {
        return clientMap.get(userId);
    }
    
    public void setClient(String userId, FacebookClient facebookClient) {
        clientMap.put(userId, facebookClient);
    }
    
    public boolean checkClients() {
        if (clientMap.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * TOTO: 待优化,accounts信息持久化到本地
     * 
     * @param facebookClient
     * @param pageId
     * @return
     */
    public FacebookClient getPageAccessClient(FacebookClient facebookClient, String userId) {
		SysUserFbBind selectEntity = new SysUserFbBind();
		selectEntity.setUserId(userId);
		SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
		String pageId = sysUserFbBind.getPageId();
        // 获取登录用户的信息
        User me = facebookClient.fetchObject("me", User.class);
        Connection<Page> accounts = facebookClient.fetchConnection(me.getId() + "/accounts", Page.class);
        // 遍历所有页面和应用程序
        for (List<Page> pageList : accounts) {
            for (Page page : pageList) {
                log.info("page:{}", page);
                if (pageId.equals(page.getId())) {
                    String pageAccessToken = page.getAccessToken();
                    log.info("pageAccessToken:{}", pageAccessToken);
                    // 获取 page access token的FacebookClient TODO: 待优化
                    return new DefaultFacebookClient(pageAccessToken, Version.VERSION_17_0);
                }
            }
        }
        return facebookClient;
    }
    
    /**
     * 启动的时自动加载已经Oauth过的用户FacebookClient
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<SysUser> listSysUser = sysUserMapper.selectAll();
        for (SysUser sysUser : listSysUser) {
            log.info("sysUser:{}", sysUser);
            String userName = sysUser.getUserName();
            String userId = sysUser.getUserId();
            UserAuths selectUserAuths = new UserAuths();
            selectUserAuths.setUserId(userId);
            selectUserAuths.setPltfrm("FB");
            log.info("selectUserAuths:{}", selectUserAuths);
            UserAuths userAuths = userAuthsMapper.selectOneByEntity(selectUserAuths);
            if (userAuths != null) {
                String accessToken = userAuths.getToken();
                FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_17_0);
                log.info("init userId:{}, userName:{}, facebookClient:{}", userId, userName, facebookClient);
                clientMap.put(userId, facebookClient);
            }
        }
        log.info("clientMap size:{}",  clientMap.size());
    }
}
