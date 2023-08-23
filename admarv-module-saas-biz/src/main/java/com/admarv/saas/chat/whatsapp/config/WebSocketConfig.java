package com.admarv.saas.chat.whatsapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 
 * @author liuliu
 *
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	private WhatAppWebSocketHandler whatAppWebSocketHandler;

	public WebSocketConfig(WhatAppWebSocketHandler whatAppWebSocketHandler) {
		this.whatAppWebSocketHandler = whatAppWebSocketHandler;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(whatAppWebSocketHandler, "/admarv").setAllowedOrigins("*");
	}
}
