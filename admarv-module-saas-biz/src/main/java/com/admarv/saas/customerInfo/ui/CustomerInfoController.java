package com.admarv.saas.customerInfo.ui;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.common.constant.GenderEnum;
import com.admarv.saas.customerInfo.dto.req.ReqEditCustomerInfo;
import com.admarv.saas.customerInfo.dto.req.ReqQueryCustomerInfo;
import com.admarv.saas.customerInfo.dto.req.ReqSaveCustomerInfo;
import com.admarv.saas.customerInfo.dto.req.ReqSelectCustomerInfo;
import com.admarv.saas.customerInfo.dto.resp.RespCustomerInfo;
import com.admarv.saas.fb.common.PageResponse;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.mapper.CustomerInfoMapper;
import com.admarv.saas.model.CustomerInfo;
import com.admarv.saas.utils.JacksonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author liuliu
 *
 */
@RestController
public class CustomerInfoController {

	private static final Logger log = LoggerFactory.getLogger(CustomerInfoController.class);
	
	@Autowired
	private CustomerInfoMapper customerInfoMapper;
	
	/**
	 * 按条件查询归档客户信息
	 * 
	 * @param reqSelectCustomerInfo
	 * @return
	 */
	@RequestMapping(value = "/admarv/selectCustomerInfo", method = RequestMethod.POST)
	public String selectCustomerInfo(@RequestBody ReqSelectCustomerInfo reqSelectCustomerInfo) {
		log.info("/admarv/selectCustomerInfo reqSelectCustomerInfo:{}", reqSelectCustomerInfo);
		String ownerId = reqSelectCustomerInfo.getOwnerId();
		String whatsapp = reqSelectCustomerInfo.getWhatsapp();
		String email = reqSelectCustomerInfo.getEmail();
		String name = reqSelectCustomerInfo.getName();
		String cntct = reqSelectCustomerInfo.getCntct();
		
		CustomerInfo selectEntity = new CustomerInfo();
		selectEntity.setOwnerId(ownerId);
		selectEntity.setWhatsapp(whatsapp);
		selectEntity.setEmail(email);
		selectEntity.setName(name);
		selectEntity.setCntct(cntct);
		
		CustomerInfo customerInfo = customerInfoMapper.selectOneByEntity(selectEntity);
		log.info("customerInfo:{}", customerInfo);
		
		Response response = new Response();
		response.setCode("200");
		response.setResult(customerInfo);
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}
	
	
	@RequestMapping(value = "/admarv/queryCustomerInfo", method = RequestMethod.POST)
	public String queryCustomerInfo(@RequestBody ReqQueryCustomerInfo reqCustomerInfo) {
		log.info("/admarv/queryCustomerInfo reqCustomerInfo:{}", reqCustomerInfo);
		String ownerId = reqCustomerInfo.getOwnerId();
		String name = reqCustomerInfo.getName();
		String email = reqCustomerInfo.getEmail();
		String cntct = reqCustomerInfo.getCntct();
		String whats = reqCustomerInfo.getWhatsapp();
		
		int pageSize = reqCustomerInfo.getPageSize();
		int pageNum = reqCustomerInfo.getPageNum();
		CustomerInfo selectEntity = new CustomerInfo();
		selectEntity.setOwnerId(ownerId);
		selectEntity.setName(name);
		selectEntity.setEmail(email);
		selectEntity.setCntct(cntct);
		selectEntity.setWhatsapp(whats);
		
		PageHelper.startPage(pageNum, pageSize);
		PageHelper.orderBy("create_time DESC");
		List<CustomerInfo> listCustomerInfo = customerInfoMapper.selectByEntity(selectEntity);
		log.info("listCustomerInfo:{}", listCustomerInfo);
		PageInfo<CustomerInfo> pageinfo = new PageInfo<CustomerInfo>(listCustomerInfo);
		log.info("pageinfo:{}", pageinfo);
		PageResponse response = new PageResponse();
		response.setCode("200");
		response.setResult(listCustomerInfo);
		response.setPageSize(pageinfo.getPageSize());
		response.setPageNum(pageinfo.getPageNum());
		response.setTotalSize(pageinfo.getTotal());
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}
	
