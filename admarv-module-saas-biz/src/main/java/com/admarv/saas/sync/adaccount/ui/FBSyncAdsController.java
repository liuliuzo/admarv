package com.admarv.saas.sync.adaccount.ui;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.model.ads.Ads;
import com.admarv.saas.fb.model.ads.Data;
import com.admarv.saas.mapper.AdInfoMapper;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.model.AdInfo;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.utils.JacksonUtils;
import com.restfb.FacebookClient;
import com.restfb.JsonMapper;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;

/**
 * 
 * @author liuliu
 *
 */
@RestController
public class FBSyncAdsController {

	private static final Logger log = LoggerFactory.getLogger(FBSyncAdsController.class);
	
	@Autowired
    private FacebookClientService facebookClientService;
	
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;
    
    @Autowired
    private AdInfoMapper adInfoMapper;
    
    /**
     * TODO:待优化，使用WEBHOOK替换
     * 
     * @param userId  
     * @return
     */
	@RequestMapping(value = "/admarv/refreshAds", method = RequestMethod.GET)
	public String refreshAds(String userId) {
		log.info("/admarv/refreshAds userId:{}", userId);
		SysUserFbBind selectEntity = new SysUserFbBind();
		selectEntity.setUserId(userId);
		SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
		String adAccountId = sysUserFbBind.getAdAccountId();
		FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
		JsonMapper jsonMapper = facebookClient.getJsonMapper();
		String fields = "id,daily_budget,name,status,campaign_id,adset_id";
		JsonObject jsonObject = facebookClient.fetchObject(adAccountId + "/ads", JsonObject.class, Parameter.with("fields", fields));
		String srtjson = jsonMapper.toJson(jsonObject);
		Ads ads = JacksonUtils.fromJson(srtjson, Ads.class);
		List<Data> dataList = ads.getData();
		for (Data data : dataList) {
			log.info("data:{}", data);
			String id = data.getId();
			String name = data.getName();
			String status = data.getStatus();
			String dailyBudget = data.getDailyBudget();
			String campaignId = data.getCampaignId();
			String adSetId = data.getAdsetId();
			AdInfo selectAdInfo = new AdInfo();
			selectAdInfo.setAdId(id);
			AdInfo adInfoUpdate = adInfoMapper.selectOneByEntity(selectAdInfo);
			if (adInfoUpdate != null) {
				adInfoUpdate.setName(name);
				adInfoUpdate.setAdId(id);
				adInfoUpdate.setStatus(status);
				adInfoUpdate.setDailyBudget(dailyBudget);
				adInfoUpdate.setCampaignId(campaignId);
				adInfoUpdate.setAdsetId(adSetId);
				adInfoUpdate.setAdAccountId(adAccountId);
				int row = adInfoMapper.updateByPrimaryKey(adInfoUpdate);
				String updateResult = String.format("insert adsInfo row %d", row);
				log.info("updateResult:{}", updateResult);
			} else {
				AdInfo insert = new AdInfo();
				insert.setName(name);
				insert.setAdId(id);
				insert.setStatus(status);
				insert.setDailyBudget(dailyBudget);
				insert.setCampaignId(campaignId);
				insert.setAdsetId(adSetId);
				insert.setAdAccountId(adAccountId);
				int row = adInfoMapper.insert(insert);
				String insertResult = String.format("insert adsInfo row %d", row);
				log.info("insertResult:{}", insertResult);
			}
		}
		return "SUCCESS refreshAds";
	}
}
