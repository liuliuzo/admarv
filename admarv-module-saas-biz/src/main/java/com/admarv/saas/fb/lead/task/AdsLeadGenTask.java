package com.admarv.saas.fb.lead.task;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.lead.domain.LeadGenService;
import com.admarv.saas.fb.lead.domain.model.LeadCust;
import com.admarv.saas.fb.model.adaccounts.AdAccounts;
import com.admarv.saas.fb.model.adaccounts.Data;
import com.admarv.saas.model.LeadGen;
import com.admarv.saas.model.SysUser;
import com.admarv.saas.user.domain.UserService;
import com.admarv.saas.utils.DateUtils;
import com.admarv.saas.utils.JacksonUtils;
import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.JsonMapper;
import com.restfb.json.JsonObject;
import com.restfb.types.User;
import com.restfb.types.ads.Ad;
import com.restfb.types.ads.FieldData;
import com.restfb.types.ads.Lead;

/**
 * 广告线索询盘单同步定时任务
 * 
 * TODO: change to webhook
 * 
 * @author liuliu
 *
 */
@Component
//@EnableScheduling
public class AdsLeadGenTask {

    private static final Logger log = LoggerFactory.getLogger(AdsLeadGenTask.class);
    
    private static final Logger unstructured = LoggerFactory.getLogger(AdsLeadGenTask.class);
    
    private static final Logger errorlog = LoggerFactory.getLogger("ERROR");

    private static final String DEFAULT_ALLOCATE_USER = "ALL";
    

    @Autowired
    private UserService userService;

    @Autowired
    private FacebookClientService facebookClientService;

    /**
     * 广告线索询盘服务
     */
    @Autowired
    private LeadGenService leadGenService;
    
