package com.admarv.saas.whatsapp.model.receivemsg;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Changes implements Serializable {
	
	private static final long serialVersionUID = 4673104130948024314L;
	
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
}
