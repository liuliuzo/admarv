package com.admarv.saas.whatsapp.ui;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.admarv.saas.whatsapp.config.WhatAppWebSocketHandler;

/**
 * 心跳保活websocket
 * 
 * @author liuliu
 *
 */
@Component
public class webSocketEchoTask {
	
	private static final Logger log = LoggerFactory.getLogger(webSocketEchoTask.class);
	
	@Autowired
	private WhatAppWebSocketHandler whatAppWebSocketHandler;
	
	//@Scheduled(cron = "0 * * * * *")
	public void doWebSocketEchoTask() {
		log.info("doWebSocketEchoTask ");
		Map<String, WebSocketSession> sessionMap= whatAppWebSocketHandler.getMapSessions();
		for (String key : sessionMap.keySet()) {
			log.info("key:{}", key);
			WebSocketSession webSocketSession = sessionMap.get(key);
			log.info("webSocketSession", webSocketSession);			
			TextMessage textMessage = new TextMessage("34717afbd03641ae87d5aa9a0ca2357f");	
			try {
				webSocketSession.sendMessage(textMessage);
			} catch (IOException e) {
				log.error("send key:{} fail", key, e);
			}
        }	
	}
}
