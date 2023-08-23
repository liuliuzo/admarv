package com.admarv.saas.chat.whatsapp.model.webhook;

import java.util.List;

public class WebHook {
	
	private String object;
	private List<Entry> entry;

	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public List<Entry> getEntry() {
		return this.entry;
	}

	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}
}
