package com.admarv.saas.chat.whatsapp.model.messages;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Text implements Serializable {
	
	private static final long serialVersionUID = 2707888390257635006L;
	
	private String body;

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Text [body=" + body + "]";
	}
	
}
