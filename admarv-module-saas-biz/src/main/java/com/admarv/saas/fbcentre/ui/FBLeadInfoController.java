package com.admarv.saas.fbcentre.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fbcentre.dto.resp.LeadInfo;
import com.admarv.saas.mapper.CustomerInfoMapper;
import com.admarv.saas.mapper.LeadGenMapper;
import com.admarv.saas.utils.JacksonUtils;

/**
 * 线索数据概览
 * 
 * @author liuliu
 *
 */
@RestController
public class FBLeadInfoController {

	private static final Logger log = LoggerFactory.getLogger(FBLeadInfoController.class);
	
	@Autowired
	private CustomerInfoMapper customerInfoMapper;
	
	@Autowired
	private LeadGenMapper leadGenMapper;

	@RequestMapping(value = "/admarv/getLeadInfo", method = RequestMethod.GET)
	public String getLeadInfo(String userId) {
		log.info("/admarv/getLeadInfo userId:{}", userId);
		LeadInfo leadInfo = new LeadInfo();
		int custCount = this.getCustomerCount();
		leadInfo.setCustomerCount(custCount);
		int leadCount = this.getLeadCount(userId);
		leadInfo.setLeadCount(leadCount);
		int orderCount = this.getOrderCount(userId);
		leadInfo.setOrderCount(orderCount);
		
		Response response = new Response();
		response.setResult(leadInfo);
		response.setCode("200");
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}

	private int getCustomerCount() {
		int result = customerInfoMapper.getCustomerInfoCount();
		log.info("getCustomerCount:{}", result);
		return result;
	}

	private int getLeadCount(String userId) {
		int result = leadGenMapper.countAllByUser(userId);
		log.info("getLeadCount:{}", result);
		return result;
	}

	private int getOrderCount(String userId) {
		int result = leadGenMapper.countAllByUserAndFlwpStat(userId,"04");
		log.info("getOrderCount:{}", result);
		return result;
	}

}
