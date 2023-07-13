package com.admarv.saas.fb.ads.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admarv.saas.fb.ads.model.campaigns.Data;
import com.admarv.saas.fb.ads.model.campaigns.FBCampaignsInsights;
import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.mapper.CampaignsInsightsMapper;
import com.admarv.saas.model.CampaignsInsights;
import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.types.User;
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
        // 获取登录用户的信息
        User me = facebookClient.fetchObject("me", User.class);
        // 获取当前用户的广告账户列表
        Connection<AdAccount> connAdAccount = facebookClient.fetchConnection(me.getId() + "/adaccounts", AdAccount.class);
        List<AdAccount> adAccounts = connAdAccount.getData();
        for (AdAccount adAccount : adAccounts) {
            String id = adAccount.getId();
            log.info("Account ID: {}", id);
            log.info("Account Name: {}", adAccount.getName());
            // 获取广告账户的广告系列列表
            Connection<Campaign> connCampaigns = facebookClient.fetchConnection(id + "/campaigns", Campaign.class);
            List<Campaign> campaigns = connCampaigns.getData();
            for (Campaign campaign : campaigns) {
                String campaignId = campaign.getId();
                String name = campaign.getName();
                log.info("Campaign ID: {}", campaignId);
                log.info("Campaign Name: {}", name);
                // 获取广告系列的洞察数据
                FBCampaignsInsights fbInsights = facebookClient.fetchObject(campaignId + "/insights", FBCampaignsInsights.class);
                log.info("fbInsights: {}", fbInsights);
                List<Data> listData= fbInsights.getData();
                for (Data data : listData) {
                	CampaignsInsights insights = new CampaignsInsights();
                	insights.setAccountCurrency(data.getAccountCurrency());
                	insights.setAccountId(data.getAccountId());
                	insights.setAccountName(data.getAccountName());
                	insights.setActions(data.getActions());
                	insights.setActionValues(data.getActionValues());
                	insights.setActivityRecency(data.getActivityRecency());
                	insights.setAdClickActions(data.getAdClickActions());
                	insights.setAdFormatAsset(data.getAdFormatAsset());
                	insights.setAdId(data.getAdId());
                	insights.setCtr(data.getCtr());
                	insights.setCampaignId(data.getCampaignId());
                    campaignsInsightsMapper.insert(insights);   
				}
            }
        }
    }
}