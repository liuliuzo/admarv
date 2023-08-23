package com.admarv.saas.whatsapp.model.sendresp;

import java.io.Serializable;
import java.util.List;

import com.admarv.saas.whatsapp.model.sendmsg.Contacts;
import com.admarv.saas.whatsapp.model.sendmsg.SendMsg;

/**
 * 
 * @author liuliu
 *
 */
public class SendResponse implements Serializable {

	private static final long serialVersionUID = -7163136819545224547L;

	private String messagingProduct;
	private List<Contacts> contacts;
	private List<SendMsg> messages;

	public String getMessagingProduct() {
		return this.messagingProduct;
	}

	public void setMessagingProduct(String messagingProduct) {
		this.messagingProduct = messagingProduct;
	}

	public List<Contacts> getContacts() {
		return this.contacts;
	}

	public void setContacts(List<Contacts> contacts) {
		this.contacts = contacts;
	}

	public List<SendMsg> getMessages() {
		return this.messages;
	}

	public void setMessages(List<SendMsg> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "WhatsAppMsg [messagingProduct=" + messagingProduct + ", contacts=" + contacts + ", messages=" + messages
				+ "]";
	}

}
