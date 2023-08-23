package com.admarv.saas.sync.adaccount.ui;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.model.campaigns.Campaigns;
import com.admarv.saas.fb.model.campaigns.Data;
import com.admarv.saas.mapper.AdsetInfoMapper;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.model.AdsetInfo;
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
public class FBSyncAdSetController {

	private static final Logger log = LoggerFactory.getLogger(FBSyncAdSetController.class);
	
	@Autowired
    private FacebookClientService facebookClientService;
	
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;
    
    @Autowired
    private AdsetInfoMapper adsetInfoMapper;
	
    /**
     * TODO:待优化，使用WEBHOOK替换
     * 
     * @param userId  
     * @return
     */
	@RequestMapping(value = "/admarv/refreshAdset", method = RequestMethod.GET)
	public String refreshAds(String userId) {
		log.info("/admarv/refreshAdset userId:{}", userId);
		SysUserFbBind selectEntity = new SysUserFbBind();
		selectEntity.setUserId(userId);
		SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
		String adAccountId = sysUserFbBind.getAdAccountId();
		FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
		JsonMapper jsonMapper = facebookClient.getJsonMapper();
		String fields = "id,daily_budget,name,status,campaign_id";  
		JsonObject jsonObject = facebookClient.fetchObject(adAccountId + "/adsets", JsonObject.class, 
				Parameter.with("fields", fields));
		String srtjson = jsonMapper.toJson(jsonObject);
		Campaigns campaigns = JacksonUtils.fromJson(srtjson, Campaigns.class);
		List<Data> dataList = campaigns.getData();
		for (Data data : dataList) {
			log.info("data:{}", data);
			String id = data.getId();
			String name = data.getName();
			String status = data.getStatus();
			String dailyBudget = data.getDailyBudget();		
			String campaignId = data.getCampaignId();
			
			AdsetInfo insert = new AdsetInfo();
			insert.setAdsetId(id);
			insert.setDailyBudget(dailyBudget);
			insert.setName(name);
			insert.setStatus(status);
			insert.setCampaignId(campaignId);
			insert.setAdAccountId(adAccountId);
			int row = adsetInfoMapper.insert(insert);
			String insertResult = String.format("insert CampaignsInfo row %d", row);
			log.info("insertResult:{}", insertResult);
		}
		return "SUCCESS refreshAdset";
	}
}
