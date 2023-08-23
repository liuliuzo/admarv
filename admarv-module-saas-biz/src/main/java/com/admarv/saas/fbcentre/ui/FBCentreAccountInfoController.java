package com.admarv.saas.fbcentre.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.common.ExchangeRateService;
import com.admarv.saas.dashboard.dto.resp.FBAssetsSituation;
import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.lead.constant.AccountStatusEnum;
import com.admarv.saas.fb.lead.constant.AccountStatusEnumCN;
import com.admarv.saas.fb.lead.domain.model.AdAccount;
import com.admarv.saas.fb.model.adaccountinsights.AdAccountInsights;
import com.admarv.saas.fbcentre.dto.resp.AccountInfo;
import com.admarv.saas.fbcentre.dto.resp.WeekData;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.utils.BigDecimalUtils;
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
public class FBCentreAccountInfoController {
	
	private static final Logger log = LoggerFactory.getLogger(FBCentreAccountInfoController.class);
	
	@Autowired
	private FacebookClientService facebookClientService;
	
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;
    
    @Autowired
    private ExchangeRateService exchangeRateService;

    /**
     * FB 数据中心进7天花费栏位
     * 
     * @param userId
     * @return
     */
	@RequestMapping(value = "/admarv/getCentreAccountInfo", method = RequestMethod.GET)
	public String getCentreAccountInfo(String userId) {
		log.info("/admarv/getCentreAccountInfo userId:{}", userId);
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
		
		List<WeekData> listWeekData = Lists.newArrayList();
		WeekData weekData0 = new WeekData();
		WeekData weekData1 = new WeekData();
		WeekData weekData2 = new WeekData();
		WeekData weekData3 = new WeekData();
		WeekData weekData4 = new WeekData();
		WeekData weekData5 = new WeekData();
		WeekData weekData6 = new WeekData();
		
		AdAccountInsights AdAccountInsights0 = getAdAccountInsights(facebookClient, userId, 0);		
		AdAccountInsights AdAccountInsights1 = getAdAccountInsights(facebookClient, userId, 1);
		AdAccountInsights AdAccountInsights2 = getAdAccountInsights(facebookClient, userId, 2);
		AdAccountInsights AdAccountInsights3 = getAdAccountInsights(facebookClient, userId, 3);
		AdAccountInsights AdAccountInsights4 = getAdAccountInsights(facebookClient, userId, 4);
		AdAccountInsights AdAccountInsights5 = getAdAccountInsights(facebookClient, userId, 5);
		AdAccountInsights AdAccountInsights6 = getAdAccountInsights(facebookClient, userId, 6);
		
		weekData0.setDate(AdAccountInsights0.getDate());
		weekData0.setSpend(AdAccountInsights0.getSpend());
		weekData1.setDate(AdAccountInsights1.getDate());
		weekData1.setSpend(AdAccountInsights1.getSpend());
		weekData2.setDate(AdAccountInsights2.getDate());
		weekData2.setSpend(AdAccountInsights2.getSpend());
		weekData3.setDate(AdAccountInsights3.getDate());
		weekData3.setSpend(AdAccountInsights3.getSpend());
		weekData4.setDate(AdAccountInsights4.getDate());
		weekData4.setSpend(AdAccountInsights4.getSpend());
		weekData5.setDate(AdAccountInsights5.getDate());
		weekData5.setSpend(AdAccountInsights5.getSpend());
		weekData6.setDate(AdAccountInsights6.getDate());
		weekData6.setSpend(AdAccountInsights6.getSpend());
		
		listWeekData.add(weekData0);
		listWeekData.add(weekData1);
		listWeekData.add(weekData2);
		listWeekData.add(weekData3);
		listWeekData.add(weekData4);
		listWeekData.add(weekData5);
		listWeekData.add(weekData6);

		FBAssetsSituation fbAssetsSituation = this.getAssetsSituations(userId);
		String balance = fbAssetsSituation.getBalance();
		String balanceCN = fbAssetsSituation.getBalanceCN();
		String amountSpent = fbAssetsSituation.getAmountSpent();
		String amountSpentCN = fbAssetsSituation.getAmountSpentCN();

		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setBalance(balance);
		accountInfo.setBalanceCn(balanceCN);
		accountInfo.setTotalSpend(amountSpent);
		accountInfo.setTotalSpendCn(amountSpentCN);
		accountInfo.setListWeekData(listWeekData);
		
		Response response = new Response();
		response.setCode("200");
		response.setResult(accountInfo);
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}
	
