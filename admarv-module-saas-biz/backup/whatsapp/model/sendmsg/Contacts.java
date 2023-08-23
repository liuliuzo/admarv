package com.admarv.saas.whatsapp.model.sendmsg;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Contacts implements Serializable {

	private static final long serialVersionUID = -5000442812028880590L;

	private String input;
	private String waId;

	public String getInput() {
		return this.input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getWaId() {
		return this.waId;
	}

	public void setWaId(String waId) {
		this.waId = waId;
	}

	@Override
	public String toString() {
		return "Contacts [input=" + input + ", waId=" + waId + "]";
	}

}
