package com.admarv.saas.dashboard.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
import org.springframework.stereotype.Service;

import com.admarv.saas.common.ExchangeRateService;
import com.admarv.saas.common.UserRoleConstant;
import com.admarv.saas.dashboard.dto.resp.AdsOverview;
import com.admarv.saas.dashboard.dto.resp.DashBoard;
import com.admarv.saas.dashboard.dto.resp.FBAssetsSituation;
import com.admarv.saas.dashboard.dto.resp.KeyData;
import com.admarv.saas.dashboard.dto.resp.MonthlyData;
import com.admarv.saas.dashboard.dto.resp.MonthlyTrend;
import com.admarv.saas.dashboard.dto.resp.QuarterData;
import com.admarv.saas.dashboard.dto.resp.QuarterTrend;
import com.admarv.saas.dashboard.dto.resp.WeekTrend;
import com.admarv.saas.dashboard.dto.resp.WeekTrendData;
import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.lead.constant.AccountStatusEnum;
import com.admarv.saas.fb.lead.constant.AccountStatusEnumCN;
import com.admarv.saas.fb.model.adaccountinsights.AdAccountInsights;
import com.admarv.saas.fb.model.adaccountinsights.Data;
import com.admarv.saas.fb.model.pageInfo.PageInfo;
import com.admarv.saas.mapper.AdaccountDetailMapper;
import com.admarv.saas.mapper.LeadGenMapper;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.mapper.SysUserRoleMapper;
import com.admarv.saas.model.AdaccountDetail;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.model.SysUserRole;
import com.admarv.saas.utils.BigDecimalUtils;
import com.admarv.saas.utils.ConvertUtils;
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
@Service
public class DashBrdGnrtService {
    
    private static final Logger log = LoggerFactory.getLogger(DashBrdGnrtService.class);

    @Autowired
    private LeadGenMapper leadGenMapper;
    
    @Autowired
    private FacebookClientService facebookClientService;
    
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;
    
    @Autowired
    private ExchangeRateService exchangeRateService;
    
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    
    @Autowired
    private AdaccountDetailMapper adaccountDetailMapper;
    
