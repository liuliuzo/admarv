package com.admarv.saas.fb.lead.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.lead.domain.LeadGenService;
import com.admarv.saas.fb.lead.domain.model.LeadCust;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.model.LeadGen;
import com.admarv.saas.model.SysUser;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.user.domain.UserService;
import com.admarv.saas.utils.DateUtils;
import com.admarv.saas.utils.JacksonUtils;
import com.google.common.collect.Maps;
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
 * TODO: change to webhook
 * 
 * @author liuliu
 *
 */
@Component
//@EnableScheduling
public class PageLeadGenTask {

	private static final Logger log = LoggerFactory.getLogger(PageLeadGenTask.class);

	private static final Logger unstructured = LoggerFactory.getLogger(PageLeadGenTask.class);

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

	@Autowired
	private SysUserFbBindMapper sysUserFbBindMapper;
	
	/**
	 * 每一分钟获取广告线索数据
	 */
	@Scheduled(cron = "${admarv.lead.cron.expression}")
	public void doLeadGenTask() {
		log.info("leadenTask start");
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
			log.info("SysUser:{}", user);
			String userId = user.getUserId();
			String userName = user.getUserName();
			SysUserFbBind selectEntity = new SysUserFbBind();
			selectEntity.setUserId(userId);
			SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
			if (sysUserFbBind == null) {
				log.warn("sysUserFbBind :{}", sysUserFbBind);
				continue;
			}
			String bindPageId = sysUserFbBind.getPageId();
			FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
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
					if (!bindPageId.equals(pageId)) {
						continue;
					}
					String pageAccessToken = page.getAccessToken();
					// 获取 page access token的FacebookClient TODO: 待优化
					FacebookClient pageAccessClient = new DefaultFacebookClient(pageAccessToken, Version.VERSION_17_0);
					try {
						String endpoint = "/" + pageId + "/leadgen_forms";
						Connection<LeadgenForm> leadgenForms = pageAccessClient.fetchConnection(endpoint,LeadgenForm.class);
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
										log.info("lead:{}", lead);
										Map<String,String> mapOtherField = Maps.newConcurrentMap();
										
										String leadId = lead.getId();
										LeadGen leadGen = new LeadGen();
										LeadCust leadCust = new LeadCust();
										List<FieldData> filedList = lead.getFieldData();
										unstructured.info("filedList:{}", filedList);
										
										for (FieldData field : filedList) {
											List<String> list = field.getValues();
											mapOtherField.put(field.getName(), list.get(0));
											/**
											 * common field
											 */
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
											if ("are_you_a_distributor_or_a_final_customer?".equals(field.getName())) {
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
											
											/**
											 * 集萃表单
											 */
											if ("phone_number".equals(field.getName())) {
												leadCust.setPhoneNumber(list.get(0));
											}
											if ("which_industry_do_you_come_from?".equals(field.getName())) {
												leadCust.setWhichIndustryDoYouComeFrom(list.get(0));
											}
											if ("your_whatsapp".equals(field.getName())) {
												leadCust.setYourWhatsapp(list.get(0));
											}
											if ("which_products_do_you_intersted?".equals(field.getName())) {
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
										leadGen.setUserName(userName);
										leadGen.setLeadId(leadId);
										leadGen.setFormId(formId);
										leadGen.setPageId(pageId);
										// ad info
										leadGen.setAdId(lead.getAdId());
										leadGen.setCampaignId(lead.getCampaignId());
										leadGen.setAdsetId(lead.getAdsetId());
										String customer = JacksonUtils.toJson(leadCust);
										leadGen.setCustomer(customer);

										// other fields
										String srtOtherField = JacksonUtils.toJson(mapOtherField);
										log.info("srtOtherField:{}", srtOtherField);
										leadGen.setOtherFields(srtOtherField);
										try {
											log.info("==leadGen:{}", leadGen);
											leadGen.setCreateBy("PageLeadGenTask");
											leadGenService.insertLeadGen(leadGen);
										} catch (Exception e) {
											errorlog.error("insert lead gen fail", e);
										}
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