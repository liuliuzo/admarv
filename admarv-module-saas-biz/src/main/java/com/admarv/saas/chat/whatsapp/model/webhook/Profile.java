package com.admarv.saas.chat.whatsapp.model.webhook;

import java.io.Serializable;

public class Profile implements Serializable{
	
	private static final long serialVersionUID = 4651855177839245218L;
	
	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Profile [name=" + name + "]";
	}

}
