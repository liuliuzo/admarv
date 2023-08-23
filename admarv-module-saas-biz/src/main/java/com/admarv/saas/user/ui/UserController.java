package com.admarv.saas.user.ui;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.common.UserRoleConstant;
import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.common.dto.req.ReqSubmitFBBind;
import com.admarv.saas.fb.common.dto.resp.RespAdAccount;
import com.admarv.saas.fb.lead.dto.req.ReqAddLeader;
import com.admarv.saas.fb.model.accounts.Accounts;
import com.admarv.saas.fb.model.adaccount.PageData;
import com.admarv.saas.fb.model.adaccount.PromotePages;
import com.admarv.saas.fb.model.adaccounts.AdAccounts;
import com.admarv.saas.fb.model.adaccounts.Data;
import com.admarv.saas.fb.model.insights.Cursors;
import com.admarv.saas.fb.model.insights.Paging;
import com.admarv.saas.fb.model.user.FBUser;
import com.admarv.saas.fb.page.dto.resp.Pages;
import com.admarv.saas.mapper.AdaccountPageInfoMapper;
import com.admarv.saas.mapper.EmailInfoMapper;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.mapper.SysUserMapper;
import com.admarv.saas.mapper.SysUserRoleMapper;
import com.admarv.saas.model.AdaccountPageInfo;
import com.admarv.saas.model.EmailInfo;
import com.admarv.saas.model.SysUser;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.model.SysUserRole;
import com.admarv.saas.utils.JacksonUtils;
import com.google.common.collect.Lists;
import com.restfb.FacebookClient;
import com.restfb.JsonMapper;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;
import com.restfb.types.User;

/**
 * 
 * @author liuliu
 *
 */
@RestController
public class UserController {
    
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private FacebookClientService facebookClientService;
    
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    
    @Autowired
    private AdaccountPageInfoMapper adaccountPageInfoMapper;
    