    public DashBoard getDashBoard(String userId) {
        SysUserFbBind selectEntity = new SysUserFbBind();
        selectEntity.setUserId(userId);
        SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
        String pageId = sysUserFbBind.getPageId();
        
        DashBoard bashBoard = new DashBoard();
        KeyData keyData = new KeyData(); 
    	this.getKeyData(keyData, userId, pageId);
		this.getKeyDataGrowth(keyData, userId, pageId);
        log.info("keyData:{}", keyData);
        bashBoard.setKeyData(keyData);
        
        //获取广告账户详情
        FBAssetsSituation fbAssetsSituation = this.getAssetsSituations(userId);
        log.info("fbAssetsSituation:{}", fbAssetsSituation);
        bashBoard.setFbAssetsSituation(fbAssetsSituation);
        
		AdsOverview adsOverview = this.getAdsOverview(userId);
        log.info("adsOverview:{}", adsOverview);
        bashBoard.setAdsOverview(adsOverview);
        
        WeekTrend weekTrend = this.getWeekTrend(userId, pageId);
        log.info("weekTrend:{}", weekTrend);
        bashBoard.setWeekTrend(weekTrend);

		MonthlyTrend monthlyTrend = this.getMonthlyTrend(userId, pageId);
        log.info("monthlyTrend:{}", monthlyTrend);
        bashBoard.setMonthlyTrend(monthlyTrend);

		QuarterTrend quarterTrend = this.getQuarterTrend(userId, pageId);
        log.info("quarterTrend:{}", quarterTrend);
        bashBoard.setQuarterTrend(quarterTrend);
        return bashBoard;
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
    
    /**
     * 获取核心数据
     * 
     * @param userName
     * @return
     */
    private KeyData getKeyData(KeyData keyData, String userId, String pageId) {

        String todayDt = this.getBeforeDayString(0);
        int todayCount = leadGenMapper.countByDate(todayDt, userId, pageId);
        log.info("today count:{}", todayCount);
        keyData.setTodayLeads(todayCount);
        
        //昨日询盘数量
        String srtLastDay = this.getBeforeDayString(1);
        int lastDayCount = leadGenMapper.countByDate(srtLastDay, userId, pageId);
        log.info("last day count:{}", lastDayCount);
        keyData.setLastDayLeads(lastDayCount);
        
        //7天询盘数量，今天不包含在内
        String sevenDayBeforeDt = this.getBeforeDayString(7);
        int sevenDayBeforeCount = leadGenMapper.countBetweenByPageId(sevenDayBeforeDt, srtLastDay, userId, pageId);
        log.info("seven day count:{}", sevenDayBeforeCount);
        keyData.setSevenDayLeads(sevenDayBeforeCount);
        
        //30天询盘数量，今天不包含在内
        String thirtyDayBeforeDt = this.getBeforeDayString(30);
        int thirtyDayBeforeCount = leadGenMapper.countBetweenByPageId(thirtyDayBeforeDt, srtLastDay, userId, pageId);
        log.info("30 day count:{}", thirtyDayBeforeCount);
        keyData.setThirtyDayLeads(thirtyDayBeforeCount);
        
        //获取公共主页粉丝数量
        FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
        JsonMapper jsonMapper = facebookClient.getJsonMapper();
        String fields = "fan_count";
        FacebookClient fBPageAccessClient = facebookClientService.getPageAccessClient(facebookClient, userId);
        JsonObject jsonObject = fBPageAccessClient.fetchObject(pageId, JsonObject.class, Parameter.with("fields", fields));
        String srtjson = jsonMapper.toJson(jsonObject);
        PageInfo pageInfo = JacksonUtils.fromJson(srtjson, PageInfo.class);
        int fanCount = pageInfo.getFanCount();
        keyData.setAllFansCount(fanCount);
        
        //累计询盘数量
		int count = leadGenMapper.countAllByUser(userId);
		keyData.setCount(count);
        
        log.info("keyData:{}", keyData);
        return keyData;
    }
    
    /**
     * 获取核心数据
     * 
     * @param userName
     * @return
     */
    private KeyData getKeyDataGrowth(KeyData keyData, String userId, String pageId) {
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");        
        //今日询盘数量
        String todayDt = LocalDate.now().format(formatter);
        int todayCount = leadGenMapper.countByDate(todayDt, userId, pageId);
        log.info("today count:{}", todayCount);
        //上周今日询盘数量
        String lastWeekTodayDt = LocalDate.now().minusWeeks(1).format(formatter);
        int lastWeekTodayCount = leadGenMapper.countByDate(lastWeekTodayDt, userId, pageId);
		log.info("last week today count:{}", lastWeekTodayCount);
		//计算增幅    
		keyData.setTodayLeadsGrowth(this.getGrowth(todayCount, lastWeekTodayCount));
        
        //近7日询盘数量
        LocalDate sevenDayBefore = LocalDate.now().minusDays(7);
        String sevenDayBeforeDt = sevenDayBefore.format(formatter);
        int sevenDayBeforeCount = leadGenMapper.countBetweenByPageId(sevenDayBeforeDt, todayDt, userId, pageId);
        log.info("seven day count:{}", sevenDayBeforeCount);
        //上周近7日询盘数量
		LocalDate lastWeekSevenDayBefore = LocalDate.now().minusWeeks(1).minusDays(7);
		String lastWeekSevenDayBeforeDt = lastWeekSevenDayBefore.format(formatter);
		int lastWeekSevenDayBeforeCount = leadGenMapper.countBetweenByPageId(lastWeekSevenDayBeforeDt, lastWeekTodayDt, userId, pageId);
		log.info("last Week Seven Day Before Count:{}", lastWeekSevenDayBeforeCount);
        //计算增幅同比7天询盘数量-上周
        log.info("seven day count:{}", sevenDayBeforeCount);
        keyData.setSevenDayLeadsGrowth(this.getGrowth(sevenDayBeforeCount, lastWeekSevenDayBeforeCount));
        
        //近30天数据
        LocalDate thirtyDayBefore = LocalDate.now().minusDays(30);
        String thirtyDayBeforeTodayDt = thirtyDayBefore.format(formatter);
        int thirtyDayBeforeCount = leadGenMapper.countBetweenByPageId(thirtyDayBeforeTodayDt, todayDt, userId, pageId);
        log.info("30 day count:{}", thirtyDayBeforeCount);      
        //前30天数据
        LocalDate lastThirtyDayBefore = LocalDate.now().minusDays(30).minusDays(30);
        String lastThirtyDayBeforeDt = lastThirtyDayBefore.format(formatter);
        int lastThirtyDayBeforeCount = leadGenMapper.countBetweenByPageId(lastThirtyDayBeforeDt, thirtyDayBeforeTodayDt, userId, pageId);
        log.info("last 30 day count:{}", lastThirtyDayBeforeCount);
        keyData.setThirtyDayLeadsGrowth(this.getGrowth(thirtyDayBeforeCount, lastThirtyDayBeforeCount));
        log.info("keyData:{}", keyData);
        
        //同比昨日询盘数量-上周
        LocalDate lastDay = LocalDate.now().minusWeeks(1).minusDays(1);
        String srtLastDay = lastDay.format(formatter);
        int lastDayCount = leadGenMapper.countByDate(srtLastDay, userId, pageId);
        log.info("last day count:{}", lastDayCount);
        keyData.setLastDayLeadsGrowth(this.getGrowth(todayCount, lastDayCount));
        return keyData;
    }
    
	private String getGrowth(int current, int same) {
		DecimalFormat df = new DecimalFormat("#0.00"); // 保留两位小数的格式化对象
		int divisor = current - same;
		double percentage1 = ((double) divisor / (double) same) * 100;
		String formattedPercentage = df.format(percentage1);
		return formattedPercentage + "%";
	}
    

    /**
     * 获取账户详情信息
     * @param userName
     * @return
     */
    private FBAssetsSituation getAssetsSituations(String userId) {
    	
		SysUserRole selectSysUserRole = new SysUserRole();
		selectSysUserRole.setUserId(userId);
		SysUserRole sysUserRole = sysUserRoleMapper.selectOneByEntity(selectSysUserRole);
		String roleId = sysUserRole.getRoleId();

        // 获取saas平台绑定的用户广告账户
        SysUserFbBind selectEntity = new SysUserFbBind();
        selectEntity.setUserId(userId);
        SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
        String adAccountId = sysUserFbBind.getAdAccountId();
        FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
        JsonMapper jsonMapper = facebookClient.getJsonMapper();
        
        // 获取账户信息        
        AdaccountDetail selectAdaccountDetail=new AdaccountDetail();
        selectAdaccountDetail.setUserId(userId);
		AdaccountDetail adaccount = adaccountDetailMapper.selectOneByEntity(selectAdaccountDetail);
        String accountId = adaccount.getAccountId();
        String amountSpent = adaccount.getAmountSpent();
        String balance = adaccount.getBalance();
        String currency = adaccount.getCurrency();
        String accountStatus = adaccount.getAccountStatus();
        String spendCap =adaccount.getSpendCap();
        
        BigDecimal amountSpentBD = BigDecimalUtils.convertToBigDecimal(amountSpent);
        BigDecimal spendCapBD = BigDecimalUtils.convertToBigDecimal(spendCap);
        
        BigDecimal divisor = new BigDecimal(100);
        BigDecimal leftBD = spendCapBD.subtract(amountSpentBD);
		BigDecimal balanceBD = BigDecimalUtils.convertToBigDecimal(balance);
        BigDecimal dispLeftBD = leftBD.divide(divisor, 2, RoundingMode.HALF_UP);
		BigDecimal dispBalanceBD = balanceBD.divide(divisor, 2, RoundingMode.HALF_UP);
		
        FBAssetsSituation assetsSituation = new FBAssetsSituation();
        assetsSituation.setId(accountId);
        assetsSituation.setAcctId(accountId);
        BigDecimal disAmtSpentBD = amountSpentBD.divide(divisor, 2, RoundingMode.HALF_UP);
        assetsSituation.setAmountSpent(disAmtSpentBD.toPlainString());
        assetsSituation.setCurrency(currency);
        assetsSituation.setAcctStat(accountStatus);
        AccountStatusEnum accountStatusEnum = AccountStatusEnum.getByCode(accountStatus);
        String desc = accountStatusEnum.getValue();
        assetsSituation.setAcctStatDesc(desc);
        AccountStatusEnumCN accountStatusEnumCN = AccountStatusEnumCN.getByCode(accountStatus);
        String descCN = accountStatusEnumCN.getValue();
        assetsSituation.setAcctStatDescCN(descCN);
        
		// 如果是客户的话低于5000
		if (UserRoleConstant.ROLE_CUSTOMER.equals(roleId)) {
			// 计算RMB金额
			try {
				String rate = exchangeRateService.exchangeRate(assetsSituation.getCurrency(), "CNY");
				BigDecimal rateBD = BigDecimalUtils.convertToBigDecimal(rate);
				BigDecimal balanceCN = dispLeftBD.multiply(rateBD);
				assetsSituation.setBalanceCN(balanceCN.toPlainString());
				BigDecimal amountSpentCN = disAmtSpentBD.multiply(rateBD);
				assetsSituation.setAmountSpentCN(amountSpentCN.toPlainString());
			} catch (Exception e) {
				log.error("exchangeRate error", e);
			}
			assetsSituation.setBalance(dispLeftBD.toPlainString());;
			BigDecimal threshold = new BigDecimal("5000");
			int comparisonResult = dispLeftBD.compareTo(threshold);
			if (comparisonResult < 0) {
				assetsSituation.setBalanceWarning(true);
			}
			// 计算百分比所占的比例
			BigDecimal percentage = dispLeftBD.divide(spendCapBD, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
			assetsSituation.setPercentage(percentage);
		// 如果是运维的话真实值的20%预警
		} else if (!UserRoleConstant.ROLE_CUSTOMER.equals(roleId)) {
			// 计算RMB金额
			try {
				String rate = exchangeRateService.exchangeRate(assetsSituation.getCurrency(), "CNY");
				BigDecimal rateBD = BigDecimalUtils.convertToBigDecimal(rate);
				BigDecimal balanceCN = dispBalanceBD.multiply(rateBD);
				assetsSituation.setBalanceCN(balanceCN.toPlainString());
				BigDecimal amountSpentCN = disAmtSpentBD.multiply(rateBD);
				assetsSituation.setAmountSpentCN(amountSpentCN.toPlainString());
			} catch (Exception e) {
				log.error("exchangeRate error", e);
			}
			assetsSituation.setBalance(dispBalanceBD.toPlainString());
			// 计算百分比所占的比例
			BigDecimal percentage = dispBalanceBD.divide(spendCapBD, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
			BigDecimal threshold = new BigDecimal("0.2");
			int comparisonResult = percentage.compareTo(threshold);
			if (comparisonResult < 0) {
				assetsSituation.setBalanceWarning(true);
			}
			assetsSituation.setPercentage(percentage);
		}
        log.info("assetsSituation:{}", assetsSituation);
        return assetsSituation;
    }
    
    
    /**
     * 生成广告概览数据
     * 
     * @param userName
     * @return
     */
    private AdsOverview getAdsOverview(String userId) {
        FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
        JsonMapper jsonMapper = facebookClient.getJsonMapper();  
        SysUserFbBind selectEntity = new SysUserFbBind();
        selectEntity.setUserId(userId);
        SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
        String adAccountId = sysUserFbBind.getAdAccountId();
        String pageId = sysUserFbBind.getPageId();
        
        LocalDate currentDate = LocalDate.now();
        String formatted_date = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time_range = String.format("{\"since\":\"%s\",\"until\":\"%s\"}", formatted_date, formatted_date);
        // 发起请求获取账户信息
        String fields = "spend,impressions,clicks,cpc,conversions,cost_per_conversion";
        JsonObject jsonObject = facebookClient.fetchObject(adAccountId + "/insights", JsonObject.class, 
                Parameter.with("fields", fields),
                Parameter.with("time_range", time_range));
        
        String srtjson = jsonMapper.toJson(jsonObject);
        AdAccountInsights adAccountInsights = JacksonUtils.fromJson(srtjson, AdAccountInsights.class);
        List<Data> listData = adAccountInsights.getData();
        log.info("listData:{}", listData);
		if (listData != null && listData.size() > 0) {
            Data data = listData.get(0);
            String clicks = data.getClicks();
            String cpc = data.getCpc();
            String impressions = data.getImpressions();
            String spend = data.getSpend();
            
            AdsOverview adsOverview = new AdsOverview();
            adsOverview.setClicks(clicks);
            adsOverview.setImpressions(impressions);
            BigDecimal cpcBigDecimal = BigDecimalUtils.convertToBigDecimal(cpc);
            BigDecimal cpcBD = cpcBigDecimal.setScale(2, RoundingMode.HALF_UP);;
            adsOverview.setCpc(cpcBD.toPlainString());
            adsOverview.setSpend(spend);
            
            //adsOverview.setCostPerConv(costPerConversion);
            int intClicks = ConvertUtils.convertToInt(clicks);
            int intImpressions = ConvertUtils.convertToInt(impressions);
            double doubleSpend= ConvertUtils.convertToDouble(spend);
            NumberFormat percentFormat = NumberFormat.getPercentInstance();
		    String clickRate = percentFormat.format((double) intClicks / intImpressions);
            adsOverview.setClicksImpressions(clickRate);
            String dateStart = data.getDateStart();
            String dateStop = data.getDateStop();
            //30天询盘数量
            int lead = leadGenMapper.countBetweenByPageId(dateStart, dateStop, userId, pageId);
            DecimalFormat df = new DecimalFormat("#0.00"); // 保留两位小数的格式化对象
            String pct2 = df.format((double) lead / intImpressions);
            adsOverview.setLeadImpressions(pct2 + "%");
            double costPerConv = doubleSpend / lead ;
            String strCostPerConv = df.format(costPerConv);
            adsOverview.setCostPerConv(strCostPerConv);
            String pct3 = df.format((double) lead / intClicks);
            adsOverview.setLeadClicks(pct3 + "%");
            log.info("adsOverview:{}", adsOverview);
            return adsOverview;
        } else {
            return new AdsOverview();
        }
    }
    
    private WeekTrend getWeekTrend(String userId, String pageId) {
        
        WeekTrend weekTrend = new WeekTrend();
        List<WeekTrendData> weekTrendDataList = Lists.newArrayList();
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 今日询盘数量
        String srtDt0 = LocalDate.now().format(formatter);
        int count0 = leadGenMapper.countByDate(srtDt0, userId, pageId);
        WeekTrendData weekTrendData0 = new WeekTrendData();
        weekTrendData0.setDate(srtDt0);
        weekTrendData0.setCount(count0);
        
        LocalDate dt1 = LocalDate.now().minusDays(1);
        String srtDt1 = dt1.format(formatter);
        int count1 = leadGenMapper.countByDate(srtDt1, userId, pageId);
        WeekTrendData weekTrendData1 = new WeekTrendData();
        weekTrendData1.setDate(srtDt1);
        weekTrendData1.setCount(count1);
        
        LocalDate dt2 = LocalDate.now().minusDays(2);
        String srtDt2 = dt2.format(formatter);
        int count2 = leadGenMapper.countByDate(srtDt2, userId, pageId);
        WeekTrendData weekTrendData2 = new WeekTrendData();
        weekTrendData2.setDate(srtDt2);
        weekTrendData2.setCount(count2);
        
        LocalDate dt3 = LocalDate.now().minusDays(3);
        String srtDt3 = dt3.format(formatter);
        int count3 = leadGenMapper.countByDate(srtDt3, userId, pageId);
        WeekTrendData weekTrendData3 = new WeekTrendData();
        weekTrendData3.setDate(srtDt3);
        weekTrendData3.setCount(count3);
        
        LocalDate dt4 = LocalDate.now().minusDays(4);
        String srtDt4 = dt4.format(formatter);
        int count4 = leadGenMapper.countByDate(srtDt4, userId, pageId);
        WeekTrendData weekTrendData4 = new WeekTrendData();
        weekTrendData4.setDate(srtDt4);
        weekTrendData4.setCount(count4);
        
        LocalDate dt5 = LocalDate.now().minusDays(5);
        String srtDt5 = dt5.format(formatter);
        int count5 = leadGenMapper.countByDate(srtDt5, userId, pageId);
        WeekTrendData weekTrendData5 = new WeekTrendData();
        weekTrendData5.setDate(srtDt5);
        weekTrendData5.setCount(count5);
        
        LocalDate dt6 = LocalDate.now().minusDays(6);
        String srtDt6 = dt6.format(formatter);
        int count6 = leadGenMapper.countByDate(srtDt6, userId, pageId);
        WeekTrendData weekTrendData6 = new WeekTrendData();
        weekTrendData6.setDate(srtDt6);
        weekTrendData6.setCount(count6);
        
        weekTrendDataList.add(weekTrendData0);
        weekTrendDataList.add(weekTrendData1);
        weekTrendDataList.add(weekTrendData2);
        weekTrendDataList.add(weekTrendData3);
        weekTrendDataList.add(weekTrendData4);
        weekTrendDataList.add(weekTrendData5);
        weekTrendDataList.add(weekTrendData6);
        weekTrend.setWeekTrendDataList(weekTrendDataList);
        log.info("weekTrend:{}", weekTrend);
        return weekTrend;
    }

    private MonthlyTrend getMonthlyTrend(String userId, String pageId) {
        MonthlyTrend monthlyTrend = new MonthlyTrend();
        List<MonthlyData> monthlyDataList = Lists.newArrayList();
        
        LocalDate startDate = LocalDate.now().minusWeeks(1);
        String startDateStr = startDate.toString();
        LocalDate endDate = LocalDate.now();
        String endDateStr = endDate.toString();
        int week1Count = leadGenMapper.countBetweenByPageId(startDateStr, endDateStr, userId, pageId);
        MonthlyData monthlyData1 = new MonthlyData();
        monthlyData1.setDate("前1周");
        monthlyData1.setCount(week1Count);
        monthlyDataList.add(monthlyData1);
        
        LocalDate startDate2 = LocalDate.now().minusWeeks(2);
        String startDateStr2 = startDate2.toString();
        LocalDate endDate2 = LocalDate.now();
        String endDateStr2 = endDate2.toString(); 
        int week2Count = leadGenMapper.countBetweenByPageId(startDateStr2, endDateStr2, userId, pageId);
        MonthlyData monthlyData2 = new MonthlyData();
        monthlyData2.setDate("前2周");
        monthlyData2.setCount(week2Count);
        monthlyDataList.add(monthlyData2);
        
        LocalDate startDate3 = LocalDate.now().minusWeeks(3);
        String startDateStr3 = startDate3.toString();
        LocalDate endDate3 = LocalDate.now();
        String endDateStr3 = endDate3.toString(); 
        int week3Count = leadGenMapper.countBetweenByPageId(startDateStr3, endDateStr3, userId, pageId);
        MonthlyData monthlyData3 = new MonthlyData();
        monthlyData3.setDate("前3周");
        monthlyData3.setCount(week3Count);
        monthlyDataList.add(monthlyData3);
        
        LocalDate startDate4 = LocalDate.now().minusWeeks(4);
        String startDateStr4 = startDate4.toString();
        LocalDate endDate4 = LocalDate.now();
        String endDateStr4 = endDate4.toString(); 
        int week4Count = leadGenMapper.countBetweenByPageId(startDateStr4, endDateStr4, userId, pageId);
        MonthlyData monthlyData4 = new MonthlyData();
        monthlyData4.setDate("前4周");
        monthlyData4.setCount(week4Count);
        monthlyDataList.add(monthlyData4);
        monthlyTrend.setMonthlyDataList(monthlyDataList);
        log.info("monthlyTrend:{}", monthlyTrend);
        return monthlyTrend;
    }

    private QuarterTrend getQuarterTrend(String userId, String pageId) {
        List<QuarterData> quarterDataList = Lists.newArrayList();
        LocalDate startDate = LocalDate.now().minusMonths(1);
        String startDateStr = startDate.toString();
        LocalDate endDate = LocalDate.now();
        String endDateStr = endDate.toString();
        int month1Count = leadGenMapper.countBetweenByPageId(startDateStr, endDateStr, userId, pageId);
        QuarterData quarterData1 = new QuarterData();
        quarterData1.setDate("前1月");
        quarterData1.setCount(month1Count);
        quarterDataList.add(quarterData1);
        
        LocalDate startDate2 = LocalDate.now().minusMonths(2);
        String startDateStr2 = startDate2.toString();
        LocalDate endDate2 = LocalDate.now().minusMonths(1);
        String endDateStr2 = endDate2.toString();
        int month2Count = leadGenMapper.countBetweenByPageId(startDateStr2, endDateStr2, userId, pageId);
        QuarterData quarterData2 = new QuarterData();
        quarterData2.setDate("前2月");
        quarterData2.setCount(month2Count);
        quarterDataList.add(quarterData2);
        
        LocalDate startDate3 = LocalDate.now().minusMonths(3);
        String startDateStr3 = startDate3.toString();
        LocalDate endDate3 = LocalDate.now().minusMonths(3);
        String endDateStr3 = endDate3.toString();
        int month3Count = leadGenMapper.countBetweenByPageId(startDateStr3, endDateStr3, userId, pageId);
        QuarterData quarterData3 = new QuarterData();
        quarterData3.setDate("前3月");
        quarterData3.setCount(month3Count);
        quarterDataList.add(quarterData3);
        
        QuarterTrend quarterTrend = new QuarterTrend();
        quarterTrend.setQuarterDataList(quarterDataList);
        
        log.info("quarterTrend:{}", quarterTrend);
        return quarterTrend;
    }
}
