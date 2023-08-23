package com.admarv.saas.whatsapp.model.messages;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Metadata implements Serializable {

	private static final long serialVersionUID = 6028324537481899992L;

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
