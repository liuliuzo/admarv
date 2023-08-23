package com.admarv.saas.whatsapp.model.sendmsg;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class SendMsg implements Serializable {

	private static final long serialVersionUID = -3617268346098881542L;

	private String messagingProduct;
	private String recipientType;
	private String to;
	private String type;
	private Text text;

	public String getMessagingProduct() {
		return messagingProduct;
	}

	public void setMessagingProduct(String messagingProduct) {
		this.messagingProduct = messagingProduct;
	}

	public String getRecipientType() {
		return recipientType;
	}

	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "SendMsg [messagingProduct=" + messagingProduct + ", recipientType=" + recipientType + ", to=" + to
				+ ", type=" + type + ", text=" + text + "]";
	}

}
