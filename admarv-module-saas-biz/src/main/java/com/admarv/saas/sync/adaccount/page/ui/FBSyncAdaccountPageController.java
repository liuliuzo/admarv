package com.admarv.saas.sync.adaccount.page.ui;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.model.adaccount.PageData;
import com.admarv.saas.fb.model.adaccount.PromotePages;
import com.admarv.saas.fb.model.adaccounts.AdAccounts;
import com.admarv.saas.fb.model.adaccounts.Data;
import com.admarv.saas.mapper.AdaccountPageInfoMapper;
import com.admarv.saas.model.AdaccountPageInfo;
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
public class FBSyncAdaccountPageController {

	private static final Logger log = LoggerFactory.getLogger(FBSyncAdaccountPageController.class);
	
	@Autowired
	private AdaccountPageInfoMapper adaccountPageInfoMapper;
	
    @Autowired
    private FacebookClientService facebookClientService;
	
	@RequestMapping(value = "/admarv/refreshAdaccountPage", method = RequestMethod.GET)
	public String refreshAds(String userId) {
		log.info("/admarv/refreshAdaccountPage userId:{}", userId);
		
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
            String adaccountJson = jsonMapper.toJson(jsonObject);
            log.info("srtjson:{}", adaccountJson);
            com.admarv.saas.fb.model.adaccount.AdAccount adaccount = JacksonUtils.fromJson(adaccountJson, com.admarv.saas.fb.model.adaccount.AdAccount.class);
            String adAccountId = adaccount.getId();
			String adAccountName = adaccount.getName();
			PromotePages promotePages = adaccount.getPromotePages();
			if (promotePages == null) {
				AdaccountPageInfo adaccountPageInfo = new AdaccountPageInfo();
				adaccountPageInfo.setAdAccountId(adAccountId);
				adaccountPageInfo.setAdAccountName(adAccountName);
				adaccountPageInfo.setUserId(userId);
				int row = adaccountPageInfoMapper.insert(adaccountPageInfo);
				log.info("insert AdaccountPageInfo adaccountPageInfo:{}, row:{}", adaccountPageInfo, row);
			} else {
				List<PageData> listPromotePages = promotePages.getData();
				if (!CollectionUtils.isEmpty(listPromotePages)) {
					for (PageData pageData : listPromotePages) {
						String pageId = pageData.getId();
						String pageName = pageData.getName();
						AdaccountPageInfo adaccountPageInfo = new AdaccountPageInfo();
						adaccountPageInfo.setAdAccountId(adAccountId);
						adaccountPageInfo.setAdAccountName(adAccountName);
						adaccountPageInfo.setPageId(pageId);
						adaccountPageInfo.setPageName(pageName);
						adaccountPageInfo.setUserId(userId);
						int row = adaccountPageInfoMapper.insert(adaccountPageInfo);
						log.info("insert AdaccountPageInfo adaccountPageInfo:{}, row:{}", adaccountPageInfo, row);
					}
				}
			}
        }
		return "SUCCESS refreshAdaccountPage";
	}
}
