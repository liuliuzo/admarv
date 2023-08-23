package com.admarv.saas.fb.lead.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.lead.domain.LeadGenService;
import com.admarv.saas.mapper.LeadGenMapper;
import com.admarv.saas.mapper.SysUserMapper;
import com.admarv.saas.model.LeadGen;
import com.admarv.saas.model.SysUser;
import com.admarv.saas.utils.JacksonUtils;

/**
 * 自动分配询盘
 * 
 * @author liuliu
 *
 */
@RestController
public class AllocationLeadController {
    
    private static final Logger log = LoggerFactory.getLogger(AllocationLeadController.class);
    
    @Autowired
    private LeadGenService leadGenService;
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private LeadGenMapper leadGenMapper;

    @GetMapping("/admarv/allocationLead")
    public String allocationLead(String date, String userId) {
        log.info("/admarv/allocationLead date:{}, userId:{}", date, userId);
		List<SysUser> listSysUser = sysUserMapper.selectAll();
		List<LeadGen> list = leadGenService.getAllLeads(date, userId);
		
		int numElements = 10;
		for (LeadGen leadGen : list) {
			String ownerId = leadGen.getOwnerId();
			if (StringUtils.isBlank(ownerId)) {
				for (int i = 0; i < numElements; i++) {
					SysUser sysUser = listSysUser.get(i % listSysUser.size());
					log.info("sysUser " + (i + 1) + ": " + sysUser);
					LeadGen leadGenUpdate = leadGenMapper.selectByPrimaryKey(leadGen.getId());
					leadGenUpdate.setOwnerId(sysUser.getUserId());
				}
			}
		}
		
        Response response = new Response();
        response.setCode("200");
        response.setResult("");
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
 
    public static void main(String[] args) {
    	List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        int numAccesses = 10; // 循环访问的次数
        for (int i = 0; i < numAccesses; i++) {
            String element = list.get(i % list.size()); // 使用取模运算符获取下一个元素
            System.out.println("Access " + (i + 1) + ": " + element);
        }
	}
    
}