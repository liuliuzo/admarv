package com.admarv.saas.chat.whatsapp.dto.req;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class ReqSendWhatsAppMsg implements Serializable {

	private static final long serialVersionUID = 2107324919622001881L;
	
	private String from;
	private String to;
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "ReqSendWhatsAppMsg [from=" + from + ", to=" + to + ", body=" + body + "]";
	}
}
