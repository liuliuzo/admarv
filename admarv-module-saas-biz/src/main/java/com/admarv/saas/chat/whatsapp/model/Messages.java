package com.admarv.saas.chat.whatsapp.model;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Messages  implements Serializable {

	private static final long serialVersionUID = 6198662418843840679L;
	
	private String from;
	private String id;
	private String timestamp;
	private String type;
	private Text text;

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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Text getText() {
		return this.text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Messages [from=" + from + ", id=" + id + ", timestamp=" + timestamp + ", type=" + type + ", text="
				+ text + "]";
	}

}
