package com.admarv.saas.chat.whatsapp.model.webhook;

import java.util.List;

public class Value {
	private String messagingProduct;
	private Metadata metadata;
	private List<Statuses> statuses;
	private List<Contacts> contacts;
	private List<Messages> messages;

	public String getMessagingProduct() {
		return this.messagingProduct;
	}

	public void setMessagingProduct(String messagingProduct) {
		this.messagingProduct = messagingProduct;
	}

	public Metadata getMetadata() {
		return this.metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<Statuses> getStatuses() {
		return this.statuses;
	}

	public void setStatuses(List<Statuses> statuses) {
		this.statuses = statuses;
	}

	public List<Contacts> getContacts() {
		return this.contacts;
	}

	public void setContacts(List<Contacts> contacts) {
		this.contacts = contacts;
	}

	public List<Messages> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Messages> messages) {
		this.messages = messages;
	}
}
