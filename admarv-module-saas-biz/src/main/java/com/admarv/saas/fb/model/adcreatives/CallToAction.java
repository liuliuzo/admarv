package com.admarv.saas.fb.model.adcreatives;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class CallToAction implements Serializable {
	
	private static final long serialVersionUID = -2682841920564064887L;

	private String type;
	private Value value;

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Value getValue() {
		return this.value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CallToAction [type=" + type + ", value=" + value + "]";
	}
	
}
