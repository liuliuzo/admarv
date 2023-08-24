package com.admarv;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 生成接口参数签名demo
 */
public class generateSignDemo {
	
	private static final Logger log = LoggerFactory.getLogger(generateSignDemo.class);
	
	/**
	 * 生成接口参数签名demo
	 */
	public void generateSignDemo() {
	    // header参数
	    Map<String, String> headers = new HashMap<>(8);
	    headers.put("accessKey", "fme2na3kdi3ki");
	    headers.put("ts", "1655710885431");
	    headers.put("bizType", "2");
	    headers.put("action", "mt");

	    // 业务参数
	    JSONObject postData = new JSONObject();
	    postData.put("id", 10001);
	    postData.put("name", "牛小信");
	    String body = postData.toString();
	    
	    // accessKey对应的密码
	    String accessSecret = "abciiiko2k3";

	    String sign = calcSign(headers, body, accessSecret);
	    log.info("sign: {}", sign); // sign: 87c3560d3331ae23f1021e2025722354
	}

	/**
	 * 计算sign签名
	 *
	 * @param headers      请求头中的公共参数
	 * @param body         body中的json字符串
	 * @param accessSecret 秘钥
	 * @return
	 */
	private String calcSign(Map<String, String> headers, String body, String accessSecret) {
	    StringBuilder raw = new StringBuilder();

	    // step1: 拼接header参数
	    raw.append("accessKey=").append(headers.get("accessKey")).append("&action=").append(headers.get("action"))
	            .append("&bizType=").append(headers.get("bizType")).append("&ts=").append(headers.get("ts"));
	    log.info("step1: {}", raw); // step1: accessKey=fme2na3kdi3ki&action=send&bizType=1&ts=1655710885431

	    // step2: 拼接body参数
	    if (StringUtils.isNotEmpty(body)) {
	        raw.append("&body=").append(body);
	    }
	    log.info("step2: {}", raw); // step2: accessKey=fme2na3kdi3ki&action=send&bizType=1&ts=1655710885431&body={"name":"牛小信","id":10001}

	    // step3: 拼接accessSecret
	    raw.append("&accessSecret=").append(accessSecret);
	    log.info("step3: {}", raw); // step3: accessKey=fme2na3kdi3ki&action=send&bizType=1&ts=1655710885431&body={"name":"牛小信","id":10001}&accessSecret=abciiiko2k3

	    // step4: MD5算法加密,结果转换成十六进制小写
	    String sign = DigestUtils.md5Hex(raw.toString());
	    log.info("step4: sign={}", sign); // step4: sign=87c3560d3331ae23f1021e2025722354

	    return sign;
	}
}

