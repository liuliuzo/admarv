package com.admarv.saas.chat.whatsapp.model.webhook;

import java.io.Serializable;

public class Statuses implements Serializable {
	
	private static final long serialVersionUID = 4895686377799208449L;
	
	private String id;
	private String status;
	private String timestamp;
	private String recipientId;
	private Conversation conversation;
	private Pricing pricing;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getRecipientId() {
		return this.recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public Conversation getConversation() {
		return this.conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Pricing getPricing() {
		return this.pricing;
	}

	public void setPricing(Pricing pricing) {
		this.pricing = pricing;
	}

	@Override
	public String toString() {
		return "Statuses [id=" + id + ", status=" + status + ", timestamp=" + timestamp + ", recipientId=" + recipientId
				+ ", conversation=" + conversation + ", pricing=" + pricing + "]";
	}
	
}
