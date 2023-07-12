package com.admarv.saas.fb.ads.task;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.admarv.saas.fb.ads.domain.LeadGenService;
import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.UserService;
import com.admarv.saas.model.LeadGen;
import com.admarv.saas.model.SysUser;
import com.admarv.saas.utils.DateUtils;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.Page;
import com.restfb.types.User;
import com.restfb.types.ads.FieldData;
import com.restfb.types.ads.Lead;
import com.restfb.types.ads.LeadgenForm;

/**
 * 广告线索询盘单同步定时任务
 * 
 * @author liuliu
 *
 */
@Component
public class LeadGenTask {
    
    private static final Logger log = LoggerFactory.getLogger(LeadGenTask.class);
    
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
        log.info("leadenTask start");
        if(!facebookClientService.checkClients()) {
            log.warn("not init oauth task not execute");
            return ;
        }
        // 获取SAAS平台中的所有用户
        List<SysUser> listSysUser = userService.getUserList();
        log.info("listSysUser :{}", listSysUser);
        /**
         * 同步所有用户的广告线索数据
         */
        for (SysUser user : listSysUser) {
            log.info("user:{}", user);
            String userName = user.getUserName();
            FacebookClient facebookClient = facebookClientService.getClientByUser(userName);
            // 获取登录用户的信息
            User me = facebookClient.fetchObject("me", User.class);
            // 获取用户管理的所有页面和应用程序
            Connection<Page> accounts = facebookClient.fetchConnection(me.getId() + "/accounts", Page.class);
            // 遍历所有页面和应用程序
            for (List<Page> pageList : accounts) {
                for (Page page : pageList) {
                    log.info("page:{}", page);
                    String pageId = page.getId();
                    String pageName = page.getName();
                    log.info("pageId:{}, pageName:{}", pageId, pageName);
                    String pageAccessToken = page.getAccessToken();
                    // 获取 page access token的FacebookClient TODO: 待优化
                    FacebookClient pageAccessClient= new DefaultFacebookClient(pageAccessToken, Version.VERSION_17_0);
                    try {
                        String endpoint = "/" + pageId + "/leadgen_forms";
                        Connection<LeadgenForm> leadgenForms = pageAccessClient.fetchConnection(endpoint, LeadgenForm.class);
                        log.info("leadgenForms:{}", leadgenForms);
                        for (List<LeadgenForm> leadgenFormPage : leadgenForms) {
                            for (LeadgenForm leadgenForm : leadgenFormPage) {
                                String formId = leadgenForm.getId();
                                String formNam = leadgenForm.getName();
                                log.info("Form ID:{}, Form Name:{}", formId, formNam);
                                // 获取潜在客户信息
                                String leadsUrl = "/" + formId + "/leads";
                                Connection<Lead> leads = pageAccessClient.fetchConnection(leadsUrl, Lead.class);
                                log.info("leads total count:{}", leads.getTotalCount());
                                for (List<Lead> leadList : leads) {
                                    for (Lead lead : leadList) {
                                        String leadId = lead.getId();
                                        LeadGen leadGen = new LeadGen();
                                        List<FieldData> filedList = lead.getFieldData();
                                        for (FieldData field : filedList) {
                                            String value = field.getValues().get(0);
                                            if ("mobile".equals(field.getName())) {
                                                leadGen.setCntct(value);
                                            }
                                            if ("email".equals(field.getName())) {
                                                leadGen.setEmail(value);
                                            }
                                            if ("full_name".equals(field.getName())) {
                                                leadGen.setName(value);
                                            }
                                            if ("country".equals(field.getName())) {
                                                leadGen.setRegn(value);
                                            }
                                        }
                                        leadGen.setRsrc("FaceBook");
                                        Date crteTmDt = lead.getCreatedTime();
                                        String crteTm = DateUtils.dateToString(crteTmDt, DateUtils.PATTERN_DATETIME);
                                        leadGen.setCrteTm(crteTm);
                                        leadGen.setOwner(DEFAULT_ALLOCATE_USER);
                                        leadGen.setUserName(userName);
                                        leadGen.setLeadId(leadId);
                                        leadGenService.insertLeadGen(leadGen);
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        log.error("task init leads error", e);
                    }
                }
            }
        }
        log.info("leadenTask end");
    }
}