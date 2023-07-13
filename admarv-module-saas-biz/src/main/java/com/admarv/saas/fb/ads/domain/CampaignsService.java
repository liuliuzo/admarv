package com.admarv.saas.fb.ads.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.mapper.CampaignsInsightsMapper;
import com.admarv.saas.model.CampaignsInsights;
import com.restfb.FacebookClient;
import com.restfb.types.ads.AdAccount;
import com.restfb.types.ads.Campaign;

/**
 * 广告系列服务
 * 
 * @author liuliu
 *
 */
@Service
public class CampaignsService {

    private static final Logger log = LoggerFactory.getLogger(CampaignsService.class);
    
    @Autowired
    private FacebookClientService facebookClientService;
    
    @Autowired
    private CampaignsInsightsMapper campaignsInsightsMapper;
    
    public void syncCampaignsInsights(String user) {
        log.info("getCampaignsInsights start");
        FacebookClient facebookClient = facebookClientService.getClientByUser(user);
        // 获取当前用户的广告账户列表
        List<AdAccount> adAccounts = facebookClient.fetchObject("me/adaccounts", List.class);
        for (AdAccount adAccount : adAccounts) {
            String id = adAccount.getId();
            log.info("Account ID: {}", id);
            log.info("Account Name: {}", adAccount.getName());
            // 获取广告账户的广告系列列表
            List<Campaign> campaigns = facebookClient.fetchObject(id + "/campaigns", List.class);
            for (Campaign campaign : campaigns) {
                String campaignId = campaign.getId();
                String name = campaign.getName();
                log.info("Campaign ID: {}", campaignId);
                log.info("Campaign Name: {}", name);
                // 获取广告系列的洞察数据
                CampaignsInsights insights = facebookClient.fetchObject(campaignId + "/insights", CampaignsInsights.class);
                log.info("insights: {}", insights);
                campaignsInsightsMapper.insert(insights);
            }
        }
    }
}
