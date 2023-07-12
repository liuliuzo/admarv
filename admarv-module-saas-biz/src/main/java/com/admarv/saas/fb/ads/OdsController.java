package com.admarv.saas.fb.ads;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.ads.domain.LeadGenService;
import com.admarv.saas.fb.ads.domain.model.Adaccount;
import com.admarv.saas.fb.ads.dto.req.ReqLeadgenAllocate;
import com.admarv.saas.fb.ads.dto.req.ReqLeadgenEdit;
import com.admarv.saas.fb.ads.dto.resp.RespAdaccount;
import com.admarv.saas.fb.ads.dto.resp.RespCampaign;
import com.admarv.saas.fb.ads.task.LeadGenTask;
import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.common.UserService;
import com.admarv.saas.fb.dto.resp.adaccounts.AdAccounts;
import com.admarv.saas.fb.dto.resp.adaccounts.Data;
import com.admarv.saas.fb.page.dto.resp.RespSysUser;
import com.admarv.saas.model.LeadGen;
import com.admarv.saas.model.SysUser;
import com.admarv.saas.utils.JacksonUtils;
import com.google.common.collect.Lists;
import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;
import com.restfb.types.User;
import com.restfb.types.ads.AdAccount;
import com.restfb.types.ads.Campaign;

/**
 * FB广告接口
 * 
 * @author liuliu
 *
 */
@RestController
public class OdsController {

    private static final Logger log = LoggerFactory.getLogger(OdsController.class);

    @Autowired
    private FacebookClientService facebookClientService;

    @Autowired
    private LeadGenTask leadGenTask;

    /**
     * 广告线索询盘服务
     */
    @Autowired
    private LeadGenService leadGenService;
    
    @Autowired
    private UserService userService;

    /**
     * 获取广告系列查询
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "/admarv/campaigns", method = RequestMethod.GET)
    public String auth(String user) {
        List<RespCampaign> campaignList = Lists.newArrayList();
        log.info("/admarv/campaigns user:{}", user);
        FacebookClient facebookClient = facebookClientService.getClientByUser(user);
        log.info("fbClient:{}", facebookClient);
        AdAccounts adaccounts = facebookClient.fetchObject("me/adaccounts", AdAccounts.class);
        List<Data> listData = adaccounts.getData();
        for (Data data : listData) {
            String id = data.getId();
            String url = "me/" + id + "campaigns";
            List<Campaign> campaigns = facebookClient.fetchObject(url, List.class);
            for (Campaign campaign : campaigns) {
                RespCampaign respCampaign = new RespCampaign();
                String campaignId = campaign.getId();
                String name = campaign.getName();
                respCampaign.setId(campaignId);
                respCampaign.setNam(name);
                campaignList.add(respCampaign);
            }
        }
        Response response = new Response();     
        response.setCode("200");
        response.setResult(campaignList);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }

    /**
     * 获取广告系详情
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "/admarv/campaigns/insights", method = RequestMethod.GET)
    public String campaignsInsights(String user, String campaignId) {
        FacebookClient facebookClient = facebookClientService.getClientByUser(user);
        JsonObject insights = facebookClient.fetchObject("CAMPAIGN_ID/insights", JsonObject.class,
                Parameter.with("fields", "impressions,clicks,cost"));
        Response response = new Response();     
        response.setCode("200");
        response.setResult(insights);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }

    /**
     * 获取广告线索询盘
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "/admarv/leadgen", method = RequestMethod.GET)
    public String leadgen(String user) {
        log.info("/admarv/leadgen user:{}", user);
        List<LeadGen> leadGenList = leadGenService.getLeadGenByUser(user);
        Response response = new Response();     
        response.setCode("200");
        response.setResult(leadGenList);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
    
    /**
     * 刷新广告线索询盘数据
     * 
     * @return
     */
    @RequestMapping(value = "/admarv/refreshLeadgen", method = RequestMethod.GET)
    public String leadgen() {
        log.info("/admarv/refreshLeadgen");
        leadGenTask.doLeadGenTask();
        return "success refresh leadenTask ";
    }
    