	@RequestMapping(value = "/admarv/customerInfoDetails", method = RequestMethod.GET)
	public String customerInfoDetails(int custId) {
		log.info("/admarv/customerInfoDetails custId:{}", custId);
		CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(custId);
		log.info("customerInfo:{}", customerInfo);
		
		RespCustomerInfo respCustomerInfo = new RespCustomerInfo();
		respCustomerInfo.setAddress(customerInfo.getAddress());
		respCustomerInfo.setAssignedTo(customerInfo.getAssignedTo());
		respCustomerInfo.setBirthday(customerInfo.getBirthday());
		respCustomerInfo.setBusinessScope(customerInfo.getBusinessScope());
		respCustomerInfo.setCntct(customerInfo.getCntct());
		respCustomerInfo.setCompanyEmail(customerInfo.getCompanyEmail());
		respCustomerInfo.setCompanyName(customerInfo.getCompanyName());
		respCustomerInfo.setCompanySize(customerInfo.getCompanySize());
		respCustomerInfo.setCountry(customerInfo.getCountry());
		respCustomerInfo.setCreateBy(customerInfo.getCreateBy());
		respCustomerInfo.setCreateTime(customerInfo.getCreateTime());
		respCustomerInfo.setCustomerQuality(customerInfo.getCustomerQuality());
		respCustomerInfo.setCustomerSource(customerInfo.getCustomerSource());
		respCustomerInfo.setCustomerStatus(customerInfo.getCustomerStatus());
		respCustomerInfo.setDelFlag(customerInfo.getDelFlag());
		respCustomerInfo.setEmail(customerInfo.getEmail());
		respCustomerInfo.setFacebook(customerInfo.getFacebook());
		respCustomerInfo.setFixedPhone(customerInfo.getFixedPhone());
		String gender = customerInfo.getGender();
		
		GenderEnum genderEnum = GenderEnum.getByCode(gender);
		respCustomerInfo.setGender(genderEnum.getValue());
		
		respCustomerInfo.setId(customerInfo.getId());
		respCustomerInfo.setIndustry(customerInfo.getIndustry());
		respCustomerInfo.setLastContactTime(customerInfo.getLastContactTime());
		respCustomerInfo.setLinkedin(customerInfo.getLinkedin());
		respCustomerInfo.setName(customerInfo.getName());
		respCustomerInfo.setOtherRemarks(customerInfo.getOtherRemarks());
		respCustomerInfo.setOwnerId(customerInfo.getOwnerId());
		respCustomerInfo.setOwnerName(customerInfo.getOwnerName());
		respCustomerInfo.setPhoneNumber(customerInfo.getPhoneNumber());
		respCustomerInfo.setPosition(customerInfo.getPosition());
		respCustomerInfo.setSkype(customerInfo.getSkype());
		respCustomerInfo.setTwitter(customerInfo.getTwitter());
		respCustomerInfo.setUpdateBy(customerInfo.getUpdateBy());
		respCustomerInfo.setUpdateTime(customerInfo.getUpdateTime());
		respCustomerInfo.setWebsite(customerInfo.getWebsite());
		respCustomerInfo.setWhatsapp(customerInfo.getWhatsapp());
		
		Response response = new Response();
		response.setCode("200");
		response.setResult(respCustomerInfo);
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}

	@RequestMapping(value = "/admarv/saveCustomerInfo", method = RequestMethod.POST)
	public String saveCustomerInfo(@RequestBody ReqSaveCustomerInfo reqSaveCustomerInfo) {
		log.info("/admarv/saveCustomerInfo reqSaveCustomerInfo:{}", reqSaveCustomerInfo);
		String name = reqSaveCustomerInfo.getName();
		String email = reqSaveCustomerInfo.getEmail();
		String cntct = reqSaveCustomerInfo.getCntct();
		String userId = reqSaveCustomerInfo.getOwnerId();
		String userName = reqSaveCustomerInfo.getName();
		String country = reqSaveCustomerInfo.getCountry();
		String whatsapp = reqSaveCustomerInfo.getWhatsapp();
		
		CustomerInfo insertEntity = new CustomerInfo();
		insertEntity.setName(name);
		insertEntity.setEmail(email);
		insertEntity.setCntct(cntct);
		insertEntity.setOwnerId(userId);
		insertEntity.setOwnerName(userName);
		insertEntity.setLastContactTime(new Date());
		insertEntity.setCountry(country);
		insertEntity.setWhatsapp(whatsapp);
		
		try {
			int row = customerInfoMapper.insert(insertEntity);
			log.info("insert customer info info row:{}", row);
			String desc = String.format("add row %d", row);
			Response response = new Response();
			response.setCode("200");
			response.setResult(desc);
			response.setSuccess(true);
			String srtResponse = JacksonUtils.toJson(response);
			log.info(srtResponse);
			return srtResponse;
		} catch (Exception e) {
			log.error("insert customer Info error", e);
			Response response = new Response();
			response.setCode("602");
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			String srtResponse = JacksonUtils.toJson(response);
			log.info(srtResponse);
			return srtResponse;
		}
	}
	
