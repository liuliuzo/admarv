package com.admarv.saas.chat.whatsapp.model.webhook;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Messages implements Serializable{
	
	private static final long serialVersionUID = -1639095391010818169L;
	
	private String from;
	private String id;
	private String timestamp;
	private Text text;
	private String type;

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Text getText() {
		return this.text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Messages [from=" + from + ", id=" + id + ", timestamp=" + timestamp + ", text=" + text + ", type="
				+ type + "]";
	}

}
