package com.admarv.saas.chat.whatsapp.model.webhook;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Conversation  implements Serializable {
	
	private static final long serialVersionUID = -2864679755687997934L;
	
	private String id;
	private String expirationTimestamp;
	private Origin origin;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExpirationTimestamp() {
		return this.expirationTimestamp;
	}

	public void setExpirationTimestamp(String expirationTimestamp) {
		this.expirationTimestamp = expirationTimestamp;
	}

	public Origin getOrigin() {
		return this.origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	@Override
	public String toString() {
		return "Conversation [id=" + id + ", expirationTimestamp=" + expirationTimestamp + ", origin=" + origin + "]";
	}
}
