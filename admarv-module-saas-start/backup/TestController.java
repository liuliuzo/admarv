package org.jeecg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试用 Controller
 * 
 * @author liuliu
 *
 */
@RestController
public class TestController {
    
    private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/v17/me";

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/admarv/test", method = RequestMethod.GET)
    public String test() {
        log.info("/admarv/test echo:{}", PROTECTED_RESOURCE_URL);
        return "success";
    }
}
