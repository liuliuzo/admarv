package com.admarv.saas.whatsapp.model.receivemsg;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author liuliu
 *
 */
public class Entry implements Serializable {
	
	private static final long serialVersionUID = 1016143139124761104L;
	
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
