package com.admarv.saas.chat.whatsapp.model.webhook;

import java.io.Serializable;

public class Origin implements Serializable {

	private static final long serialVersionUID = -2203417251253847689L;
	
	private String type;

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Origin [type=" + type + "]";
	}

}
