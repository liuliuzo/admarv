package com.admarv.saas.fb.common.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.dashboard.dto.resp.DashBoard;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.common.UserLoginService;
import com.admarv.saas.utils.JacksonUtils;

/**
 * 用户登录
 * 
 * @author liuliu
 *
 */
@RestController
public class LoginController {
    
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private UserLoginService userLoginService;
    
    /**
     * 获取数据中心数据
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "/admarv/login", method = RequestMethod.GET)
    public String auth(String user) {
        log.info("/admarv/dashboard user:{}", user);

        DashBoard dashBoard = new DashBoard();
        Response response = new Response();
        response.setCode("200");
        response.setResult(dashBoard);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
    
}
