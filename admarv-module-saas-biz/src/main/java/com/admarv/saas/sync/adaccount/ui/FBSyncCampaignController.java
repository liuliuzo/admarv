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
import com.admarv.saas.mapper.CampaignInfoMapper;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.model.CampaignInfo;
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
public class FBSyncCampaignController {

	private static final Logger log = LoggerFactory.getLogger(FBSyncCampaignController.class);
	
	@Autowired
    private FacebookClientService facebookClientService;
	
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;
    
    @Autowired
    private CampaignInfoMapper campaignInfoMapper;
	
    /**
     * TODO:待优化，使用WEBHOOK替换
     * 
     * @param userId  
     * @return
     */
	@RequestMapping(value = "/admarv/refreshCampaign", method = RequestMethod.GET)
	public String refreshAds(String userId) {
		log.info("/admarv/refreshCampaign userId:{}", userId);
		SysUserFbBind selectEntity = new SysUserFbBind();
		selectEntity.setUserId(userId);
		SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
		String adAccountId = sysUserFbBind.getAdAccountId();
		FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
		JsonMapper jsonMapper = facebookClient.getJsonMapper();
		String fields = "id,daily_budget,status,name";  
		JsonObject jsonObject = facebookClient.fetchObject(adAccountId + "/campaigns", JsonObject.class, 
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
			CampaignInfo insert = new CampaignInfo();
			insert.setCampaignId(id);
			insert.setName(name);
			insert.setStatus(status);
			insert.setDailyBudget(dailyBudget);
			insert.setAdAccountId(adAccountId);
			int row = campaignInfoMapper.insert(insert);
			String insertResult = String.format("insert CampaignInfo row %d", row);
			log.info("insertResult:{}", insertResult);
		}
		return "SUCCESS refreshCampaign";
	}
}
