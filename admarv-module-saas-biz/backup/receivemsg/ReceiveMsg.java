package com.admarv.saas.whatsapp.model.receivemsg;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author liuliu
 *
 */
public class ReceiveMsg implements Serializable {
	
	private static final long serialVersionUID = 1160734400990010404L;
	
	private String object;
	private List<Entry> entry;

	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public List<Entry> getEntry() {
		return this.entry;
	}

	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}

	@Override
	public String toString() {
		return "ReceiveMsg [object=" + object + ", entry=" + entry + "]";
	}
}