    /**
     * 每一分钟获取广告线索数据
     */
    @Scheduled(cron = "${admarv.lead.cron.expression}")
    public void doLeadGenTask() {
        log.info("AdsLeadGen start");
        if (!facebookClientService.checkClients()) {
            log.warn("not init oauth clients task not execute");
            return;
        }
        
        // 获取SAAS平台中的所有用户
        List<SysUser> listSysUser = userService.getUserList();
        log.info("listSysUser :{}", listSysUser);
        
        /**
         * 同步所有用户的广告线索数据
         */
        for (SysUser user : listSysUser) {
            log.info("user:{}", user);
            String userId = user.getUserId();
            String userName = user.getUserName();
            
            if(!userService.getUserIsAuthedFB(userId)) {
                continue;
            }
            
            FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
            // 获取登录用户的信息
            User me = facebookClient.fetchObject("me", User.class);
            // 获取广告账户
            JsonMapper jsonMapper = facebookClient.getJsonMapper();
            log.info("fbClient:{}", facebookClient);
            JsonObject jsonObjectAdaccounts = facebookClient.fetchObject("me/adaccounts", JsonObject.class);
            String srtJsonObjectAdaccounts = jsonMapper.toJson(jsonObjectAdaccounts);
            AdAccounts adaccounts = JacksonUtils.fromJson(srtJsonObjectAdaccounts, AdAccounts.class);
            List<Data> listData = adaccounts.getData();
            for (Data data : listData) {
                String id = data.getId();
                Connection<Ad> accounts = facebookClient.fetchConnection(id + "/ads", Ad.class);
                for (List<Ad> AdList : accounts) {
                    for (Ad ad : AdList) {
                        String adId = ad.getId();
                        String leadsUrl = "/" + adId + "/leads";
                        Connection<Lead> leads = facebookClient.fetchConnection(leadsUrl, Lead.class);
                        log.info("leads total count:{}", leads.getTotalCount());
                        for (List<Lead> leadList : leads) {
                            for (Lead lead : leadList) {
                            	StringBuffer sbOtherField = new StringBuffer();
                                log.info("lead:{}", lead);
                                
								String fromId = lead.getFormId();
								log.info("fromId:{}", fromId);

                                String leadId = lead.getId();
                                LeadGen leadGen = new LeadGen();
                                LeadCust leadCust = new LeadCust();
                                List<FieldData> filedList = lead.getFieldData();
                                unstructured.info("filedList:{}", filedList);
                                for (FieldData field : filedList) {
                                    List<String> list = field.getValues();
                                    
                                    sbOtherField.append(field.getName() + ":" + list.get(0));
                                    
                                    if ("mobile".equals(field.getName())) {
                                        leadGen.setCntct(list.get(0));
                                    }
                                    if ("email".equals(field.getName())) {
                                        leadGen.setEmail(list.get(0));
                                    }
                                    if ("full_name".equals(field.getName())) {
                                        leadGen.setName(list.get(0));
                                    }
                                    if ("country".equals(field.getName())) {
                                        leadGen.setRegn(list.get(0));
                                    }
                                    
                                    /**
                                     * 客户化表单
                                     */
                                    if ("job_title".equals(field.getName())) {
                                        leadCust.setJobTitle(list.get(0));
                                    }
                                    if ("are_you_a_distributor_or_a_final_customer".equals(field.getName())) {
                                        leadCust.setDistributorOrCustomer(list.get(0));
                                    }
                                    if ("your_screw_specifications".equals(field.getName())) {
                                        leadCust.setYourScrewSpecifications(list.get(0));
                                    }
                                    if ("your_company_name".equals(field.getName())) {
                                        leadCust.setYourCompanyName(list.get(0));
                                    }
                                    if ("quantity_required".equals(field.getName())) {
                                        leadCust.setQuantityRequired(list.get(0));
                                    }
                                    if ("whatsapp".equals(field.getName())) {
                                        leadCust.setWhatapp(list.get(0));
                                    }
                                    
                                    // 另外一份表单
                                    if ("phone_number".equals(field.getName())) {
                                        leadCust.setPhoneNumber(list.get(0));
                                    }
                                    if ("which_industry_do_you_come_from".equals(field.getName())) {
                                        leadCust.setWhichIndustryDoYouComeFrom(list.get(0));
                                    }
                                    if ("your_whatsapp".equals(field.getName())) {
                                        leadCust.setYourWhatsapp(list.get(0));
                                    }
                                    if ("which_products_do_you_intersted".equals(field.getName())) {
                                        leadCust.setWhichProductsDoYouIntersted(list.get(0));
                                    }
                                }
                                
                                leadGen.setRsrc("FaceBook");
                                Date crteTmDt = lead.getCreatedTime();
                                String crteTm = DateUtils.dateToString(crteTmDt, DateUtils.PATTERN_DATETIME);
                                leadGen.setCrteTm(crteTm);
                                String crteDt = DateUtils.dateToString(crteTmDt, DateUtils.PATTERN_DEFAULT);
                                leadGen.setCrteDt(crteDt);
                                leadGen.setOwnerName(DEFAULT_ALLOCATE_USER);
                                leadGen.setOwnerId(DEFAULT_ALLOCATE_USER);
                                leadGen.setUserId(userId);
                                leadGen.setLeadId(leadId);
                                leadGen.setUserName(userName);
                                // ad info
                                leadGen.setAdId(lead.getAdId());
                                leadGen.setCampaignId(lead.getCampaignId());
                                leadGen.setAdsetId(lead.getAdsetId());
                                String customer = JacksonUtils.toJson(leadCust);
                                leadGen.setCustomer(customer);
                                
								// other fields
								String srtOtherField = sbOtherField.toString();
								log.info("srtOtherField:{}", srtOtherField);
								leadGen.setOtherFields(srtOtherField);
								leadGen.setCreateBy("AdsLeadGenTask");
                                
                                log.info("==leadGen:{}", leadGen);
								try {
									leadGenService.insertLeadGen(leadGen);
								} catch (Exception e) {
									errorlog.error("insert lead gen fail", e);
								}
                            }
                        }
                    }
                    log.info("AdsLeadGen end");
                }
            }
        }
    }
}