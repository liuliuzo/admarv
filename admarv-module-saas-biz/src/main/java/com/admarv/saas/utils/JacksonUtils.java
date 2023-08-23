package com.admarv.saas.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 
 * @author liuliu
 *
 */
public class JacksonUtils {
    
    private static final Logger log = LoggerFactory.getLogger(JacksonUtils.class);
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    private static final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    
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
        } catch (Exception e) {
            log.error("Serialized JSON error", e);
            return null;
        }
    }
    
    public static JsonObject fromJson(String jsonString) {
        try {
            JsonObject jsonObject = com.google.gson.JsonParser.parseString(jsonString).getAsJsonObject();
            log.info("Parsed JsonObject:{}", jsonObject);
            return jsonObject;
        } catch (Exception e) {
            log.error("Deserialization error", e);
            return null;
        }
    }

    public static <T> T fromJson(String jsonString, Class<T> valueType) {
        try {
            T object = objectMapper.readValue(jsonString, valueType);
            log.info("Deserialized Object:{}", object);
            return object;
        } catch (Exception e) {
            log.error("Serialized JSON error", e);
            return null;
        }
    }
    
    public static JsonArray fromJsonArray(String jsonArrayString) {
        try {
            JsonArray jsonArray = com.google.gson.JsonParser.parseString(jsonArrayString).getAsJsonArray();
            log.info("Parsed JsonArray: {}", jsonArray);
            return jsonArray;
        } catch (Exception e) {
            log.error("Deserialization error", e);
            return null;
        }
    }
    
    public static String jsonArrayToString(JsonArray jsonArray) {
        return gson.toJson(jsonArray);
    }
    
    
    public static <T> T convertJsonObjectToJavaBean(JsonObject jsonObject, Class<T> beanClass) {
        return gson.fromJson(jsonObject, beanClass);
    }
}