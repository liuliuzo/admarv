package com.admarv.saas.fb.lead.ui;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.common.CommonConstant;
import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.PageResponse;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.lead.constant.SourceEnum;
import com.admarv.saas.fb.lead.domain.LeadGenService;
import com.admarv.saas.fb.lead.domain.LeadQueryDO;
import com.admarv.saas.fb.lead.domain.model.LeadCust;
import com.admarv.saas.fb.lead.dto.req.ReqAssignLeadRole;
import com.admarv.saas.fb.lead.dto.req.ReqDeleteLeadRole;
import com.admarv.saas.fb.lead.dto.req.ReqLeadIds;
import com.admarv.saas.fb.lead.dto.req.ReqLeadQuery;
import com.admarv.saas.fb.lead.dto.req.ReqLeadgenAllocate;
import com.admarv.saas.fb.lead.dto.req.ReqLeadgenEdit;
import com.admarv.saas.fb.lead.dto.resp.RespAllUser;
import com.admarv.saas.fb.lead.dto.resp.RespLeadGen;
import com.admarv.saas.fb.lead.dto.resp.RespLeadGenFile;
import com.admarv.saas.fb.lead.task.AdsLeadGenTask;
import com.admarv.saas.fb.lead.task.PageLeadGenTask;
import com.admarv.saas.fb.page.dto.resp.RespSysUser;
import com.admarv.saas.mapper.CustomerInfoMapper;
import com.admarv.saas.model.CustomerInfo;
import com.admarv.saas.model.LeadCntry;
import com.admarv.saas.model.LeadGen;
import com.admarv.saas.model.SysUser;
import com.admarv.saas.user.domain.UserService;
import com.admarv.saas.utils.JacksonUtils;
import com.admarv.saas.utils.ValidationUtils;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.restfb.FacebookClient;

/**
 * 广告询盘页面
 * 
 * @author liuliu
 *
 */
@RestController
public class LeadController {
    
    private static final Logger log = LoggerFactory.getLogger(LeadController.class);
    
    /**
     * 广告线索询盘服务
     */
    @Autowired
    private LeadGenService leadGenService;
    
    @Autowired
    private PageLeadGenTask leadGenTask;
    
    @Autowired
    private AdsLeadGenTask adsLeadGenTask;
    
    @Autowired
    private UserService userService;
    
	@Autowired
	private CustomerInfoMapper customerInfoMapper;
	
    @Autowired
    private FacebookClientService facebookClientService;
    
