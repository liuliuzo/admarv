package com.admarv.saas.chat.whatsapp.model.webhook;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author liuliu
 *
 */
public class Entry implements Serializable {
	
	private static final long serialVersionUID = 4368831051592137315L;
	private String id;
	private List<Changes> changes;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Changes> getChanges() {
		return this.changes;
	}

	public void setChanges(List<Changes> changes) {
		this.changes = changes;
	}

	@Override
	public String toString() {
		return "Entry [id=" + id + ", changes=" + changes + "]";
	}
	
}