    /**
     * 广告线索询盘编辑
     * 
     * @return
     */
    @RequestMapping(value = "/admav/leadgenEdit", method = RequestMethod.POST)
    public String leadgenEdit(@RequestBody ReqLeadgenEdit reqLeadgenEdit) {
        log.info("/admav/leadgenEdit :{}", reqLeadgenEdit);
        Integer id = reqLeadgenEdit.getId();
        String leadStat = reqLeadgenEdit.getLeadStat();
        String flwpStat = reqLeadgenEdit.getFlwpStat();
        try {
            int rows = leadGenService.updateLeadGen(id,leadStat,flwpStat);
            String desc = String.format("add rows %d", rows);
            Response response = new Response(); 
            response.setCode("200");
            response.setResult(desc);
            response.setSuccess(true);
            String srtResponse = JacksonUtils.toJson(response);
            log.info(srtResponse);
            return srtResponse;
        } catch (Exception e) {
            log.error(" update lead gen error", e);
            Response response = new Response(); 
            response.setMessage(e.getMessage());
            response.setSuccess(false);
            String srtResponse = JacksonUtils.toJson(response);
            log.info(srtResponse);
            return srtResponse;
        }
    }
    
    /**
     * 查询分配用户
     */
    @RequestMapping(value = "/admav/queryUser", method = RequestMethod.GET)
    public String leadgenAllocate() {
        log.info("/admav/queryUser leadgenAllocate");
        List<RespSysUser> listRespSysUser = Lists.newArrayList();
        List<SysUser> listSysUser = userService.getUserList();
        for (SysUser sysUser : listSysUser) {
            String userName = sysUser.getUserName();
            RespSysUser respSysUser = new RespSysUser();
            respSysUser.setUserName(userName);
            listRespSysUser.add(respSysUser);
        }
        Response response = new Response();     
        response.setCode("200");
        response.setResult(listRespSysUser);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
    
    /**
     * 广告线索询盘任务分配
     */
    @RequestMapping(value = "/admav/leadgenAllocate", method = RequestMethod.POST)
    public String leadgenAllocate(@RequestBody ReqLeadgenAllocate reqLeadgenAllocate) {
        log.info("/admav/reqLeadgenAllocate :{}", reqLeadgenAllocate);
        Integer id = reqLeadgenAllocate.getId();
        String owner = reqLeadgenAllocate.getUserName();
        int rows = leadGenService.leadgenAllocate(id, owner);
        String desc = String.format("success dd rows %d", rows);
        log.info("add rows {}", rows);
        Response response = new Response();     
        response.setCode("200");
        response.setResult(desc);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
    
    /**
     * 获取用户所有广告账户详情
     * 
     */
    @RequestMapping(value = "/admav/adaccounts", method = RequestMethod.GET)
    public String adaccountDetail(String user) {
        log.info("/admav/adaccountDetail user:{}", user);
        FacebookClient facebookClient = facebookClientService.getClientByUser(user);
        User fbUser = facebookClient.fetchObject("me", User.class);
        List<RespAdaccount> listRespAdaccount = Lists.newArrayList();
        Connection<AdAccount> accounts = facebookClient.fetchConnection(fbUser.getId() + "/adaccounts", AdAccount.class);
        List<AdAccount> listAdAccount = accounts.getData();
        for (AdAccount adAccount : listAdAccount) {
            RespAdaccount respAdaccount = new RespAdaccount();
            // 发起 API 请求
            String apiEndpoint = adAccount.getId();
            String fields = "amount_spent,balance,currency,account_status,id,account_id";
            Parameter parameters = Parameter.with("fields", fields);
            Adaccount account = facebookClient.fetchObject(apiEndpoint, Adaccount.class, parameters);
            // 从响应中提取所需字段
            long amountSpent = account.getAmountSpent();
            long balance = account.getBalance();
            respAdaccount.setAmountSpent(amountSpent);
            respAdaccount.setBalance(balance);
            String currency = account.getCurrency();
            String accountStatus = account.getAccountStatus();
            String id = account.getId();
            String accountId = account.getAccountId();
            respAdaccount.setAccountId(accountId);
            respAdaccount.setId(id);
            respAdaccount.setCurrency(currency);
            respAdaccount.setAccountStatus(accountStatus);
            listRespAdaccount.add(respAdaccount);
        }
        Response response = new Response();     
        response.setCode("200");
        response.setResult(listRespAdaccount);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
}