package com.admarv.saas.sync.adaccount.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.lead.domain.model.AdAccount;
import com.admarv.saas.mapper.AdaccountDetailMapper;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.model.AdaccountDetail;
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
public class FBSyncAdaccountDetailController {

	private static final Logger log = LoggerFactory.getLogger(FBSyncAdaccountDetailController.class);
	
	@Autowired
    private FacebookClientService facebookClientService;
	
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;
    
    @Autowired
    private AdaccountDetailMapper adaccountDetailMapper;
    
	@RequestMapping(value = "/admarv/refreshAdaccountDetail", method = RequestMethod.GET)
	public String refreshAdaccountDetail(String userId) {
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
        log.info("adaccount:{}", adaccount);
        
        String id = adaccount.getId();				      //"id": "act_884306296107034"
        //String accountId = adaccount.getAccountId();    //"account_id": "884306296107034"
        String amountSpent = adaccount.getAmountSpent();
        String balance = adaccount.getBalance();
        String currency = adaccount.getCurrency();
        String accountStatus = adaccount.getAccountStatus();
        String spendCap =adaccount.getSpendCap();
        
        AdaccountDetail insert = new AdaccountDetail();
        insert.setAccountId(id);
        insert.setAmountSpent(amountSpent);
        insert.setBalance(balance);
        insert.setCurrency(currency);
        insert.setAccountStatus(accountStatus);
        insert.setSpendCap(spendCap);
        insert.setUserId(userId);
        
		int row = adaccountDetailMapper.insert(insert);
		log.info("insert AdaccountDetail success row:{}", row);
		return "SUCCESS refreshAdaccountDetail";
	}
	
}
