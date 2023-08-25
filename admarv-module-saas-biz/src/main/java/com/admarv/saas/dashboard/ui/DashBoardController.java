package com.admarv.saas.dashboard.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.dashboard.domain.DashBrdGnrtService;
import com.admarv.saas.dashboard.dto.resp.DashBoard;
import com.admarv.saas.dashboard.task.CampaignsInsightsTask;
import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.utils.JacksonUtils;
import com.restfb.FacebookClient;

/**
 * 数据中心
 * 
 * @author liuliu
 *
 */
@RestController
public class DashBoardController {
    
    private static final Logger log = LoggerFactory.getLogger(DashBoardController.class);
    
    @Autowired
    private DashBrdGnrtService dashBrdGnrtService;
    
    @Autowired
    private CampaignsInsightsTask campaignsInsightsTask; 
    
    @Autowired
    private FacebookClientService facebookClientService;
    
    /**
     * 获取数据中心数据
     * 
     * @param user
     * @return
     */
    @GetMapping("/admarv/dashboard")
    public String dashboard(String userId) {
        log.info("/admarv/dashboard userId:{}", userId);
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
		try {
			DashBoard dashBoard = dashBrdGnrtService.getDashBoard(userId);
			log.info("dashBoard:{}", dashBoard);
			Response response = new Response();
			response.setCode("200");
			response.setResult(dashBoard);
			response.setSuccess(true);
			String srtResponse = JacksonUtils.toJson(response);
			log.info(srtResponse);
			return srtResponse;
		} catch (Exception e) {
			Response response = new Response();
			response.setCode("602");
			response.setResult(e.getMessage());
			response.setSuccess(false);
			String srtResponse = JacksonUtils.toJson(response);
			log.info(srtResponse);
			return srtResponse;
		}
    }
    
    /**
     * 同步广告系列成效
     * 
     * @return
     */
    @GetMapping("/admarv/refreshCampaignsInsights")
    public String refreshCampaignsInsights() {
        log.info("/admarv/refreshCampaignsInsights");
        campaignsInsightsTask.doCampaignsInsightsTask();
        return "success refresh campaignsInsightsTask ";
    }
    
}
