package com.admarv.saas.chat.whatsapp.model.webhook;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Changes implements Serializable{
	
	private static final long serialVersionUID = 1059733644234342253L;
	
	private Value value;
	private String field;

	public Value getValue() {
		return this.value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "Changes [value=" + value + ", field=" + field + "]";
	}
}
