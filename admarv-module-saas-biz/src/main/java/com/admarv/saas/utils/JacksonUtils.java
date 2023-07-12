package com.admarv.saas.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.admarv.saas.fb.dto.resp.Accounts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

/**
 * 
 * @author liuliu
 *
 */
public class JacksonUtils {
    
    private static final Logger log = LoggerFactory.getLogger(JacksonUtils.class);
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    private JacksonUtils() {
        throw new IllegalStateException("Utility class");
    }
    
    static {
        // 配置 ObjectMapper
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 设置驼峰命名策略
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }
    
    public static String toJson(Object object) {
        try {
            String jsonString = objectMapper.writeValueAsString(object);
            log.info("Serialized JSON:{}", jsonString);
            return jsonString;
        } catch (JsonProcessingException e) {
            log.error("Serialized JSON error", e);
            return null;
        }
    }
    
    public static <T> T fromJson(String jsonString, Class<T> valueType) {
        try {
            T object = objectMapper.readValue(jsonString, valueType);
            log.info("Deserialized Object:{}", object);
            return object;
        } catch (JsonProcessingException e) {
            log.error("Serialized JSON error", e);
            return null;
        }
    }
    
    public static void main(String[] args) {
        String json="{\"data\":[{\"account_id\":\"1995454164119373\",\"id\":\"act_1995454164119373\"}],\"paging\":{\"cursors\":{\"before\":\"MjM4NTY0NDE3NDQxMzA3NDYZD\",\"after\":\"MjM4NTY0NDE3NDQxMzA3NDYZD\"}}}";
        Accounts accounts = JacksonUtils.fromJson(json, Accounts.class);
        //Accounts accountsNull = JacksonUtils.fromJson(null, Accounts.class);
        //Accounts accountsEmpty = JacksonUtils.fromJson("", Accounts.class);
        log.info("accounts:{}", accounts);
        String result = JacksonUtils.toJson(accounts);
        log.info("result:{}", result);
        
    }
}