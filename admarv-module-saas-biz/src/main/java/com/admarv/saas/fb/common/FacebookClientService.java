package com.admarv.saas.fb.common;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
public class FacebookClientService {
    
    private static final Logger log = LoggerFactory.getLogger(FacebookClientService.class);
    
    // user and FacebookClient
    private Map<String, FacebookClient> clientMap = Maps.newHashMap();
    
    public FacebookClient getClientByUser(String user) {
        return clientMap.get(user);
    }
    
    public void setClientIfAbsent(String user, FacebookClient facebookClient) {
        clientMap.putIfAbsent(user, facebookClient);
    }
    
    public void setClient(String user, FacebookClient facebookClient) {
        clientMap.put(user, facebookClient);
    }
    
    public boolean checkClients() {
        if (clientMap.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * TOTO: 待优化
     * 
     * @param facebookClient
     * @param pageId
     * @return
     */
    public FacebookClient getPageAccessClient(FacebookClient facebookClient, String pageId) {
        // 获取登录用户的信息
        User me = facebookClient.fetchObject("me", User.class);
        Connection<Page> accounts = facebookClient.fetchConnection(me.getId() + "/accounts", Page.class);
        // 遍历所有页面和应用程序
        for (List<Page> pageList : accounts) {
            for (Page page : pageList) {
                log.info("page:{}", page);
                if (pageId.equals(page.getId())) {
                    String pageAccessToken = page.getAccessToken();
                    // 获取 page access token的FacebookClient TODO: 待优化
                    return new DefaultFacebookClient(pageAccessToken, Version.VERSION_17_0);
                }
            }
        }
        return facebookClient;
    }
}