	/**
	 * 查询当前多少天的数据
	 * 
	 */
	private AdAccountInsights getAdAccountInsights(FacebookClient facebookClient, String userId, int minusDays) {
		JsonMapper jsonMapper = facebookClient.getJsonMapper();
		SysUserFbBind selectEntity = new SysUserFbBind();
		selectEntity.setUserId(userId);
		SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
		String adAccountId = sysUserFbBind.getAdAccountId();
		LocalDate queryDate = LocalDate.now().minusDays(minusDays);
		String formatted_date = queryDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String time_range = String.format("{\"since\":\"%s\",\"until\":\"%s\"}", formatted_date, formatted_date);
		// 发起请求获取账户信息
		String fields = "spend,impressions,clicks,cpc,conversions,cost_per_conversion";
		JsonObject jsonObject = facebookClient.fetchObject(adAccountId + "/insights", JsonObject.class,
				Parameter.with("fields", fields), 
				Parameter.with("time_range", time_range));
		String srtjson = jsonMapper.toJson(jsonObject);
		log.info("adAccountId:{},insights:{}", adAccountId, srtjson);
		AdAccountInsights adAccountInsights = JacksonUtils.fromJson(srtjson, AdAccountInsights.class);
		List<com.admarv.saas.fb.model.adaccountinsights.Data> listData = adAccountInsights.getData();
		if (!CollectionUtils.isEmpty(listData)) {
			String spend = adAccountInsights.getData().get(0).getSpend();
			adAccountInsights.setSpend(spend);
		} else {
			adAccountInsights.setSpend("0.00");
		}
		adAccountInsights.setDate(formatted_date);
		log.info("adAccountInsights:{}", adAccountInsights);
		return adAccountInsights;
	}
	
	/**
     * 获取账户详情信息
     * 
     * @param userName
     * @return
     */
    private FBAssetsSituation getAssetsSituations(String userId) {
        // 获取saas平台绑定的用户广告账户
        SysUserFbBind selectEntity = new SysUserFbBind();
        selectEntity.setUserId(userId);
        SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
        String adAccountId = sysUserFbBind.getAdAccountId();
        FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
        JsonMapper jsonMapper = facebookClient.getJsonMapper();
        
        // 发起请求获取账户信息
        String fields = "id,account_id,amount_spent,balance,currency,account_status,spend_cap";
        JsonObject jsonObject = facebookClient.fetchObject(adAccountId, JsonObject.class, Parameter.with("fields", fields));
        String srtjson = jsonMapper.toJson(jsonObject);
        AdAccount adaccount = JacksonUtils.fromJson(srtjson, AdAccount.class);
        String id = adaccount.getId();
        String accountId = adaccount.getAccountId();
        String amountSpent = adaccount.getAmountSpent();
        String currency = adaccount.getCurrency();
        String accountStatus = adaccount.getAccountStatus();
        String spendCap =adaccount.getSpendCap();
        
        BigDecimal divisor = new BigDecimal(100);
        BigDecimal amountSpentBD = BigDecimalUtils.convertToBigDecimal(amountSpent);
        BigDecimal spendCapBD = BigDecimalUtils.convertToBigDecimal(spendCap);
        BigDecimal leftBD = spendCapBD.subtract(amountSpentBD);
        BigDecimal dispLeftBD = leftBD.divide(divisor, 2, RoundingMode.HALF_UP);
        
        FBAssetsSituation assetsSituation = new FBAssetsSituation();
        assetsSituation.setId(id);
        assetsSituation.setAcctId(accountId);
        BigDecimal disAmtSpentBD = amountSpentBD.divide(divisor, 2, RoundingMode.HALF_UP);
        assetsSituation.setAmountSpent(disAmtSpentBD.toPlainString());
        assetsSituation.setBalance(dispLeftBD.toPlainString());
        assetsSituation.setCurrency(currency);
        assetsSituation.setAcctStat(accountStatus);
        AccountStatusEnum accountStatusEnum = AccountStatusEnum.getByCode(accountStatus);
        String desc = accountStatusEnum.getValue();
        assetsSituation.setAcctStatDesc(desc);
        AccountStatusEnumCN accountStatusEnumCN = AccountStatusEnumCN.getByCode(accountStatus);
        String descCN = accountStatusEnumCN.getValue();
        assetsSituation.setAcctStatDescCN(descCN);
        
        //计算RMB金额
        try {
            String rate = exchangeRateService.exchangeRate(assetsSituation.getCurrency(), "CNY");
            BigDecimal rateBD = BigDecimalUtils.convertToBigDecimal(rate);
            BigDecimal balanceCN = dispLeftBD.multiply(rateBD);
            BigDecimal disAmtSpentBDCN = disAmtSpentBD.multiply(rateBD);
            assetsSituation.setBalanceCN(balanceCN.toPlainString());
            assetsSituation.setAmountSpentCN(disAmtSpentBDCN.toPlainString());
        } catch (Exception e) {
            log.error("exchangeRate error", e);
        }
        log.info("assetsSituation:{}", assetsSituation);
        return assetsSituation;
    }
	
}
