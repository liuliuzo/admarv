package com.admarv.saas.chat.whatsapp.model.messages;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Profile implements Serializable  {

	private static final long serialVersionUID = 5915130143581144087L;
	
	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
