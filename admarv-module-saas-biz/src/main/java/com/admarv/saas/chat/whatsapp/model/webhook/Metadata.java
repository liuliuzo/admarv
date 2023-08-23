package com.admarv.saas.chat.whatsapp.model.webhook;

import java.io.Serializable;

public class Metadata  implements Serializable {
	
	private static final long serialVersionUID = 7193505791031984091L;
	
	private String displayPhoneNumber;
	private String phoneNumberId;

	public String getDisplayPhoneNumber() {
		return this.displayPhoneNumber;
	}

	public void setDisplayPhoneNumber(String displayPhoneNumber) {
		this.displayPhoneNumber = displayPhoneNumber;
	}

	public String getPhoneNumberId() {
		return this.phoneNumberId;
	}

	public void setPhoneNumberId(String phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}

	@Override
	public String toString() {
		return "Metadata [displayPhoneNumber=" + displayPhoneNumber + ", phoneNumberId=" + phoneNumberId + "]";
	}
	
}
