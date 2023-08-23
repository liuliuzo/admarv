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
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.lead.domain.model.AdAccount;
import com.admarv.saas.fb.model.adaccountinsights.AdAccountInsights;
import com.admarv.saas.fb.model.adaccountinsights.Data;
import com.admarv.saas.fbcentre.dto.resp.FBCentre;
import com.admarv.saas.fbcentre.dto.resp.Header;
import com.admarv.saas.mapper.AdInfoMapper;
import com.admarv.saas.mapper.AdsetInfoMapper;
import com.admarv.saas.mapper.CampaignInfoMapper;
import com.admarv.saas.mapper.LeadGenMapper;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.model.AdInfo;
import com.admarv.saas.model.AdsetInfo;
import com.admarv.saas.model.CampaignInfo;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.utils.BigDecimalUtils;
import com.admarv.saas.utils.ConvertUtils;
import com.admarv.saas.utils.JacksonUtils;
import com.alibaba.excel.util.StringUtils;
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
public class FBCentreController {

	private static final Logger log = LoggerFactory.getLogger(FBCentreController.class);
	
    @Autowired
    private FacebookClientService facebookClientService;
    
    @Autowired
    private LeadGenMapper leadGenMapper;
    
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;
    
    @Autowired
    private AdInfoMapper adInfoMapper;
    
    @Autowired
    private AdsetInfoMapper adsetInfoMapper;
    
    @Autowired
    private CampaignInfoMapper campaignInfoMapper;
   
	@RequestMapping(value = "/admarv/fbCentre", method = RequestMethod.GET)
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
		
		FBCentre fbCentre = new FBCentre();
		Header header = this.getHeader(userId);
		log.info("header:{}", header);
		fbCentre.setHeader(header);
		
		Response response = new Response();
		response.setResult(fbCentre);
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
        log.info("adAccountId:{} srtjson", adAccountId, srtjson);
        AdAccount adaccount = JacksonUtils.fromJson(srtjson, AdAccount.class);
        String spendCap = adaccount.getSpendCap(); 			       //账户充值总上限
        String minDailyBudget = adaccount.getMinDailyBudget();     //每日最小金额
		String dailyBudget = this.getDailyBudget(userId, adAccountId);  //每日预算
        
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
			header.setDailyBudget(dailyBudget);
			header.setReach(reach);	
			header.setUnqCount(uniqueClicks);
			log.info("header:{}", header);
			return header;
		} else {
			log.info("header:{}", header);
			header.setLeadToday(count);
			header.setSpendCap(dispSpendCap.toPlainString());
			header.setDailyBudget(dailyBudget);
			return header;
		}
	}
	
	/**
	 * 每日预算 = 目前运营反馈的逻辑是“Campaign”、 “Adset”、 “Ad”，只有一个设值每日预算
	 * 
	 * @param userId
	 * @param adAccountId
	 * @return
	 */
	private String getDailyBudget(String userId, String adAccountId) {
		BigDecimal result = BigDecimal.ZERO;
		log.info("getDailyBudget userId:{}, adAccountId:{}", userId, adAccountId);
		CampaignInfo selectCampaignInfo = new CampaignInfo();
		selectCampaignInfo.setStatus("ACTIVE");
		selectCampaignInfo.setAdAccountId(adAccountId);
		List<CampaignInfo> campaignInfoList = campaignInfoMapper.selectByEntity(selectCampaignInfo);
		log.info("campaignInfoList:{}", campaignInfoList);
		for (CampaignInfo campaignInfo : campaignInfoList) {
			log.info("campaignInfo:{}", campaignInfo);
			String campaignId = campaignInfo.getCampaignId();
			String campaignDailyBudget = campaignInfo.getDailyBudget();
			//如果广告系列设置了每日预算, 每日预算使用广告系列值
			if (StringUtils.isNotBlank(campaignDailyBudget)) {
				BigDecimal campaignDailyBudgetBD = BigDecimalUtils.convertToBigDecimal(campaignDailyBudget);
				log.info("result:{} + campaignDailyBudgetBD:{}", result, campaignDailyBudgetBD);
				result = result.add(campaignDailyBudgetBD);
			} 
			//广告系列没有设置每日预算，再查看广告组
			else {
				AdsetInfo selectAdsetInfo = new AdsetInfo();
				selectAdsetInfo.setCampaignId(campaignId);
				selectAdsetInfo.setStatus("ACTIVE");
				List<AdsetInfo> adsetInfoList = adsetInfoMapper.selectByEntity(selectAdsetInfo);
				for (AdsetInfo adsetInfo : adsetInfoList) {
					log.info("adsetInfo:{}", adsetInfo);
					String adsetDailyBudget = adsetInfo.getDailyBudget();
					//如果广告组设置了每日预算, 每日预算使用广告组列值
					if (StringUtils.isNotBlank(adsetDailyBudget)) {
						BigDecimal adsetDailyBudgetBD = BigDecimalUtils.convertToBigDecimal(adsetDailyBudget);
						log.info("result:{} + adsetDailyBudgetBD:{}", result, adsetDailyBudgetBD);
						result = result.add(adsetDailyBudgetBD);
					} else {
						AdInfo selectAdInfo = new AdInfo();
						selectAdInfo.setCampaignId(campaignId);
						selectAdInfo.setStatus("ACTIVE");
						List<AdInfo> adInfoList = adInfoMapper.selectByEntity(selectAdInfo);
						for (AdInfo adInfo : adInfoList) {
							log.info("adInfo:{}", adInfo);
							String adInfoDailyBudget = adInfo.getDailyBudget();
							BigDecimal adInfoDailyBudgetBD = BigDecimalUtils.convertToBigDecimal(adInfoDailyBudget);
							log.info("result:{} + adInfoDailyBudgetBD:{}", result, adInfoDailyBudgetBD);
							result = result.add(adInfoDailyBudgetBD);
						}
					}
					log.info("result:{}", result);
				}
			}
		}
		return result.toPlainString();
	}

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
