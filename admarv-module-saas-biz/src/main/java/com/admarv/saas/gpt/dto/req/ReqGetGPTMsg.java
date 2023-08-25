package com.admarv.saas.gpt.dto.req;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class ReqGetGPTMsg implements Serializable {

	private static final long serialVersionUID = 6004674153531132112L;

	private String userId;
	private String msg;
	
	// 会话ID
	private String conversationId;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ReqGetGPTMsg [userId=" + userId + ", msg=" + msg + ", conversationId=" + conversationId + "]";
	}

}
