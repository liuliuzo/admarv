package com.admarv.saas.fb.model.adcreatives;

import java.io.Serializable;
import java.util.List;

import com.admarv.saas.fb.model.insights.Paging;

/**
 * 
 * @author liuliu
 *
 */
public class Adcreatives implements Serializable {

	private static final long serialVersionUID = 8164975695255445892L;

	private List<Data> data;
	
	private Paging paging;

	public List<Data> getData() {
		return this.data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public Paging getPaging() {
		return this.paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	@Override
	public String toString() {
		return "Adcreatives [data=" + data + ", paging=" + paging + "]";
	}

}
