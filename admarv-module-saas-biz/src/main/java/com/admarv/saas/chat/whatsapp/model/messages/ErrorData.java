package com.admarv.saas.chat.whatsapp.model.messages;

public class ErrorData {

	private String details;

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ErrorData [details=" + details + "]";
	}
	
}
