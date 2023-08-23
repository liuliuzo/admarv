package com.admarv.saas.whatsapp.config;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.admarv.saas.mapper.CustomerInfoMapper;
import com.admarv.saas.mapper.WhatsappMsgMapper;
import com.admarv.saas.model.CustomerInfo;
import com.admarv.saas.model.WhatsappMsg;
import com.admarv.saas.utils.JacksonUtils;
import com.admarv.saas.utils.URLUtils;
import com.admarv.saas.whatsapp.dto.resp.GlobalWhatsAppNotice;
import com.admarv.saas.whatsapp.model.messages.Contacts;
import com.admarv.saas.whatsapp.model.messages.Messages;
import com.admarv.saas.whatsapp.model.messages.Metadata;
import com.admarv.saas.whatsapp.model.messages.Statuses;
import com.google.common.collect.Maps;

/**
 * 
 * @author liuliu
 *
 */
@Component
public class WhatAppWebSocketHandler extends TextWebSocketHandler {

	private static final Logger log = LoggerFactory.getLogger(WhatAppWebSocketHandler.class);
	
	private Map<String, WebSocketSession> mapSessions = Maps.newHashMap();
	
    public Map<String, WebSocketSession> getMapSessions() {
		return mapSessions;
	}

	public void setMapSessions(Map<String, WebSocketSession> mapSessions) {
		this.mapSessions = mapSessions;
	}

	@Autowired
    private WhatsappMsgMapper whatsappMsgMapper;
	
	@Autowired
	private CustomerInfoMapper customerInfoMapper;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("Connection Established session：{}", session);
		URI url = session.getUri();
		String query = url.getQuery();
		Map<String, String> queryParams = URLUtils.parseQueryParameters(query);
		String sessionKey = queryParams.get("sessionKey");
		log.info("Connection Established session sessionKey：{}", sessionKey);
		mapSessions.put(sessionKey, session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("WebSocketSession:{}, TextMessage:{}", session, message);
	}

	/**
	 * 发送global session
	 * 
	 * @param contacts
	 * @param metadata
	 * @param message
	 * @throws IOException
	 */
	public void sendGlobalSession(Contacts contacts, Metadata metadata) throws IOException {
		log.info("sendGlobalSession contacts:{}, metadata:{}", contacts, metadata);
		String fromNum = contacts.getWaId();
		WebSocketSession globalWhatsAppSession = mapSessions.get("GlobalSessionKey_WhatsApp_15550387717");
		GlobalWhatsAppNotice globalWhatsAppNotice = new GlobalWhatsAppNotice();
		globalWhatsAppNotice.setFromNum(fromNum);
		globalWhatsAppNotice.setCount(1);
		String notice = JacksonUtils.toJson(globalWhatsAppNotice);
		TextMessage msgGlobalWhatsApp = new TextMessage(notice.getBytes());
		if (globalWhatsAppSession != null) {
			log.info("send global session globalWhatsAppSession:{}", globalWhatsAppSession);
			globalWhatsAppSession.sendMessage(msgGlobalWhatsApp);
		} else {
			log.warn("globalWhatsAppSession is null session:{}", globalWhatsAppSession);
		}
	}
	
	/**
	 * @param contacts
	 * @param metadata
	 * @param message
	 * @throws IOException
	 */
	public void sendMessageBack(Contacts contacts, Metadata metadata, Messages message) throws IOException {
		log.info("sendMessageBack metadata:{}, message:{}", metadata, message);
		String displayPhoneNumber = metadata.getDisplayPhoneNumber();
		String fromNum = contacts.getWaId();
		String sessionKey = displayPhoneNumber + "_" + fromNum;
		log.info("sessionKey:{}", sessionKey);
		WebSocketSession session = mapSessions.get(sessionKey);
		if (session != null) {
			String from = message.getFrom();
			String body = message.getText().getBody();
			TextMessage textMessage = new TextMessage(body);
			session.sendMessage(textMessage);
			WhatsappMsg insert = new WhatsappMsg();
			insert.setWhatsappFrom(from);
			insert.setWhatsappTo(displayPhoneNumber);
			insert.setMsg(body);
			int row = whatsappMsgMapper.insert(insert);
			log.info("success insert WhatsappMsg row:{}", row);
			CustomerInfo selectEntity = new CustomerInfo();
			selectEntity.setWhatsapp(fromNum);
			CustomerInfo customerInfoUpdate = customerInfoMapper.selectOneByEntity(selectEntity);
			if (customerInfoUpdate != null) {
				customerInfoUpdate.setLastContactTime(new Date());
				int row2 = customerInfoMapper.updateByEntity(customerInfoUpdate);
				log.info("success update CustomerInfo row:{}", row2);
			}
		} else {
			log.warn(" session is null session:{}", session);
		}
	}
	
	public void sendFailMessageBack(Metadata metadata, Statuses statuses) throws IOException {
		log.info("sendFailMessageBack metadata:{}, statuses:{}", metadata, statuses);
		String displayPhoneNumber = metadata.getDisplayPhoneNumber();
		String fromNum = statuses.getRecipientId();
		String sessionKey = displayPhoneNumber + "_" + fromNum;
		log.info("sessionKey:{}", sessionKey);
		WebSocketSession session = mapSessions.get(sessionKey);
		if (session != null) {
			String from = statuses.getRecipientId();
			String body = statuses.getErrors().get(0).getErrorData().getDetails();
			TextMessage textMessage = new TextMessage(body);
			session.sendMessage(textMessage);
			WhatsappMsg insert = new WhatsappMsg();
			insert.setWhatsappFrom(from);
			insert.setWhatsappTo(displayPhoneNumber);
			insert.setMsg(body);
			int row = whatsappMsgMapper.insert(insert);
			log.info("success insert WhatsappMsg row:{}", row);
			CustomerInfo selectEntity = new CustomerInfo();
			selectEntity.setWhatsapp(fromNum);
			CustomerInfo customerInfoUpdate = customerInfoMapper.selectOneByEntity(selectEntity);
			if (customerInfoUpdate != null) {
				customerInfoUpdate.setLastContactTime(new Date());
				int row2 = customerInfoMapper.updateByEntity(customerInfoUpdate);
				log.info("success update CustomerInfo row:{}", row2);
			}
		} else {
			log.warn(" session is null session:{}", session);
		}
	}
}
