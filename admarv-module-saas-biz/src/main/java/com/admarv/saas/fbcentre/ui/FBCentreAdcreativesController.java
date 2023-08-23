package com.admarv.saas.fbcentre.ui;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.PageResponse;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.model.adcreatives.Adcreatives;
import com.admarv.saas.fb.model.insights.Paging;
import com.admarv.saas.fbcentre.dto.req.ReqGetAdsAdcreatives;
import com.admarv.saas.fbcentre.dto.req.ReqGetAllAdsAdcreatives;
import com.admarv.saas.mapper.AdInfoMapper;
import com.admarv.saas.mapper.AdsetInfoMapper;
import com.admarv.saas.mapper.CampaignInfoMapper;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.model.AdInfo;
import com.admarv.saas.model.AdsetInfo;
import com.admarv.saas.model.CampaignInfo;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.utils.JacksonUtils;
import com.alibaba.excel.util.StringUtils;
import com.google.api.client.util.Lists;
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
public class FBCentreAdcreativesController {

	private static final Logger log = LoggerFactory.getLogger(FBCentreAdcreativesController.class);
	
    @Autowired
    private FacebookClientService facebookClientService;
    
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;
    
    @Autowired
    private AdInfoMapper adInfoMapper;
    
    @Autowired
    private CampaignInfoMapper campaignInfoMapper;
    
    @Autowired
    private AdsetInfoMapper adsetInfoMapper;
	
    @Deprecated
	@RequestMapping(value = "/admarv/adcreatives", method = RequestMethod.GET)
	public String adcreatives(String userId) {
		log.info("/admarv/adcreatives userId:{}", userId);
		FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
		if (facebookClient == null) {
			Response response = new Response();
			response.setCode("601");
			response.setMessage("此用户未授权");
			response.setSuccess(false);
			String srtResponse = JacksonUtils.toJson(response);
			log.info(srtResponse);
			return srtResponse;
		}
		
		// 获取saas平台绑定的用户广告账户
		SysUserFbBind selectEntity = new SysUserFbBind();
		selectEntity.setUserId(userId);
		SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
		String adAccountId = sysUserFbBind.getAdAccountId();
		JsonMapper jsonMapper = facebookClient.getJsonMapper();
		String fields = "id,account_id,body,asset_feed_spec,authorization_category,categorization_criteria,actor_id,adlabels,applink_treatment,branded_content,branded_content_sponsor_page_id,bundle_folder_id,category_media_source,degrees_of_freedom_spec,destination_set_id,dynamic_ad_voice,enable_direct_install,enable_launch_instant_app,facebook_branded_content,image_crops,image_file,image_hash,image_url,instagram_actor_id,instagram_branded_content,instagram_permalink_url,instagram_user_id,interactive_components_spec,link_og_id,link_url,messenger_sponsored_message,name,object_id,object_story_id,object_story_spec,object_type,object_url,place_page_set_id,platform_customizations,playable_asset_id,portrait_customizations,product_set_id,recommender_settings,source_instagram_media_id,template_url,template_url_spec,thumbnail_url,title,url_tags";
		JsonObject jsonObjectAdcreatives = facebookClient.fetchObject(adAccountId + "/adcreatives", JsonObject.class, Parameter.with("fields", fields));
		String strAdcreatives = jsonMapper.toJson(jsonObjectAdcreatives);
		Adcreatives adcreatives = JacksonUtils.fromJson(strAdcreatives, Adcreatives.class);
		log.info("adcreatives:{}", adcreatives); 
		Paging paging = adcreatives.getPaging();

		PageResponse response = new PageResponse();
		response.setResult(adcreatives);
		response.setCode("200");
		response.setSuccess(true);
		response.setPaging(paging);
		
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}
	