    @Autowired
    private EmailInfoMapper emailInfoMapper;
    
    
    /**
     * 查询广告账户列表
     * 
     * @param user
     * @return
     */
    @GetMapping("/admarv/adaccount")
    public String adaccount(String userId) {
        log.info("/admarv/adaccount userId:{}", userId);
        List<RespAdAccount> adaccountList = Lists.newArrayList();
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
        log.info("fbClient:{}", facebookClient);
        JsonObject jsonObjectAdaccounts = facebookClient.fetchObject("me/adaccounts", JsonObject.class);
        String srtJsonObjectAdaccounts = jsonMapper.toJson(jsonObjectAdaccounts);
        
        AdAccounts adaccounts = JacksonUtils.fromJson(srtJsonObjectAdaccounts, AdAccounts.class);
        List<Data> listData = adaccounts.getData();
        for (Data data : listData) {
            String id = data.getId();
            String fields = "id,account_id,amount_spent,balance,currency,name,account_status,spend_cap,funding_source_details,promote_pages";
            JsonObject jsonObject = facebookClient.fetchObject(id, JsonObject.class, Parameter.with("fields", fields));
            String srtjson = jsonMapper.toJson(jsonObject);
            log.info("srtjson:{}", srtjson);
            com.admarv.saas.fb.model.adaccount.AdAccount adaccount = JacksonUtils.fromJson(srtjson, com.admarv.saas.fb.model.adaccount.AdAccount.class);
            RespAdAccount respAdAccount = new RespAdAccount();
            respAdAccount.setId(adaccount.getId());
            respAdAccount.setAccountId(adaccount.getAccountId());
            respAdAccount.setName(adaccount.getName());
            respAdAccount.setPromotePages(adaccount.getPromotePages());
            adaccountList.add(respAdAccount);
        }
        Response response = new Response();
        response.setCode("200");
        response.setResult(adaccountList);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
    
    /**
     * 查询广告账户列表
     * 
     * @param user
     * @return
     */
    @GetMapping("/admarv/adaccountPageInfo")
    public String adaccountPageInfo(String userId) {
        List<RespAdAccount> adaccountList = Lists.newArrayList();
        log.info("/admarv/adaccountPageInfo userId:{}", userId);
		AdaccountPageInfo selectAdaccountPageInfo = new AdaccountPageInfo();
		selectAdaccountPageInfo.setUserId(userId);
		List<AdaccountPageInfo> listAdaccountPageInfo = adaccountPageInfoMapper.selectByEntity(selectAdaccountPageInfo);
		for (AdaccountPageInfo adaccountPageInfo : listAdaccountPageInfo) {
			log.info("adaccountPageInfo:{}", adaccountPageInfo);
			String adAccountId = adaccountPageInfo.getAdAccountId(); //"act_"
			String accountName = adaccountPageInfo.getAdAccountName();
			String pageId = adaccountPageInfo.getPageId();
			String pageName = adaccountPageInfo.getPageName();
			RespAdAccount respAdAccount = new RespAdAccount();      
	        respAdAccount.setId(adAccountId);
	        respAdAccount.setName(accountName);
	        PromotePages promotePages = new PromotePages();
	        List<PageData> pageDataList = Lists.newArrayList();
	        PageData pageData = new PageData();
	        pageData.setId(pageId);
	        pageData.setName(pageName);
	        pageDataList.add(pageData);
	        promotePages.setData(pageDataList);
	        respAdAccount.setPromotePages(promotePages);
	        adaccountList.add(respAdAccount);
		}    
        Response response = new Response();
        response.setCode("200");
        response.setResult(adaccountList);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
    
    
    
    /**
     * 查询用户的公共主页
     * @param user
     * @return
     */
    @GetMapping("/admarv/pages")
    public String pages(String userId) {
        log.info("/admarv/pages userId:{}", userId);
        List<Pages> listRespPages = Lists.newArrayList();
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
        // 获取登录用户的信息
        User me = facebookClient.fetchObject("me", User.class);
        // 获取用户管理的所有页面和应用程序
        JsonObject jsonObjectAccounts = facebookClient.fetchObject(me.getId() + "/accounts", JsonObject.class);
        String srtJsonObjectAccounts = jsonMapper.toJson(jsonObjectAccounts);
        Accounts accounts = JacksonUtils.fromJson(srtJsonObjectAccounts, Accounts.class);
        log.info("accounts:{}", accounts);
        List<com.admarv.saas.fb.model.accounts.Data> dataList = accounts.getData();
        for (com.admarv.saas.fb.model.accounts.Data data : dataList) {
            String pageId = data.getId();
            String pageName = data.getName();
            log.info("pageId:{}, pageName:{}", pageId, pageName);
            Pages pages = new Pages();
            pages.setPageId(pageId);
            pages.setPageName(pageName);
            listRespPages.add(pages);
        }
        Paging paging = accounts.getPaging();
        while (paging != null) {
            String after = paging.getCursors().getAfter();
            paging = nextPages(listRespPages, me, facebookClient, after);
            log.info("paging:{}", paging);
        }
        String result = JacksonUtils.toJson(listRespPages);
        log.info("result:{}", result);
        return result;
    }
    
    /**
     * @param listRespPages
     */
    private Paging nextPages(List<Pages> listRespPages, User me, FacebookClient facebookClient, String after) {
        log.info("nextPages after:{}", after);
        JsonMapper jsonMapper = facebookClient.getJsonMapper();
        JsonObject jsonTemp = facebookClient.fetchObject(me.getId() + "/accounts", JsonObject.class, Parameter.with("after", after));
        String srtJsonObjectAccounts = jsonMapper.toJson(jsonTemp);
        Accounts accounts = JacksonUtils.fromJson(srtJsonObjectAccounts, Accounts.class);
        Paging paging = accounts.getPaging();
        if (paging != null) {
            Cursors cursors = paging.getCursors();
            log.info("cursors:{}", cursors);
            List<com.admarv.saas.fb.model.accounts.Data> dataList = accounts.getData();
            for (com.admarv.saas.fb.model.accounts.Data data : dataList) {
                String pageId = data.getId();
                String pageName = data.getName();
                log.info("pageId:{}, pageName:{}", pageId, pageName);
                Pages pages = new Pages();
                pages.setPageId(pageId);
                pages.setPageName(pageName);
                listRespPages.add(pages);
            }
        }
        return paging;
    }
    
	/**
	 * 绑定saas平台用户的facebook公共主页和广告账户
	 * 
	 * @param reqSubmitFBBind
	 * @return
	 */
    @PostMapping("/admarv/editBindFb")
    public String editBindFb(@RequestBody ReqSubmitFBBind reqSubmitFBBind) {
        log.info("/admarv/bindFb reqSubmitFBBind:{}", reqSubmitFBBind);
        String userId = reqSubmitFBBind.getUserID();
        String userName = reqSubmitFBBind.getUserName();
        String adAccountId = reqSubmitFBBind.getAdAccountId();
        String adAccountName = reqSubmitFBBind.getAdAccountName();
        String pageId = reqSubmitFBBind.getPageId();
        String pageName = reqSubmitFBBind.getPageName();
        SysUserFbBind selectEntity = new SysUserFbBind();
        selectEntity.setUserId(userId);
        SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
        if(sysUserFbBind == null) {
            SysUserFbBind insertEntity = new SysUserFbBind();
            insertEntity.setUserId(userId);
            insertEntity.setUserName(userName);
            insertEntity.setAdAccountId(adAccountId);
            insertEntity.setAdAccountName(adAccountName);
            insertEntity.setPageId(pageId);
            insertEntity.setPageName(pageName);
            int row = sysUserFbBindMapper.insert(insertEntity);
            String desc = String.format("add rows %d", row);
            Response response = new Response(); 
            response.setCode("200");
            response.setResult(desc);
            response.setSuccess(true);
            String srtResponse = JacksonUtils.toJson(response);
            log.info(srtResponse);
            return srtResponse;
        } else {
			if (StringUtils.isNotBlank(adAccountId)) {
				sysUserFbBind.setAdAccountId(adAccountId);
			}
			if (StringUtils.isNotBlank(pageId)) {
				sysUserFbBind.setPageId(pageId);
			}
			if (StringUtils.isNotBlank(adAccountName)) {
				sysUserFbBind.setAdAccountName(adAccountName);
			}
			if (StringUtils.isNotBlank(pageName)) {
				sysUserFbBind.setPageName(pageName);
			}
            int row = sysUserFbBindMapper.updateByPrimaryKey(sysUserFbBind);
            String desc = String.format("upate sysUserFbBind rows %d", row);
            Response response = new Response(); 
            response.setCode("200");
            response.setResult(desc);
            response.setSuccess(true);
            String srtResponse = JacksonUtils.toJson(response);
            log.info(srtResponse);
            return srtResponse;
        }
    }
    
    @PostMapping("/admarv/addLeader")
    public String addLeader(@RequestBody ReqAddLeader reqAddLeader) {
        log.info("/admarv/addLeader reqAddLeader:{}", reqAddLeader);
        String userName = reqAddLeader.getUserName();
        String pswrd = reqAddLeader.getPswrd();
        String email = reqAddLeader.getEmail();
        String phone = reqAddLeader.getPhone();
        SysUser insert = new SysUser();
        insert.setUserName(userName);
        String userId = UUID.randomUUID().toString().replaceAll("-", "");
        insert.setUserId(userId);
        insert.setPassword(pswrd);
        insert.setPhone(phone);
        insert.setEmail(email);
        try {
            int row1 = sysUserMapper.insert(insert);
            String strRow1 = String.format("add SysUser rows %d", row1);
            log.info("add SysUser:{}", strRow1);
            SysUserRole sysUserRole = new SysUserRole();
            String uuid2 = UUID.randomUUID().toString().replaceAll("-", "");
            sysUserRole.setId(uuid2);
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(UserRoleConstant.ROLE_LEADER_MGR);
            int row2 = sysUserRoleMapper.insert(sysUserRole);
            String strRow2 = String.format("add SysUserRole rows %d", row2);
            log.info("add SysUserRole:{}", strRow2);
            String result = strRow1 + "\\n" + strRow2;
            log.info("result:{}", result);
            Response response = new Response(); 
            response.setCode("200");
            response.setResult(result);
            response.setSuccess(true);
            String srtResponse = JacksonUtils.toJson(response);
            log.info(srtResponse);
            return srtResponse;
        } catch (Exception e) {
            Response response = new Response(); 
            response.setCode("200");
            response.setResult(null);
            response.setMessage(e.getMessage());
            response.setSuccess(false);
            String srtResponse = JacksonUtils.toJson(response);
            log.info(srtResponse);
            return srtResponse;
        }
    }
    
    /**
     * 获取用户绑定的公共主页和账户
     * 
     * @param userId
     * @return
     */
    @GetMapping("/admarv/getBindFb")
    public String getBindFb(String userId) {
        log.info("/admarv/getBindFb:{}", userId);
        SysUserFbBind selectEntity = new SysUserFbBind();
        selectEntity.setUserId(userId);
        SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
        if (sysUserFbBind == null) {
            Response response = new Response();
            response.setCode("601");
            response.setMessage("此用户未绑定广告账户和公共主页");
            response.setSuccess(false);
            String srtResponse = JacksonUtils.toJson(response);
            log.info(srtResponse);
            return srtResponse;
        } else {
            Response response = new Response();
            response.setCode("200");
            response.setResult(sysUserFbBind);
            response.setSuccess(true);
            String srtResponse = JacksonUtils.toJson(response);
            log.info(srtResponse);
            return srtResponse;
        }
    }
    
    /**
     * 获取用户姓名和头像
     * 
     * @param userId
     * @return
     */
    @GetMapping("/admarv/getFBUserDetail")
    public String getUserDetail(String userId) {
        log.info("/admarv/getUserDetail:{}", userId);
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
        String fields = "id,name,picture";
		JsonObject jsonMe = facebookClient.fetchObject("me", JsonObject.class, Parameter.with("fields", fields));
        String srtJsonMe = jsonMapper.toJson(jsonMe);
        FBUser fbUser = JacksonUtils.fromJson(srtJsonMe, FBUser.class);
        log.info("fbUser:{}", fbUser);
        Response response = new Response();
        response.setCode("200");
        response.setResult(fbUser);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }

    /**
     * 账户中心
     * 
     * @param userId
     * @return
     */
	@GetMapping("/admarv/getAccountInfo")
	public String getAccountInfo(String userId) {
		log.info("/admarv/getAccountInfo:{}", userId);
		EmailInfo selectEmailInfo = new EmailInfo();
		selectEmailInfo.setUserId(userId);
		EmailInfo emailInfo = emailInfoMapper.selectOneByEntity(selectEmailInfo);
		Response response = new Response();
		response.setCode("200");
		response.setResult(emailInfo);
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}
}
