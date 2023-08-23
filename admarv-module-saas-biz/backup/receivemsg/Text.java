package com.admarv.saas.whatsapp.model.receivemsg;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Text implements Serializable  {

	private static final long serialVersionUID = 206546625082624243L;
	
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
