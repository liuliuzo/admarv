package com.admarv.saas.fb.ads.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.admarv.saas.fb.ads.domain.CampaignsService;
import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.UserService;
import com.admarv.saas.model.SysUser;

/**
 * 
 * @author liuliu
 *
 */
@Component
public class CampaignsInsightsTask {
    
    private static final Logger log = LoggerFactory.getLogger(CampaignsInsightsTask.class);
    
    @Autowired
    private FacebookClientService facebookClientService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CampaignsService campaignsService;
    
    public void doCampaignsInsightsTask() {
        log.info("CampaignsInsightsTask start");
        if (!facebookClientService.checkClients()) {
            log.warn("not init oauth clients task not execute");
            return;
        }
        // 获取SAAS平台中的所有用户
        List<SysUser> listSysUser = userService.getUserList();
        log.info("listSysUser :{}", listSysUser);
        /**
         * 同步所有用户的广告系列成效数据
         */
        for (SysUser user : listSysUser) {
            log.info("user:{}", user);
            String userName = user.getUserName();
            campaignsService.syncCampaignsInsights(userName);
        }
        log.info("CampaignsInsightsTask end");
    }
}
