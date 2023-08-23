package com.admarv.saas.whatsapp.model.templateCategoryUpdate;

import java.io.Serializable;

public class TemplateCategoryUpdate implements Serializable  {
	
	private static final long serialVersionUID = 5155160333875475813L;
	
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
		return "TemplateCategoryUpdate [field=" + field + ", value=" + value + "]";
	}
}