    @GetMapping("/admarv/leadgenDetail")
    public String leadgenDetail(int leadId) {
        log.info("/admarv/leadgenDetail leadId:{}", leadId);
        LeadGen leadGen = leadGenService.getLeadDetail(leadId);
        RespLeadGen respLeadGen = new RespLeadGen();
		respLeadGen.setCrteTm(leadGen.getCrteTm());
		String email = leadGen.getEmail();
		String customer = leadGen.getCustomer();
		LeadCust leadCust = JacksonUtils.fromJson(customer, LeadCust.class);
		respLeadGen.setFlwpStat(leadGen.getFlwpStat());
		respLeadGen.setLeadStat(leadGen.getLeadStat());
		respLeadGen.setName(leadGen.getName());
		respLeadGen.setRegn(leadGen.getRegn());
		respLeadGen.setRsrc(leadGen.getRsrc());
		respLeadGen.setCampaignId(leadGen.getCampaignId());
		respLeadGen.setAdId(leadGen.getAdId());
		respLeadGen.setAdsetId(leadGen.getAdsetId());
		respLeadGen.setCntct(leadGen.getCntct());
		respLeadGen.setCrteDt(leadGen.getCrteDt());
		respLeadGen.setFormId(leadGen.getFormId());
		respLeadGen.setPageId(leadGen.getPageId());
		respLeadGen.setOwnerId(leadGen.getOwnerId());
		respLeadGen.setOwnerName(leadGen.getOwnerName());
		respLeadGen.setUserId(leadGen.getUserId());
		respLeadGen.setUserName(leadGen.getUserName());
		respLeadGen.setLeadAuality(leadGen.getLeadAuality());
        respLeadGen.setCustomer(leadGen.getCustomer());
        respLeadGen.setDelFlag(leadGen.getDelFlag());
        respLeadGen.setCreateBy(leadGen.getCreateBy());
        respLeadGen.setUpdateBy(leadGen.getUpdateBy());
        respLeadGen.setUpdateTime(leadGen.getUpdateTime());
        respLeadGen.setStartDate(leadGen.getStartDate());
        respLeadGen.setEndDate(leadGen.getEndDate());
		String otherFileds = leadGen.getOtherFields();
		
		Map<String, String> fieldsMap = JacksonUtils.fromJson(otherFileds, Map.class);
        respLeadGen.setOtherFieldsMap(fieldsMap);
        respLeadGen.setCrteTm(leadGen.getCrteTm());
        respLeadGen.setLeadId(leadGen.getLeadId());
        respLeadGen.setEmail(leadGen.getEmail());
        respLeadGen.setId(leadGen.getId());
		respLeadGen.setLeadCust(leadCust);
		
		/**
		 * TODO:因为设计表单不规范的特殊处理 start
		 */
		String yourWhatsapp = leadCust.getYourWhatsapp();
		String whatapp = leadCust.getWhatapp();
		if(StringUtils.isNotBlank(whatapp)) {
			respLeadGen.setWhatsApp(whatapp);
		}
		if(StringUtils.isNotBlank(yourWhatsapp)) {
			respLeadGen.setWhatsApp(yourWhatsapp);
		}
		
		/**
		 * TODO:因为设计表单不规范的特殊处理 end
		 */
		if (ValidationUtils.isValidWhatsappNumber(respLeadGen.getWhatsApp())) {
			respLeadGen.setValidWhatsapp(true);
		}
		
		//检查是否是有效的email
		respLeadGen.setValidEmail(ValidationUtils.isValidEmail(email));
		
        //判断是否归档
        CustomerInfo selectEntity = new CustomerInfo();
        selectEntity.setName(leadGen.getName());
        selectEntity.setEmail(leadGen.getEmail());
		CustomerInfo customerInfo = customerInfoMapper.selectOneByEntity(selectEntity);
		if (customerInfo != null) {
			respLeadGen.setMgrCRM(true);
		}
		
        Response response = new Response();
        response.setCode("200");
        response.setResult(respLeadGen);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
    
    /**
     * 广告线索询盘编辑
     * 
     * @return
     */
    @PostMapping("/admarv/leadgenEdit")
    public String leadgenEdit(@RequestBody ReqLeadgenEdit reqLeadgenEdit) {
        log.info("/admav/leadgenEdit :{}", reqLeadgenEdit);
        Integer id = reqLeadgenEdit.getId();
        String leadStat = reqLeadgenEdit.getLeadStat();
        String flwpStat = reqLeadgenEdit.getFlwpStat();
        String leadAuality = reqLeadgenEdit.getLeadAuality();
        try {
            int rows = leadGenService.updateLeadGen(id,leadStat,flwpStat,leadAuality);
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
     * 查询跟单员角色用户
     */
    @GetMapping("/admarv/queryLeadUser")
    public String queryLeadUser() {
        log.info("/admav/queryLeadUser");
        List<RespSysUser> listRespSysUser = Lists.newArrayList();
        List<SysUser> listSysUser = userService.getLeadgenUsers();
        for (SysUser sysUser : listSysUser) {
            String userName = sysUser.getUserName();
            String userId = sysUser.getUserId();
            RespSysUser respSysUser = new RespSysUser();
            respSysUser.setUserName(userName);
            respSysUser.setUserId(userId);
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
     * 查询跟单员角色用户查询询盘
     */
    @GetMapping(value = "/admarv/queryLeadByUser")
    public String queryLeadByUser(String userId) {
        log.info("/admav/queryLeadByUser:{}", userId);
        List<LeadGen> leadGenList = leadGenService.getLeadGenByOwnerId(userId);
        Response response = new Response();
        response.setCode("200");
        response.setResult(leadGenList);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
    
    /**
     * 查询所有用户
     */
    @GetMapping("/admarv/queryAllUser")
    public String queryAllUser() {
        log.info("/admav/queryAllUser");
        List<RespAllUser> listRespAllUser = Lists.newArrayList();
        List<SysUser> listSysUser = userService.getUserList();
        for (SysUser sysUser : listSysUser) {
            String userId = sysUser.getUserId();
            String userName = sysUser.getUserName();
            RespAllUser respAllUser = new RespAllUser();
            respAllUser.setUserId(userId);
            respAllUser.setUserName(userName);
            listRespAllUser.add(respAllUser);
        }
        Response response = new Response();
        response.setCode("200");
        response.setResult(listRespAllUser);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
    
    /**
     * 分配跟单角色  AssignLeadRole
     */
    @PostMapping("/admarv/assignLeadRole")
    public String assignLeadRole(@RequestBody ReqAssignLeadRole reqAssignLeadRole) {
        log.info("/admav/assignLeadRole:{}", reqAssignLeadRole);
        String userId = reqAssignLeadRole.getUserId();
        int row = userService.assignLeadRole(userId);
        String desc = String.format("add row %d", row);
        Response response = new Response();
        response.setCode("200");
        response.setResult(desc);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
    
    /**
     * 删除询盘跟踪员
     */
    @PostMapping("/admarv/deleteLeadRole")
    public String deleteLeadRole(@RequestBody ReqDeleteLeadRole reqDeleteLeadRole) {
        log.info("/admav/deleteLeadRole:{}", reqDeleteLeadRole);
        String userId = reqDeleteLeadRole.getUserId();
        int row = userService.deleteLeadRole(userId);
        String desc = String.format("add row %d", row);
        Response response = new Response();
        response.setCode("200");
        response.setResult(desc);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
    
    /**
     * 广告线索询盘任务分配
     */
    @PostMapping("/admarv/leadgenAllocate")
    public String leadgenAllocate(@RequestBody ReqLeadgenAllocate reqLeadgenAllocate) {
        log.info("/admav/reqLeadgenAllocate :{}", reqLeadgenAllocate);
        Integer leadId = reqLeadgenAllocate.getId();
        String userId = reqLeadgenAllocate.getUserId();
        int rows = leadGenService.leadgenAllocate(leadId, userId);
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
     * 获取广告线索询盘列表
     * 
     * @param user
     * @return
     */
    @PostMapping("/admarv/leadQuery")
    public String leadQuery(@RequestBody ReqLeadQuery reqLeadQuery) {
        log.info("/admarv/leadgen leadQuery:{}", reqLeadQuery);
        String userId = reqLeadQuery.getUserId();
        
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
        
        String userName = reqLeadQuery.getUserName();
        int pageNum = reqLeadQuery.getPageNum();
        int pageSize = reqLeadQuery.getPageSize();
        String leadAuality = reqLeadQuery.getLeadAuality();
        String flwpStat = reqLeadQuery.getFlwpStat();
        String leadStat = reqLeadQuery.getLeadStat();
        String rsrc = reqLeadQuery.getRsrc();
		
        String cntryCd = reqLeadQuery.getCntryCd();
        String startDate = reqLeadQuery.getStartDate();
        String endDate = reqLeadQuery.getEndDate();
        String ownerId = reqLeadQuery.getOwnerId();
        String ownerName = reqLeadQuery.getOwnerName();
        String name = reqLeadQuery.getName();
        
        //转化为领域对象
        LeadQueryDO leadQueryDO = new LeadQueryDO();
        leadQueryDO.setUserName(userName);
        leadQueryDO.setUserId(userId);
        leadQueryDO.setPageNum(pageNum);
        leadQueryDO.setPageSize(pageSize);
		if (StringUtils.isNotBlank(leadAuality)) {
			leadQueryDO.setLeadAuality(leadAuality);
		}
		if (StringUtils.isNotBlank(flwpStat)) {
	        leadQueryDO.setFlwpStat(flwpStat);
		}
		if (StringUtils.isNotBlank(leadStat)) {
	        leadQueryDO.setLeadStat(leadStat);
		}
		if (StringUtils.isNotBlank(rsrc)) {
			rsrc = SourceEnum.getByCode(rsrc).getValue();
			leadQueryDO.setRsrc(rsrc);
		}
        leadQueryDO.setCntryCd(cntryCd);
        leadQueryDO.setStartDate(startDate);
        leadQueryDO.setEndDate(endDate);
        leadQueryDO.setOwnerId(ownerId);
        leadQueryDO.setOwnerName(ownerName);
        leadQueryDO.setName(name);
        
        PageInfo<LeadGen> leadsPageInfo = leadGenService.getDispLeads(leadQueryDO);
        List<LeadGen> listLeadGen = leadsPageInfo.getList();
        List<RespLeadGen> listRespLeadGen = Lists.newArrayList();
        for (LeadGen leadGen : listLeadGen) {
			String email = leadGen.getEmail();
            String customer = leadGen.getCustomer();
            LeadCust leadCust = JacksonUtils.fromJson(customer, LeadCust.class);
            
            RespLeadGen respLeadGen = new RespLeadGen();
            respLeadGen.setId(leadGen.getId());
            respLeadGen.setCampaignId(leadGen.getCampaignId());
            respLeadGen.setAdId(leadGen.getAdId());
            respLeadGen.setAdsetId(leadGen.getAdsetId());
            respLeadGen.setLeadId(leadGen.getLeadId());
            respLeadGen.setName(leadGen.getName());
            respLeadGen.setEmail(leadGen.getEmail());
            respLeadGen.setCntct(leadGen.getCntct());
            respLeadGen.setCrteTm(leadGen.getCrteTm());
            respLeadGen.setCrteDt(leadGen.getCrteDt());
            respLeadGen.setLeadStat(leadGen.getLeadStat());
            respLeadGen.setRegn(leadGen.getRegn());
            respLeadGen.setFlwpStat(leadGen.getFlwpStat());
            respLeadGen.setRsrc(leadGen.getRsrc());
            respLeadGen.setFormId(leadGen.getFormId());
            respLeadGen.setPageId(leadGen.getPageId());
            respLeadGen.setOwnerId(leadGen.getOwnerId());
            respLeadGen.setOwnerName(leadGen.getOwnerName());
            respLeadGen.setUserId(leadGen.getUserId());
            respLeadGen.setUserName(leadGen.getUserName());
            respLeadGen.setLeadAuality(leadGen.getLeadAuality());
            respLeadGen.setCustomer(leadGen.getCustomer());
            respLeadGen.setDelFlag(leadGen.getDelFlag());
            respLeadGen.setCreateBy(leadGen.getCreateBy());
            respLeadGen.setCrteDt(leadGen.getCrteDt());
            respLeadGen.setUpdateBy(leadGen.getUpdateBy());
            respLeadGen.setUpdateTime(leadGen.getUpdateTime());
            respLeadGen.setStartDate(leadGen.getStartDate());
            respLeadGen.setEndDate(leadGen.getEndDate());
    		String otherFileds = leadGen.getOtherFields();
    		Map<String, String> fieldsMap = JacksonUtils.fromJson(otherFileds, Map.class);
            respLeadGen.setOtherFieldsMap(fieldsMap);
            respLeadGen.setCrteTm(leadGen.getCrteTm());
			respLeadGen.setEmail(email);
            respLeadGen.setFlwpStat(leadGen.getFlwpStat());
            respLeadGen.setLeadStat(leadGen.getLeadStat());
            respLeadGen.setName(leadGen.getName());
            respLeadGen.setRegn(leadGen.getRegn());
            respLeadGen.setRsrc(leadGen.getRsrc());
            respLeadGen.setLeadCust(leadCust);
            //判断是否归档
            CustomerInfo selectEntity = new CustomerInfo();
            selectEntity.setEmail(leadGen.getEmail());
            selectEntity.setCntct(leadGen.getCntct());
            selectEntity.setName(leadGen.getName());
			CustomerInfo customerInfo = customerInfoMapper.selectOneByEntity(selectEntity);
			if (customerInfo != null) {
				respLeadGen.setMgrCRM(true);
			}
			/**
			 * TODO:因为设计表单不规范的特殊处理 start
			 */
			String yourWhatsapp = leadCust.getYourWhatsapp();
			String whatapp = leadCust.getWhatapp();
			if(StringUtils.isNotBlank(yourWhatsapp)) {
				respLeadGen.setWhatsApp(yourWhatsapp);
			}
			if(StringUtils.isNotBlank(whatapp)) {
				respLeadGen.setWhatsApp(whatapp);
			}
			/**
			 * TODO:因为设计表单不规范的特殊处理 end
			 */
			if (ValidationUtils.isValidWhatsappNumber(respLeadGen.getWhatsApp())) {
				respLeadGen.setValidWhatsapp(true);
			}
			respLeadGen.setValidEmail(ValidationUtils.isValidEmail(email));
			
            listRespLeadGen.add(respLeadGen);
        }

        PageResponse response = new PageResponse();     
        response.setCode("200");
        response.setResult(listRespLeadGen);
        response.setSuccess(true);
        response.setPageSize(leadsPageInfo.getPageSize());
        response.setPageNum(leadsPageInfo.getPageNum());
        response.setTotalSize(leadsPageInfo.getTotal());
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
    
    /**
     * 刷新公共主页广告线索询盘数据
     * 
     * @return
     */
    @GetMapping("/admarv/refreshAdsLeadgen")
    public String refreshAdsLeadgen() {
        log.info("/admarv/refreshAdsLeadgen");
        adsLeadGenTask.doLeadGenTask();
        return "success refresh adsLeadGenTask ";
    }
    
    /**
     * 刷新广告线索询盘数据
     * 
     * @return
     */
    @GetMapping("/admarv/refreshLeadgen")
    public String refreshLeadgen() {
        log.info("/admarv/refreshLeadgen");
        leadGenTask.doLeadGenTask();
        return "success refresh leadenTask ";
    }
    
    /**
     * 生成询盘文件
     * 
     * @param user
     * @return
     */
    @PostMapping("/admarv/downLoadLeadFile")
    public ResponseEntity<FileSystemResource> downLoadLeadFile(@RequestBody ReqLeadQuery reqLeadQuery) {
        log.info("/admarv/downLoadLeadFile leadQuery:{}", reqLeadQuery);
        String userId = reqLeadQuery.getUserId();
        String userName = reqLeadQuery.getUserName();
        
        int pageNum = reqLeadQuery.getPageNum();
        int pageSize = reqLeadQuery.getPageSize();
        String leadAuality = reqLeadQuery.getLeadAuality();
        String flwpStat = reqLeadQuery.getFlwpStat();
        String leadStat = reqLeadQuery.getLeadStat();
        String rsrc = reqLeadQuery.getRsrc();
        String cntryCd = reqLeadQuery.getCntryCd();
        String startDate = reqLeadQuery.getStartDate();
        String endDate = reqLeadQuery.getEndDate();
        String ownerId = reqLeadQuery.getOwnerId();
        String ownerName = reqLeadQuery.getOwnerName();
        
        //转化为领域对象
        LeadQueryDO leadQueryDO = new LeadQueryDO();
        leadQueryDO.setUserName(userName);
        leadQueryDO.setUserId(userId);
        leadQueryDO.setPageNum(pageNum);
        leadQueryDO.setPageSize(pageSize);
        
		if (StringUtils.isNotBlank(leadAuality)) {
			leadQueryDO.setLeadAuality(leadAuality);
		}
		if (StringUtils.isNotBlank(flwpStat)) {
	        leadQueryDO.setFlwpStat(flwpStat);
		}
		if (StringUtils.isNotBlank(leadStat)) {
	        leadQueryDO.setLeadStat(leadStat);
		}
		if (StringUtils.isNotBlank(rsrc)) {
			rsrc = SourceEnum.getByCode(rsrc).getValue();
			leadQueryDO.setRsrc(rsrc);
		}
		
        leadQueryDO.setCntryCd(cntryCd);
        leadQueryDO.setStartDate(startDate);
        leadQueryDO.setEndDate(endDate);
        leadQueryDO.setOwnerId(ownerId);
        leadQueryDO.setOwnerName(ownerName);
        
        PageInfo<LeadGen> leadsPageInfo = leadGenService.getDispLeads(leadQueryDO);
        List<LeadGen> listLeadGen = leadsPageInfo.getList();
        List<RespLeadGenFile> listRespLeadGenFile = Lists.newArrayList();
        for (LeadGen leadGen : listLeadGen) {
            RespLeadGenFile respLeadGenFile = new RespLeadGenFile();
            respLeadGenFile.setCrteTm(leadGen.getCrteTm());
            respLeadGenFile.setEmail(leadGen.getEmail());
            respLeadGenFile.setNam(leadGen.getName());
            respLeadGenFile.setRegn(leadGen.getRegn());
            respLeadGenFile.setRsrc(leadGen.getRsrc());
            String customer = leadGen.getCustomer();
            LeadCust leadCust = JacksonUtils.fromJson(customer, LeadCust.class);
            respLeadGenFile.setJobTitle(leadCust.getJobTitle());
            respLeadGenFile.setDistributorOrCustomer(leadCust.getDistributorOrCustomer());
            respLeadGenFile.setQuantityRequired(leadCust.getQuantityRequired());
            respLeadGenFile.setWhatapp(leadCust.getWhatapp());
            respLeadGenFile.setYourCompanyName(leadCust.getYourCompanyName());
            respLeadGenFile.setYourScrewSpecifications(leadCust.getYourScrewSpecifications());
            respLeadGenFile.setPhoneNumber(leadCust.getPhoneNumber());
            respLeadGenFile.setWhichIndustryDoYouComeFrom(leadCust.getWhichIndustryDoYouComeFrom());
            respLeadGenFile.setYourWhatsapp(leadCust.getYourWhatsapp());
            respLeadGenFile.setWhichProductsDoYouIntersted(leadCust.getWhichProductsDoYouIntersted());
            listRespLeadGenFile.add(respLeadGenFile);
        }
        
        String fileName = CommonConstant.LEAD_FILE_PATH + "_" + userId + "_" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, RespLeadGenFile.class).sheet("FB询盘").doWrite(listRespLeadGenFile);
        File file = new File(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        FileSystemResource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    
    /**
     * 通过询盘ID生成询盘文件
     * 
     * @param user
     * @return
     */
    @PostMapping("/admarv/downLoadLeadFileByIds")
    public ResponseEntity<FileSystemResource> downLoadLeadFileByIds(@RequestBody ReqLeadIds reqLeadIds) {
        log.info("/admarv/downLoadLeadFileByIds reqLeadIds:{}", reqLeadIds);
        List<Integer> ids = reqLeadIds.getIds();
        String userId = reqLeadIds.getUserId();
        List<LeadGen> listLeadGen = leadGenService.getLeadByIds(ids);
        List<RespLeadGenFile> listRespLeadGenFile = Lists.newArrayList();
        for (LeadGen leadGen : listLeadGen) {
            RespLeadGenFile respLeadGenFile = new RespLeadGenFile();
            respLeadGenFile.setCrteTm(leadGen.getCrteTm());
            respLeadGenFile.setEmail(leadGen.getEmail());
            respLeadGenFile.setNam(leadGen.getName());
            respLeadGenFile.setRegn(leadGen.getRegn());
            respLeadGenFile.setRsrc(leadGen.getRsrc());
            String customer = leadGen.getCustomer();
            LeadCust leadCust = JacksonUtils.fromJson(customer, LeadCust.class);
            respLeadGenFile.setJobTitle(leadCust.getJobTitle());
            respLeadGenFile.setDistributorOrCustomer(leadCust.getDistributorOrCustomer());
            respLeadGenFile.setQuantityRequired(leadCust.getQuantityRequired());
            respLeadGenFile.setWhatapp(leadCust.getWhatapp());
            respLeadGenFile.setYourCompanyName(leadCust.getYourCompanyName());
            respLeadGenFile.setYourScrewSpecifications(leadCust.getYourScrewSpecifications());
            respLeadGenFile.setPhoneNumber(leadCust.getPhoneNumber());
            respLeadGenFile.setWhichIndustryDoYouComeFrom(leadCust.getWhichIndustryDoYouComeFrom());
            respLeadGenFile.setYourWhatsapp(leadCust.getYourWhatsapp());
            respLeadGenFile.setWhichProductsDoYouIntersted(leadCust.getWhichProductsDoYouIntersted());
            listRespLeadGenFile.add(respLeadGenFile);
        }
        String fileName = CommonConstant.LEAD_FILE_PATH + "_" + userId + "_" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, RespLeadGenFile.class).sheet("FB询盘").doWrite(listRespLeadGenFile);
        File file = new File(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        FileSystemResource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    
    
    /**
     * 获取TOP国家
     * 
     * @return
     */
    @GetMapping("/admarv/topRegn")
    public String topRegn(int top) {
        log.info("/admarv/topRegn top:{}", top);
        List<LeadCntry> listLeadCntry = leadGenService.countInquiriesByRegn(top);
        Response response = new Response();
        response.setCode("200");
        response.setResult(listLeadCntry);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
}