	@RequestMapping(value = "/admarv/editCustomerInfo", method = RequestMethod.POST)
	public String editCustomerInfo(@RequestBody ReqEditCustomerInfo reqEditCustomerInfo) {
		log.info("/admarv/editCustomerInfo reqEditCustomerInfo:{}", reqEditCustomerInfo); 
		int id = reqEditCustomerInfo.getId();
		CustomerInfo customerInfoUpdate = customerInfoMapper.selectByPrimaryKey(id);
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getPhoneNumber())) {
			customerInfoUpdate.setPhoneNumber(reqEditCustomerInfo.getPhoneNumber());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getWhatsapp())) {
			customerInfoUpdate.setWhatsapp(reqEditCustomerInfo.getWhatsapp());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getTwitter())) {
			customerInfoUpdate.setTwitter(reqEditCustomerInfo.getTwitter());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getSkype())) {
			customerInfoUpdate.setSkype(reqEditCustomerInfo.getSkype());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getProductRange())) {
			customerInfoUpdate.setProductRange(reqEditCustomerInfo.getProductRange());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getWebsite())) {
			customerInfoUpdate.setWebsite(reqEditCustomerInfo.getWebsite());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getAddress())) {
			customerInfoUpdate.setAddress(reqEditCustomerInfo.getAddress());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getAssignedTo())) {
			customerInfoUpdate.setAssignedTo(reqEditCustomerInfo.getAssignedTo());
		}
		if (reqEditCustomerInfo.getBirthday() != null) {
			customerInfoUpdate.setBirthday(reqEditCustomerInfo.getBirthday());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getBusinessScope())) {
			customerInfoUpdate.setBusinessScope(reqEditCustomerInfo.getBusinessScope());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getCntct())) {
		    customerInfoUpdate.setCntct(reqEditCustomerInfo.getCntct());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getCompanyEmail())) {
		    customerInfoUpdate.setCompanyEmail(reqEditCustomerInfo.getCompanyEmail());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getCompanyName())) {
		    customerInfoUpdate.setCompanyName(reqEditCustomerInfo.getCompanyName());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getCompanySize())) {
		    customerInfoUpdate.setCompanySize(reqEditCustomerInfo.getCompanySize());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getCountry())) {
		    customerInfoUpdate.setCountry(reqEditCustomerInfo.getCountry());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getCustomerQuality())) {
		    customerInfoUpdate.setCustomerQuality(reqEditCustomerInfo.getCustomerQuality());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getCustomerSource())) {
		    customerInfoUpdate.setCustomerSource(reqEditCustomerInfo.getCustomerSource());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getCustomerStatus())) {
		    customerInfoUpdate.setCustomerStatus(reqEditCustomerInfo.getCustomerStatus());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getEmail())) {
		    customerInfoUpdate.setEmail(reqEditCustomerInfo.getEmail());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getFacebook())) {
		    customerInfoUpdate.setFacebook(reqEditCustomerInfo.getFacebook());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getFixedPhone())) {
		    customerInfoUpdate.setFixedPhone(reqEditCustomerInfo.getFixedPhone());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getGender())) {
		    customerInfoUpdate.setGender(reqEditCustomerInfo.getGender());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getIndustry())) {
		    customerInfoUpdate.setIndustry(reqEditCustomerInfo.getIndustry());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getLinkedin())) {
		    customerInfoUpdate.setLinkedin(reqEditCustomerInfo.getLinkedin());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getName())) {
		    customerInfoUpdate.setName(reqEditCustomerInfo.getName());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getOtherRemarks())) {
		    customerInfoUpdate.setOtherRemarks(reqEditCustomerInfo.getOtherRemarks());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getOwnerId())) {
		    customerInfoUpdate.setOwnerId(reqEditCustomerInfo.getOwnerId());
		}
		if (StringUtils.isNotBlank(reqEditCustomerInfo.getOwnerName())) {
		    customerInfoUpdate.setOwnerName(reqEditCustomerInfo.getOwnerName());
		}
		log.info("customerInfoUpdate:{}", customerInfoUpdate);
		
		int row = customerInfoMapper.updateByEntity(customerInfoUpdate);
		String desc = String.format("update customer Info rows %d", row);
		Response response = new Response();
		response.setCode("200");
		response.setResult(desc);
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}
	
}