	@RequestMapping(value = "/admarv/getAdsAdcreatives", method = RequestMethod.POST)
	public String getAdsAdcreatives(@RequestBody ReqGetAdsAdcreatives reqGetAdsAdcreatives) {
		log.info("/admarv/adcreatives reqGetAdsAdcreatives:{}", reqGetAdsAdcreatives);
		String userId = reqGetAdsAdcreatives.getUserId();
		String adsId = reqGetAdsAdcreatives.getAdsId();
		FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
		if (facebookClient == null) {
			Response response = new Response();
			response.setCode("601");
			response.setMessage("此用户未授权");
			response.setSuccess(false);
			String srtResponse = JacksonUtils.toJson(response);
			log.info(srtResponse);
			return srtResponse;
		}
		JsonMapper jsonMapper = facebookClient.getJsonMapper();
		String fields = "id,account_id,body,asset_feed_spec,authorization_category,categorization_criteria,actor_id,adlabels,applink_treatment,branded_content,branded_content_sponsor_page_id,bundle_folder_id,category_media_source,degrees_of_freedom_spec,destination_set_id,dynamic_ad_voice,enable_direct_install,enable_launch_instant_app,facebook_branded_content,image_crops,image_file,image_hash,image_url,instagram_actor_id,instagram_branded_content,instagram_permalink_url,instagram_user_id,interactive_components_spec,link_og_id,link_url,messenger_sponsored_message,name,object_id,object_story_id,object_story_spec,object_type,object_url,place_page_set_id,platform_customizations,playable_asset_id,portrait_customizations,product_set_id,recommender_settings,source_instagram_media_id,template_url,template_url_spec,thumbnail_url,title,url_tags";
		JsonObject jsonObjectAdcreatives = facebookClient.fetchObject(adsId + "/adcreatives", JsonObject.class, Parameter.with("fields", fields));
		String strAdcreatives = jsonMapper.toJson(jsonObjectAdcreatives);
		Adcreatives adcreatives = JacksonUtils.fromJson(strAdcreatives, Adcreatives.class);
		log.info("adcreatives:{}", adcreatives); 
		Paging paging = adcreatives.getPaging();
		
		PageResponse response = new PageResponse();
		response.setResult(adcreatives);
		response.setCode("200");
		response.setSuccess(true);
		response.setPaging(paging);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}
	
	
	@RequestMapping(value = "/admarv/getAllAdsAdcreatives", method = RequestMethod.POST)
	public String getAllAdsAdcreatives(@RequestBody ReqGetAllAdsAdcreatives reqGetAllAdsAdcreatives) {
		log.info("/admarv/getAllAdsAdcreatives reqGetAllAdsAdcreatives:{}", reqGetAllAdsAdcreatives);
		List<Adcreatives> listAdcreatives = Lists.newArrayList();
		String userId = reqGetAllAdsAdcreatives.getUserId();
		String adsId = reqGetAllAdsAdcreatives.getAdsId();
		FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
		if (facebookClient == null) {
			Response response = new Response();
			response.setCode("601");
			response.setMessage("此用户未授权");
			response.setSuccess(false);
			String srtResponse = JacksonUtils.toJson(response);
			log.info(srtResponse);
			return srtResponse;
		}
		//用筛选条件筛选
		if (StringUtils.isNotBlank(adsId)) {
			JsonMapper jsonMapper = facebookClient.getJsonMapper();
			String fields = "id,account_id,body,asset_feed_spec,authorization_category,categorization_criteria,actor_id,adlabels,applink_treatment,branded_content,branded_content_sponsor_page_id,bundle_folder_id,category_media_source,degrees_of_freedom_spec,destination_set_id,dynamic_ad_voice,enable_direct_install,enable_launch_instant_app,facebook_branded_content,image_crops,image_file,image_hash,image_url,instagram_actor_id,instagram_branded_content,instagram_permalink_url,instagram_user_id,interactive_components_spec,link_og_id,link_url,messenger_sponsored_message,name,object_id,object_story_id,object_story_spec,object_type,object_url,place_page_set_id,platform_customizations,playable_asset_id,portrait_customizations,product_set_id,recommender_settings,source_instagram_media_id,template_url,template_url_spec,thumbnail_url,title,url_tags";
			JsonObject jsonObjectAdcreatives = facebookClient.fetchObject(adsId + "/adcreatives", JsonObject.class, Parameter.with("fields", fields));
			String strAdcreatives = jsonMapper.toJson(jsonObjectAdcreatives);
			Adcreatives adcreatives = JacksonUtils.fromJson(strAdcreatives, Adcreatives.class);
			log.info("adcreatives:{}", adcreatives);
			listAdcreatives.add(adcreatives);
		} else {
			SysUserFbBind selectSysUserFbBind = new SysUserFbBind();
			selectSysUserFbBind.setUserId(userId);
			SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectSysUserFbBind);
			String adAccountId = sysUserFbBind.getAdAccountId();
			AdInfo selectEntity = new AdInfo();
			selectEntity.setAdAccountId(adAccountId);
			selectEntity.setStatus("ACTIVE");
			List<AdInfo> adInfoList = adInfoMapper.selectByEntity(selectEntity);
			for (AdInfo adsInfo : adInfoList) {
				log.info("adsInfo:{}", adsInfo);
				String campaignId = adsInfo.getCampaignId();
				String adsetId = adsInfo.getAdsetId();
				String status = adsInfo.getStatus();
				CampaignInfo selectCampaignInfo = new CampaignInfo();
				selectCampaignInfo.setCampaignId(campaignId);
				selectCampaignInfo.setStatus(status);
				CampaignInfo campaignInfo = campaignInfoMapper.selectOneByEntity(selectCampaignInfo);
				log.info("campaignInfo:{}", campaignInfo);
				String campaignStatus = campaignInfo.getStatus();
				AdsetInfo selectAdsetInfo = new AdsetInfo();
				selectAdsetInfo.setAdsetId(adsetId);
				AdsetInfo adsetInfo = adsetInfoMapper.selectOneByEntity(selectAdsetInfo);
				log.info("adsetInfo:{}", adsetInfo);
				String adsetStatus = adsetInfo.getStatus();
				if ("ACTIVE".equals(campaignStatus) 
						|| "ACTIVE".equals(adsetStatus)) {
					JsonMapper jsonMapper = facebookClient.getJsonMapper();
					String fields = "id,account_id,body,asset_feed_spec,authorization_category,categorization_criteria,actor_id,adlabels,applink_treatment,branded_content,branded_content_sponsor_page_id,bundle_folder_id,category_media_source,degrees_of_freedom_spec,destination_set_id,dynamic_ad_voice,enable_direct_install,enable_launch_instant_app,facebook_branded_content,image_crops,image_file,image_hash,image_url,instagram_actor_id,instagram_branded_content,instagram_permalink_url,instagram_user_id,interactive_components_spec,link_og_id,link_url,messenger_sponsored_message,name,object_id,object_story_id,object_story_spec,object_type,object_url,place_page_set_id,platform_customizations,playable_asset_id,portrait_customizations,product_set_id,recommender_settings,source_instagram_media_id,template_url,template_url_spec,thumbnail_url,title,url_tags";
					JsonObject jsonObjectAdcreatives = facebookClient.fetchObject(adsInfo.getAdId() + "/adcreatives", JsonObject.class, Parameter.with("fields", fields));
					String strAdcreatives = jsonMapper.toJson(jsonObjectAdcreatives);
					Adcreatives adcreatives = JacksonUtils.fromJson(strAdcreatives, Adcreatives.class);
					log.info("adcreatives:{}", adcreatives);
					listAdcreatives.add(adcreatives);
				}
			}
		}

		Response response = new Response();
		response.setCode("200");
		response.setResult(listAdcreatives);
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}
	
}