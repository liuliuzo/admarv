package com.admarv.saas.whatsapp.model.receivemsg;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Contacts implements Serializable {
	
	private static final long serialVersionUID = 2524219536851187524L;
	
	private Profile profile;
	private String waId;

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getWaId() {
		return this.waId;
	}

	public void setWaId(String waId) {
		this.waId = waId;
	}

	@Override
	public String toString() {
		return "Contacts [profile=" + profile + ", waId=" + waId + "]";
	}
}
