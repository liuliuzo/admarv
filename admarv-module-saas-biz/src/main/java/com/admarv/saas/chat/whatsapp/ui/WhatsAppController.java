package com.admarv.saas.chat.whatsapp.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.admarv.saas.chat.whatsapp.config.WhatAppWebSocketHandler;
import com.admarv.saas.chat.whatsapp.dto.req.ReqSendWhatsAppMsg;
import com.admarv.saas.chat.whatsapp.dto.req.ReqWhatsAppMsg;
import com.admarv.saas.chat.whatsapp.model.messages.Contacts;
import com.admarv.saas.chat.whatsapp.model.messages.ErrorData;
import com.admarv.saas.chat.whatsapp.model.messages.Errors;
import com.admarv.saas.chat.whatsapp.model.messages.Messages;
import com.admarv.saas.chat.whatsapp.model.messages.Metadata;
import com.admarv.saas.chat.whatsapp.model.messages.Profile;
import com.admarv.saas.chat.whatsapp.model.messages.Statuses;
import com.admarv.saas.chat.whatsapp.model.sendmsg.SendMsg;
import com.admarv.saas.chat.whatsapp.model.sendmsg.Text;
import com.admarv.saas.chat.whatsapp.model.sendresp.SendResponse;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.common.dto.resp.RespSaasInfo;
import com.admarv.saas.mapper.CustomerInfoMapper;
import com.admarv.saas.mapper.WhatsappContactMapper;
import com.admarv.saas.mapper.WhatsappMsgMapper;
import com.admarv.saas.model.CustomerInfo;
import com.admarv.saas.model.WhatsappContact;
import com.admarv.saas.model.WhatsappMsg;
import com.admarv.saas.utils.CheckURLInTextUtils;
import com.admarv.saas.utils.JacksonUtils;
import com.admarv.saas.utils.MatchedPair;
import com.google.api.client.util.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * 
 * @author liuliu
 *
 */
@RestController
public class WhatsAppController {

	private static final Logger log = LoggerFactory.getLogger(WhatsAppController.class);

	@Autowired
	private WhatAppWebSocketHandler whatAppWebSocketHandler;

	@Value("${whatsapp.token}")
	private String whatsappToken;

	@Value("${whatsapp.verify.token}")
	private String verifyToken;

	@Value("${whatsapp.phoneNumberId}")
	private String phoneNumberId;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WhatsappMsgMapper whatsappMsgMapper;
	
	@Autowired
	private WhatsappContactMapper whatsappContactMapper;
	
	@Autowired
	private CustomerInfoMapper customerInfoMapper;

