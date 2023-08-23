package com.admarv.saas.fb.auth.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.admarv.saas.fb.auth.domain.FBAuthService;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.utils.JacksonUtils;

/**
 * 预授权服务
 * 
 * @author liuliu
 *
 */
@RestController
public class OAuthController {

    private static final Logger log = LoggerFactory.getLogger(OAuthController.class);

    @Autowired
    private FBAuthService authService;
    
    /**
     * FBOauth 获取跳转链接
     * @param customer当前登录人账号
     * @return oauth回调接口
     */
    @GetMapping("/admarv/FBOauth")
    public Response auth(String userId) {
        log.info("/admarv/auth userId:{}", userId);
        String callbackUrl = authService.genAuthorizationUrl(userId);
        log.info("callbackUrl:{}", callbackUrl);        
        Response response = new Response();
		response.setCode("200");
		response.setResult(callbackUrl);
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return response; 
    }
    
    /**
     * Oauth callback 回调方法
     */
    @GetMapping("/admarv/oauth_callback")
    public String authCallback(@RequestParam(required=false) String code,
                               @RequestParam(required=false) String state,
                               @RequestParam(required=false) String error,
                               @RequestParam(required=false) String error_reason,
                               @RequestParam(required=false) String error_description) {
        //callback info
        log.info("/admarv/oauth_callback authCallback code:{}, state:{}", code, state);
        //error msg info
        log.info("/admarv/oauth_callback authCallback error:{}, error_reason:{}, error_description:{}", error_reason, error_reason, error_description);
        String token = authService.callBack(code, state, error, error_reason, error_description);
        log.info("token:{}", token);
        return token;
    }
}