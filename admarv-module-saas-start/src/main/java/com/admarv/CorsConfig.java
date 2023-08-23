package com.admarv;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 
 * @author liuliu
 *
 */
@Configuration
public class CorsConfig {

    /**
     * 目前跨域访问关闭
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); //允许所有来源
        config.addAllowedHeader("*"); //允许所有头部信息
        config.addAllowedMethod("*"); //允许所有请求方法
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}