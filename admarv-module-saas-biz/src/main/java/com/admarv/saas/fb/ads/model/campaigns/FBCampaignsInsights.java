package com.admarv.saas.fb.ads.model.campaigns;

import java.io.Serializable;
import java.util.List;

public class FBCampaignsInsights implements Serializable {
	
	private static final long serialVersionUID = -8661898595430675265L;
	
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
		return "CampaignsInsights [data=" + data + ", paging=" + paging + "]";
	}
}
