package com.admarv.saas.fb.model.adsets;

import java.util.List;

import com.admarv.saas.fb.model.insights.Paging;

/**
 * 
 * @author liuliu
 *
 */
public class AdSets {
	
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
		return "Campaigns [data=" + data + ", paging=" + paging + "]";
	}
	
}
