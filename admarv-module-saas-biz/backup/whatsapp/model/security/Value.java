package com.admarv.saas.whatsapp.model.security;

/**
 * 
 * @author liuliu
 *
 */
public class Value {

	private String displayPhoneNumber;
	private String event;
	private String requester;

	public String getDisplayPhoneNumber() {
		return this.displayPhoneNumber;
	}

	public void setDisplayPhoneNumber(String displayPhoneNumber) {
		this.displayPhoneNumber = displayPhoneNumber;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getRequester() {
		return this.requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	@Override
	public String toString() {
		return "Value [displayPhoneNumber=" + displayPhoneNumber + ", event=" + event + ", requester=" + requester
				+ "]";
	}

}
