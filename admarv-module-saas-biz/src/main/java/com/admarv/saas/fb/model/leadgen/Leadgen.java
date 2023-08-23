package com.admarv.saas.fb.model.leadgen;

import java.io.Serializable;

public class Leadgen implements Serializable {
	
	private static final long serialVersionUID = 3770684831887893732L;
	
	private String field;
	private Value value;

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Value getValue() {
		return this.value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Leadgen [field=" + field + ", value=" + value + "]";
	}
}
