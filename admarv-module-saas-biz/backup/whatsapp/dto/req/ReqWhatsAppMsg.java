package com.admarv.saas.whatsapp.dto.req;

/**
 * 
 * @author liuliu
 *
 */
public class ReqWhatsAppMsg {

	private String from;

	private String to;

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
		return "ReqWhatsAppMsg [from=" + from + ", to=" + to + "]";
	}

}