	@PostMapping(value = "/admarv/webhook", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String webhook(@RequestBody String srtJson) {
		log.info("/admarv/webhook srtJson{}", srtJson);
		try {
			JsonObject jsonObject = JacksonUtils.fromJson(srtJson);
			String strObject = jsonObject.get("object").getAsString();
			if (!"whatsapp_business_account".equals(strObject)) {
				log.warn("object:{}", strObject);
				return "object is null";
			}	
			JsonArray jsonArray = jsonObject.getAsJsonArray("entry");
			for (JsonElement element : jsonArray) {
				if (element.isJsonObject()) {
					JsonObject jsonObjectEntry = element.getAsJsonObject();
					JsonArray jsonArrayChanges = jsonObjectEntry.getAsJsonArray("changes");
					for (JsonElement elementChanges : jsonArrayChanges) {
						if (elementChanges.isJsonObject()) {
							JsonObject jsonObjectChanges = elementChanges.getAsJsonObject();
							JsonObject jsonValue = jsonObjectChanges.get("value").getAsJsonObject();
							String field = jsonObjectChanges.get("field").getAsString();
							if ("messages".equals(field)) {
								List<Statuses> listStatuses = Lists.newArrayList();
								try {
									JsonArray jsonArrayStatuses = jsonValue.get("statuses").getAsJsonArray();
									listStatuses = this.parseJsonArrayToStatusesList(jsonArrayStatuses);
								} catch (Exception e) {
									log.warn("this is success message not contain statuses");
								}
								JsonObject jsonObjectMetadata = jsonValue.get("metadata").getAsJsonObject();
								Metadata metadata = JacksonUtils.convertJsonObjectToJavaBean(jsonObjectMetadata, Metadata.class);
								log.info("metadata:{}", metadata);
								// 失败情况发送消息
								if (!CollectionUtils.isEmpty(listStatuses)) {
								    Statuses statuses = listStatuses.get(0);
									String status = statuses.getStatus();
									if ("failed".equals(status)) {
										whatAppWebSocketHandler.sendFailMessageBack(metadata, statuses);
									}
								} else {
									JsonArray jsonArrayMessages = jsonValue.get("messages").getAsJsonArray();
									List<Messages> listMessages = this.parseJsonArrayToMessagesList(jsonArrayMessages);
									log.info("listMessages :{}", listMessages);
									JsonArray jsonArrayContacts = jsonValue.get("contacts").getAsJsonArray();
									List<Contacts> listContacts = this.parseJsonArrayToContactsList(jsonArrayContacts);
									log.info("contacts:{}", listContacts);
									List<MatchedPair> listMatchedPair = MatchedPair.findMatchedPairs(listMessages,listContacts);
									for (MatchedPair matchedPair : listMatchedPair) {
										Contacts contacts = matchedPair.getContacts();
										Messages messages = matchedPair.getMessages();
										//Send to global session
									    whatAppWebSocketHandler.sendGlobalSession(contacts, metadata);
										//Send to person
										whatAppWebSocketHandler.sendMessageBack(contacts, metadata, messages);
									}
								}
							}
						}
					}
				}
			}
			return "whatsApp ReceiveMsg success";
		} catch (Exception e) {
			log.error("webhook:{}", e);
			return e.getMessage();
		}
	}

	private static List<Messages> parseJsonArrayToMessagesList(JsonArray jsonArray) {
		List<Messages> messagesList = new ArrayList<>();
		for (JsonElement element : jsonArray) {
			if (element.isJsonObject()) {
				JsonObject jsonObject = element.getAsJsonObject();
				Messages messages = new Messages();
				messages.setFrom(jsonObject.get("from").getAsString());
				messages.setId(jsonObject.get("id").getAsString());
				messages.setTimestamp(jsonObject.get("timestamp").getAsString());
				messages.setType(jsonObject.get("type").getAsString());
				JsonObject textObject = jsonObject.getAsJsonObject("text");
				com.admarv.saas.chat.whatsapp.model.messages.Text text = new com.admarv.saas.chat.whatsapp.model.messages.Text();
				text.setBody(textObject.get("body").getAsString());
				messages.setText(text);
				messagesList.add(messages);
			}
		}
		return messagesList;
	}

	private List<Contacts> parseJsonArrayToContactsList(JsonArray jsonArray) {
		List<Contacts> contactsList = new ArrayList<>();
		for (JsonElement element : jsonArray) {
			if (element.isJsonObject()) {
				JsonObject jsonObject = element.getAsJsonObject();
				Contacts contacts = parseJsonObjectToContacts(jsonObject);
				contactsList.add(contacts);
			}
		}
		return contactsList;
	}

	/**
	 * 获取Statuses信息
	 * 
	 * @param jsonArray
	 * @return
	 */
	private List<Statuses> parseJsonArrayToStatusesList(JsonArray jsonArray) {
		List<Statuses> statusesList = new ArrayList<>();
		for (JsonElement element : jsonArray) {
			if (element.isJsonObject()) {
				JsonObject jsonObject = element.getAsJsonObject();
				Statuses statuses = new Statuses();
				statuses.setId(jsonObject.get("id").getAsString());
				statuses.setStatus(jsonObject.get("status").getAsString());
				statuses.setTimestamp(jsonObject.get("timestamp").getAsString());
				statuses.setRecipientId(jsonObject.get("recipient_id").getAsString());
				List<Errors> errorsList = new ArrayList<>();
				JsonArray errorsArray = jsonObject.getAsJsonArray("errors");
				for (JsonElement errorElement : errorsArray) {
					if (errorElement.isJsonObject()) {
						JsonObject errorObject = errorElement.getAsJsonObject();
						Errors error = new Errors();
						error.setCode(errorObject.get("code").getAsInt());
						error.setTitle(errorObject.get("title").getAsString());
						error.setMessage(errorObject.get("message").getAsString());
						JsonObject errorDataObject = errorObject.getAsJsonObject("error_data");
						if (errorDataObject != null) {
							ErrorData errorData = new ErrorData();
							errorData.setDetails(errorDataObject.get("details").getAsString());
							error.setErrorData(errorData);
						}
						errorsList.add(error);
					}
				}
				statuses.setErrors(errorsList);
				statusesList.add(statuses);
			}
		}
		return statusesList;
	}

	private Contacts parseJsonObjectToContacts(JsonObject jsonObject) {
		Contacts contacts = new Contacts();
		JsonObject profileObject = jsonObject.getAsJsonObject("profile");
		if (profileObject != null) {
			Profile profile = parseJsonObjectToProfile(profileObject);
			contacts.setProfile(profile);
		}
		contacts.setWaId(jsonObject.get("wa_id").getAsString());
		return contacts;
	}

	private Profile parseJsonObjectToProfile(JsonObject profileObject) {
		Profile profile = new Profile();
		profile.setName(profileObject.get("name").getAsString());
		return profile;
	}

	@PostMapping(value = "/admarv/sendWhatsAppMsg", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String sendWhatsAppMsg(@RequestBody ReqSendWhatsAppMsg reqSendWhatsAppMsg) {
		log.info("/admarv/sendWhatsAppMsg reqSendWhatsAppMsg:{}", reqSendWhatsAppMsg);
		String from = reqSendWhatsAppMsg.getFrom();
		String to = reqSendWhatsAppMsg.getTo();
		String body = reqSendWhatsAppMsg.getBody();
		
		WhatsappContact selectEntity = new WhatsappContact();
		selectEntity.setWhatsApp(to);
		WhatsappContact whatsappContact = whatsappContactMapper.selectOneByEntity(selectEntity);
		if (whatsappContact == null) {
			WhatsappContact insert = new WhatsappContact();
			insert.setWhatsApp(to);
			whatsappContactMapper.insert(insert);
		}
		
		String sessionKey = from + "_" + to;
		Map<String, WebSocketSession> sessionsMap = whatAppWebSocketHandler.getMapSessions();
		WebSocketSession session = sessionsMap.get(sessionKey);
		
		try {
			String apiUrl = "https://graph.facebook.com/v17.0/" + phoneNumberId + "/messages";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer" + " " + whatsappToken);
			Text text = new Text();
			boolean checkUrl = CheckURLInTextUtils.containsURL(body);
			if (checkUrl) {
				text.setPreviewUrl(true);
			}
			text.setBody(body);
			SendMsg sendMsg = new SendMsg();
			sendMsg.setTo(to);
			sendMsg.setMessagingProduct("whatsapp");
			sendMsg.setType("text");
			sendMsg.setText(text);
			sendMsg.setRecipientType("individual");
			String strSendMsg = JacksonUtils.toJson(sendMsg);
			log.info("sendMsg:{}", sendMsg);
			HttpEntity<String> requestEntity = new HttpEntity<>(strSendMsg, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
			String strSendResp = responseEntity.getBody();
			log.info("strSendResp:{}", strSendResp);
			SendResponse sendResponse = JacksonUtils.fromJson(strSendResp, SendResponse.class);
			log.info("sendResponse:{}", sendResponse);

			WhatsappMsg insert = new WhatsappMsg();
			insert.setWhatsappFrom(from);
			insert.setWhatsappTo(to);
			insert.setMsg(body);
			int row = whatsappMsgMapper.insert(insert);
			log.info("success insert whatsappMsg row:{} , insert:{}", row, insert);
			
			CustomerInfo selectCustomerInfo = new CustomerInfo();
			selectCustomerInfo.setWhatsapp(to);
			CustomerInfo customerInfoUpdate = customerInfoMapper.selectOneByEntity(selectCustomerInfo);
			if (customerInfoUpdate != null) {
				customerInfoUpdate.setLastContactTime(new Date());
				int row2 = customerInfoMapper.updateByEntity(customerInfoUpdate);
				log.info("success update CustomerInfo row:{}", row2);
			}
			
			Response response = new Response();
			response.setCode("200");
			response.setResult(sendResponse);
			response.setSuccess(true);
			String srtResponse = JacksonUtils.toJson(response);
			log.info("response:{}", response);
			return srtResponse;
		} catch (Exception e) {
			TextMessage textMessage = new TextMessage(e.getMessage());
			try {
				session.sendMessage(textMessage);
			} catch (Exception e2) {
				log.error("session send message fail", e);
			}
			Response response = new Response();
			response.setCode("602");
			response.setResult(e.getMessage());
			response.setSuccess(false);
			String srtResponse = JacksonUtils.toJson(response);
			log.info("response:{}", response);
			return srtResponse;
		}
	}

	/**
	 * webhook 验证
	 * 
	 * @param mode
	 * @param token
	 * @param challenge
	 * @return
	 */
	@GetMapping("/admarv/webhook")
	public ResponseEntity<String> verifyWebhook(@RequestParam("hub.mode") String mode,
			@RequestParam("hub.verify_token") String token, @RequestParam("hub.challenge") String challenge) {
		if ("subscribe".equals(mode) && verifyToken.equals(token)) {
			return ResponseEntity.ok(challenge);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	/**
	 * 获取联系过的whatsapp用户
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping("/admarv/whatappCntct")
	public String whatappCntct(String userId) {
		log.info("/admarv/whatappCntct userId:{}", userId);
		List<String> result = Lists.newArrayList();
		List<WhatsappContact> listWhatsappContact =  whatsappContactMapper.selectAll();
		for (WhatsappContact whatsappContact : listWhatsappContact) {
			String whatsAppNO = whatsappContact.getWhatsApp();
			result.add(whatsAppNO); // dahui01
		}
		// +14327568359
		// +15813441826
		Response response = new Response();
		response.setCode("200");
		response.setResult(result);
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}

	/**
	 * 获取用户saasInfo
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping("/admarv/saasInfo")
	public String saasInfo(String userId) {
		log.info("/admarv/oauthInfo userId:{}", userId);
		RespSaasInfo respSaasInfo = new RespSaasInfo();
		respSaasInfo.setWhatsApp("15550633754");
		Response response = new Response();
		response.setCode("200");
		response.setResult(respSaasInfo);
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}

	/**
	 * 获取whats聊天消息
	 * 
	 * @param reqWhatsAppMsg
	 * @return
	 */
	@PostMapping("/admarv/getWhatsAppMsg")
	public String getWhatsAppMsg(@RequestBody ReqWhatsAppMsg reqWhatsAppMsg) {
		log.info("/admarv/getWhatsAppMsg reqWhatsAppMsg :{}", reqWhatsAppMsg);
		String from = reqWhatsAppMsg.getFrom();
		String to = reqWhatsAppMsg.getTo();
		
		WhatsappMsg selectEntity = new WhatsappMsg();
		selectEntity.setWhatsappFrom(from);
		selectEntity.setWhatsappTo(to);
		List<WhatsappMsg> listWhatsappMsg = whatsappMsgMapper.selectByEntity(selectEntity);

		WhatsappMsg selectEntity02 = new WhatsappMsg();
		selectEntity02.setWhatsappFrom(to);
		selectEntity02.setWhatsappTo(from);
		List<WhatsappMsg> listWhatsappMsg02 = whatsappMsgMapper.selectByEntity(selectEntity02);
		
		List<WhatsappMsg> mergedList = new ArrayList<WhatsappMsg>(listWhatsappMsg);
		mergedList.addAll(listWhatsappMsg02);

		Response response = new Response();
		response.setCode("200");
		response.setResult(mergedList);
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}

}
