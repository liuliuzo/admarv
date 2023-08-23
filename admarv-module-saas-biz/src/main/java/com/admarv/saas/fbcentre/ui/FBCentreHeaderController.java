package com.admarv.saas.fbcentre.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.lead.domain.model.AdAccount;
import com.admarv.saas.fb.model.adaccountinsights.AdAccountInsights;
import com.admarv.saas.fb.model.adaccountinsights.Data;
import com.admarv.saas.fbcentre.dto.resp.Header;
import com.admarv.saas.mapper.LeadGenMapper;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.utils.BigDecimalUtils;
import com.admarv.saas.utils.ConvertUtils;
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
//@RestController
public class FBCentreHeaderController {

	private static final Logger log = LoggerFactory.getLogger(FBCentreHeaderController.class);
	
    @Autowired
    private FacebookClientService facebookClientService;
    
    @Autowired
    private LeadGenMapper leadGenMapper;
    
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;

	@RequestMapping(value = "/admarv/getHeader", method = RequestMethod.GET)
	public String fbCentre(String userId) {
		log.info("/admarv/dashboard userId:{}", userId);
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
		
		Header header = this.getHeader(userId);
		log.info("header:{}", header);
		Response response = new Response();
		response.setResult(header);
		response.setCode("200");
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}
	
