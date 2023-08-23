package com.admarv.saas.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExchangeRateService {
    
    private static final Logger log = LoggerFactory.getLogger(ExchangeRateService.class);

    @Autowired
    private RestTemplate restTemplate;
    
    /**
     * 
     * @param jsonResponse
     * @param currencyCode
     * @return
     * @throws JsonProcessingException 
     * @throws JsonMappingException 
     */
    public String exchangeRate(String base, String target) throws JsonMappingException, JsonProcessingException {
        // 设置API的URL和要查询的货币代码
        String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + base;
        // 发送GET请求并获取响应
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, String.class);
        String jsonResponse = responseEntity.getBody();
        log.info("exchangerate response:{}", jsonResponse);
        // 创建 ObjectMapper 实例
        ObjectMapper objectMapper = new ObjectMapper();
        // 将 JSON 字符串转换为 JsonNode 对象
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        // 获取特定字段的值
        String rates = String.valueOf(jsonNode.get("rates"));
        JsonNode jsonNodeRates = objectMapper.readTree(rates);
        String rate=jsonNodeRates.get(target).asText();
        log.info("rate:{}", rate);
        return rate; 
    }

}
