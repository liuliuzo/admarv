package com.admarv.saas.fbcentre.ui;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.model.pageEngagement.Data;
import com.admarv.saas.fb.model.pageEngagement.PageEngagement;
import com.admarv.saas.fb.model.pageEngagement.Values;
import com.admarv.saas.fbcentre.domain.AgeGenderService;
import com.admarv.saas.fbcentre.dto.resp.AgeStat;
import com.admarv.saas.fbcentre.dto.resp.FansInfo;
import com.admarv.saas.fbcentre.dto.resp.GenderStat;
import com.admarv.saas.fbcentre.util.AgeStatComparator;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.utils.JacksonUtils;
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
public class FBCentreFansController {

	private static final Logger log = LoggerFactory.getLogger(FBCentreFansController.class);

	@Autowired
	private FacebookClientService facebookClientService;
	
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;

    /**
     * 获取粉丝信息
     * 
     * @param userId
     * @return
     */
	@RequestMapping(value = "/admarv/getFansInfo", method = RequestMethod.GET)
	public String getFansInfo(String userId) {
		log.info("/admarv/getFansInfo userId:{}", userId);
		FansInfo fansInfo = new FansInfo();
		
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
		
		FacebookClient pageAccessClient = facebookClientService.getPageAccessClient(facebookClient, userId);	
		SysUserFbBind selectEntity = new SysUserFbBind();
		selectEntity.setUserId(userId);
		SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
		String pageId = sysUserFbBind.getPageId();
		JsonMapper jsonMapper = pageAccessClient.getJsonMapper();
		
		//page Engagement
		String metric = "page_impressions,page_impressions_unique,page_impressions_paid,page_impressions_paid_unique,page_impressions_organic_v2,page_impressions_organic_unique_v2,page_impressions_viral,page_impressions_viral_unique,page_impressions_nonviral,page_impressions_nonviral_unique,page_impressions_by_story_type,page_impressions_by_story_type_unique,page_impressions_by_city_unique,page_impressions_by_country_unique,page_impressions_by_locale_unique,page_impressions_by_age_gender_unique,page_impressions_frequency_distribution";
		metric = metric + ",page_fans,page_fan_adds_unique,page_fan_removes_unique";
		
		JsonObject objPageInsights = pageAccessClient.fetchObject(pageId + "/insights", JsonObject.class, Parameter.with("metric", metric));
		String strObjPageInsights = jsonMapper.toJson(objPageInsights);
		PageEngagement pageEngagement = JacksonUtils.fromJson(strObjPageInsights, PageEngagement.class);
		log.info("pageEngagement:{}", pageEngagement);
		List<Data> listData = pageEngagement.getData();

		for (Data data : listData) {
			String name = data.getName(); 	  // 统计纬度
			String period = data.getPeriod(); // 时间范围
				
			// 页面粉丝数量
			if (name.equals("page_fans") && "day".equals(period)) {
				List<Values> valuesList = data.getValues();
				Values values1 = valuesList.get(0);
				Integer vlaue1 = (Integer) values1.getValue();
				fansInfo.setFansCount(vlaue1);
			}
			
			// 页面粉丝增加数量
			if (name.equals("page_fan_adds_unique") && "day".equals(period)) {
				List<Values> valuesList = data.getValues();
				Values values1 = valuesList.get(0);
				Integer vlaue1 = (Integer) values1.getValue();
				fansInfo.setGrowFans(vlaue1);
			}
			
			// 页面粉丝流失数量
			if (name.equals("page_fan_removes_unique") && "day".equals(period)) {
				List<Values> valuesList = data.getValues();
				Values values1 = valuesList.get(0);
				Integer vlaue1 = (Integer) values1.getValue();
				fansInfo.setUnlikeFans(vlaue1);
			}
			
			// 页面展示次数
			if (name.equals("page_impressions") && "day".equals(period)) {
				List<Values> valuesList = data.getValues();
				log.info("valuesList:{}", valuesList);
			}

			// 付费粉丝浏览贴文次数
			if (name.equals("page_impressions_paid_unique") && "day".equals(period)) {
				List<Values> valuesList = data.getValues();
				Values values1 = valuesList.get(0);
				Integer vlaue1 = (Integer) values1.getValue();
				fansInfo.setPaidImpressions(vlaue1);
			}

			// 自然粉丝浏览贴文次数
			if (name.equals("page_impressions_unique") && "day".equals(period)) {
				List<Values> valuesList = data.getValues();
				Values values0 = valuesList.get(0);
				Integer vlaue0 = (Integer) values0.getValue();
				fansInfo.setOrgaincImpressions(vlaue0);
			}

			// 按国家分类的页面唯一展示次数
			if (name.equals("page_impressions_by_country_unique") && "day".equals(period)) {
				List<Values> valuesList = data.getValues();
				Values values = valuesList.get(0);
				LinkedHashMap mapValue = (LinkedHashMap) values.getValue();
				log.info("mapValue country unique:{}", mapValue);
			}

			// 按照性别分类展示次数
			if (name.equals("page_impressions_by_age_gender_unique") && "day".equals(period)) {
				List<Values> valuesList = data.getValues();
				Values values = valuesList.get(0);
				LinkedHashMap mapValue = (LinkedHashMap) values.getValue();
				log.info("mapValue gender unique:{}", mapValue);
				
				// 按年龄和性别统计
				Map<String, Integer> ageStatistics = new HashMap<>();
				Map<String, Integer> genderStatistics = new HashMap<>();
				
				AgeGenderService.calculateAgeAndGenderStatistics(ageStatistics, genderStatistics, mapValue);
				
				log.info("ageStatistics:{}", ageStatistics);
				log.info("genderStatistics:{}", genderStatistics);
				List<AgeStat> listAgeStat = Lists.newArrayList();
				for (Map.Entry<String, Integer> entry : ageStatistics.entrySet()) {
				    String rang = entry.getKey();
				    Integer count = entry.getValue();
				    AgeStat genderStat = new AgeStat(rang, count);
				    listAgeStat.add(genderStat);
				}
				Collections.sort(listAgeStat, new AgeStatComparator());
				
				for (AgeStat ageStat : listAgeStat) {
					ageStat.setAgeRange(ageStat.getAgeRange() + " 岁");
				}
				fansInfo.setListAgeStat(listAgeStat);
				
				List<GenderStat> listGenderStat =Lists.newArrayList();
				for (Map.Entry<String, Integer> entry : genderStatistics.entrySet()) {
				    String gender = entry.getKey();
				    Integer count = entry.getValue();
				    GenderStat genderStat = new GenderStat(gender, count);
				    listGenderStat.add(genderStat);
				}
				fansInfo.setListGenderStat(listGenderStat);
			}
		}
		
		log.info("fansInfo:{}", fansInfo);
		Response response = new Response();
		response.setResult(fansInfo);
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}
}