	private Header getHeader(String userId) {
		
		Header header = new Header();
		 // 获取saas平台绑定的用户广告账户
        SysUserFbBind selectEntity = new SysUserFbBind();
        selectEntity.setUserId(userId);
        SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
        String adAccountId = sysUserFbBind.getAdAccountId();
        String pageId = sysUserFbBind.getPageId();
        FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
        JsonMapper jsonMapper = facebookClient.getJsonMapper();
        
        // 发起请求获取账户基本信息
		String fields = "id,account_id,amount_spent,balance,currency,account_status,spend_cap,min_daily_budget";
        JsonObject jsonObject = facebookClient.fetchObject(adAccountId, JsonObject.class, Parameter.with("fields", fields));
        String srtjson = jsonMapper.toJson(jsonObject);
        AdAccount adaccount = JacksonUtils.fromJson(srtjson, AdAccount.class);
        String spendCap = adaccount.getSpendCap(); 			       //账户充值总上限
        String minDailyBudget = adaccount.getMinDailyBudget();     //每日最小金额
		//String dailyBudget = getDailyBudget(userId, adAccountId);  //每日预算
        
        BigDecimal divisor = new BigDecimal(100);
        BigDecimal spendCapBD = BigDecimalUtils.convertToBigDecimal(spendCap);
        BigDecimal dispSpendCap = spendCapBD.divide(divisor, 2, RoundingMode.HALF_UP);
		String todayDt = getBeforeDayString(0);
		int count = leadGenMapper.countByDate(todayDt, userId, pageId);
		log.info("today count:{}", count);
		
		LocalDate currentDate = LocalDate.now();
	    String formatted_date = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    String time_range = String.format("{\"since\":\"%s\",\"until\":\"%s\"}", formatted_date, formatted_date);
	    
	    // 获取广告成效详情数据
	    String fields02 = "spend,impressions,clicks,cpc,conversions,cost_per_conversion,unique_clicks,reach";
	    JsonObject jsonObject02 = facebookClient.fetchObject(adAccountId + "/insights", JsonObject.class,
	    		Parameter.with("fields", fields02), 
	    		Parameter.with("time_range", time_range));
	    
	    String srtjson02 = jsonMapper.toJson(jsonObject02);
	    AdAccountInsights adAccountInsights = JacksonUtils.fromJson(srtjson02, AdAccountInsights.class);
	    List<Data> listData = adAccountInsights.getData();
	    
		log.info("listData:{}", listData);
		if (listData != null && listData.size() > 0) {
			Data data = listData.get(0);
			String clicks = data.getClicks();
			String cpc = data.getCpc();
			String impressions = data.getImpressions();
			String spend = data.getSpend();
			String uniqueClicks = data.getUniqueClicks();
			String reach = data.getReach();
			BigDecimal cpcBigDecimal = BigDecimalUtils.convertToBigDecimal(cpc);
			BigDecimal cpcBD = cpcBigDecimal.setScale(2, RoundingMode.HALF_UP);
			int intClicks = ConvertUtils.convertToInt(clicks);
			int intImpressions = ConvertUtils.convertToInt(impressions);
		    NumberFormat percentFormat = NumberFormat.getPercentInstance();
		    String clickRate = percentFormat.format((double) intClicks / intImpressions);
		    header.setClickRate(clickRate);
			header.setClicks(clicks);
			header.setCpc(cpcBD.toPlainString());
			header.setImpression(impressions);
			header.setSpend(spend);
			header.setLeadToday(count);
			header.setSpendCap(dispSpendCap.toPlainString());
			//header.setDailyBudget(dailyBudget);
			header.setReach(reach);	
			header.setUnqCount(uniqueClicks);
			log.info("header:{}", header);
			return header;
		} else {
			log.info("header:{}", header);
			header.setLeadToday(count);
			header.setSpendCap(dispSpendCap.toPlainString());
			//header.setDailyBudget(dailyBudget);
			return header;
		}
	}
	
//	/**
//	 * 每日预算
//	 * 
//	 * @param userId
//	 * @param adAccountId
//	 * @return
//	 */
//	private String getDailyBudget(String userId, String adAccountId) {
//		BigDecimal dailyBudgetAdSet = BigDecimal.ZERO;
//		BigDecimal dailyBudgetCampaigns = BigDecimal.ZERO;
//		
//		FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
//		JsonMapper jsonMapper = facebookClient.getJsonMapper();
//		JsonObject jsonObjectCampaigns = facebookClient.fetchObject(adAccountId + "/campaigns", JsonObject.class);
//		String strJsonCampaigns = jsonMapper.toJson(jsonObjectCampaigns);
//		Campaigns campaigns = JacksonUtils.fromJson(strJsonCampaigns, Campaigns.class);
//		List<com.admarv.saas.fb.model.campaigns.Data> listData = campaigns.getData();
//		for (com.admarv.saas.fb.model.campaigns.Data data : listData) {
//			//广告系列信息
//			String id = data.getId();
//			String fields = "daily_budget,name,status";
//			JsonObject campaignsFields = facebookClient.fetchObject(id, JsonObject.class,Parameter.with("fields", fields));
//			String strCampaignsFields = jsonMapper.toJson(campaignsFields);
//			com.admarv.saas.fb.model.campaignsinsights.CampaignsFields campaign = JacksonUtils.fromJson(strCampaignsFields, com.admarv.saas.fb.model.campaignsinsights.CampaignsFields.class);
//			log.info("campaign:{}", campaign);
//			String strDailyBudget = campaign.getDailyBudget();
//			String status = campaign.getStatus();
//			if ("ACTIVE".equals(status)) {
//				BigDecimal dailyBudgetBD = BigDecimalUtils.convertToBigDecimal(strDailyBudget);
//				dailyBudgetCampaigns = dailyBudgetCampaigns.add(dailyBudgetBD);
//			}
//		}
//		
//		//广告组
//		JsonObject jsonObjectAdsets = facebookClient.fetchObject(adAccountId + "/adsets", JsonObject.class);
//		String strJsonAdsets = jsonMapper.toJson(jsonObjectAdsets);
//		AdSets adSets = JacksonUtils.fromJson(strJsonAdsets, AdSets.class);
//		List<com.admarv.saas.fb.model.adsets.Data> listAdSets = adSets.getData();
//		for (com.admarv.saas.fb.model.adsets.Data data : listAdSets) {
//			String id = data.getId();
//			String fields = "daily_budget,name,status";
//			JsonObject adSetFields = facebookClient.fetchObject(id, JsonObject.class, Parameter.with("fields", fields));
//			String strAdSet = jsonMapper.toJson(adSetFields);
//			AdSet adSet = JacksonUtils.fromJson(strAdSet, AdSet.class);
//			log.info("adSet:{}", adSet);
//			String status = adSet.getStatus();
//			String adSetDailyBudget = adSet.getDailyBudget();
//			if ("ACTIVE".equals(status)) {
//				BigDecimal dailyBudgetBD = BigDecimalUtils.convertToBigDecimal(adSetDailyBudget);
//				dailyBudgetAdSet = dailyBudgetAdSet.add(dailyBudgetBD);
//			}
//		}
//		
//		BigDecimal divisor = new BigDecimal(100);
//		int comparisonResult = dailyBudgetAdSet.compareTo(dailyBudgetCampaigns);
//		if (comparisonResult > 0) {
//			BigDecimal dispDailyBudget = dailyBudgetAdSet.divide(divisor, 2, RoundingMode.HALF_UP);
//			return dispDailyBudget.toPlainString();
//		} else if (comparisonResult < 0) {
//			BigDecimal dispDailyBudget = dailyBudgetCampaigns.divide(divisor, 2, RoundingMode.HALF_UP);
//			return dispDailyBudget.toPlainString();
//		} else {
//			BigDecimal dispDailyBudget = dailyBudgetAdSet.divide(divisor, 2, RoundingMode.HALF_UP);
//			return dispDailyBudget.toPlainString();
//		}
//	}

	private String getBeforeDayString(int days) {
		LocalDateTime localDateTime = LocalDateTime.now().minusDays(days);
		ZoneId beijingZoneId = ZoneId.of("Asia/Shanghai");
		ZonedDateTime beijingDateTime = ZonedDateTime.of(localDateTime, beijingZoneId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String todayDt = beijingDateTime.format(formatter);
		log.info("getBeforeDayString:{}", todayDt);
		return todayDt;
	}
}
