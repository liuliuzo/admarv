package com.admarv.saas.whatsapp.model.receivemsg;

import java.io.Serializable;

public class Profile implements Serializable {
	
	private static final long serialVersionUID = -7822379660793239822L;
	
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
