package com.admarv.saas.fb.common.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.common.ExchangeRateService;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.utils.JacksonUtils;

/**
 * 用户登录
 * 
 * @author liuliu
 *
 */
@RestController
public class CommonController {

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ExchangeRateService exchangeRateService;
    
    /**
     * 获取汇率
     * 
     * @param user
     * @return
     */
    @GetMapping("/admarv/exchangeRate")
    public String exchangeRate(String base, String target) {
        log.info("/admarv/exchangeRate base:{}, target:{}", base, target);
        try {
            String rate = exchangeRateService.exchangeRate(base, target);
            log.info("rate:{}", rate);
            Response response = new Response();
            response.setCode("200");
            response.setResult(rate);
            response.setSuccess(true);
            String srtResponse = JacksonUtils.toJson(response);
            log.info(srtResponse);
            return srtResponse;
        } catch (Exception e) {
            Response response = new Response();
            response.setCode("602");
            response.setResult(e.getMessage());
            response.setSuccess(true);
            String srtResponse = JacksonUtils.toJson(response);
            log.info(srtResponse);
            return srtResponse;
        }
    }

}
