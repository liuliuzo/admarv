package com.admarv.saas.fb.model.pageEngagement;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Paging implements Serializable {
	
	private static final long serialVersionUID = -6200224841991052240L;
	
	private String previous;
	private String next;

	public String getPrevious() {
		return this.previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getNext() {
		return this.next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Paging [previous=" + previous + ", next=" + next + "]";
	}

}
