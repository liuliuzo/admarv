package org.jeecg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.admarv.saas.HelloWorldController;
import com.admarv.saas.fb.FBConfiguration;
import com.admarv.saas.fb.FaceBookController;

/**
 * 此类为适配jeegc框架和Spring Framework 5使用，增加扩展性
 * 
 * @author liuliu
 *
 */
@Configuration
public class AdmarvConfiguration {

    @Bean
    public HelloWorldController helloWorldController() {
        return new HelloWorldController();
    }
    
    @Bean
    public FaceBookController faceBookController() {
        return new FaceBookController();
    }
    
    @Bean
    public FBConfiguration fBConfiguration() {
        return new FBConfiguration();
    }

}
