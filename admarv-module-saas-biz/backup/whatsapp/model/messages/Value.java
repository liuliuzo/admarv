package com.admarv.saas.whatsapp.model.messages;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author liuliu
 *
 */
public class Value implements Serializable {
	
	private static final long serialVersionUID = 794873289308458209L;
	
	private String messagingProduct;
	private Metadata metadata;
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

	@Override
	public String toString() {
		return "Value [messagingProduct=" + messagingProduct + ", metadata=" + metadata + ", contacts=" + contacts
				+ ", messages=" + messages + "]";
	}

}
