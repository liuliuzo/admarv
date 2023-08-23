package com.admarv.saas.fb.model.ads.dto.resp;

import java.io.Serializable;

public class RespGetAds implements Serializable {

	private static final long serialVersionUID = 2647426243504876244L;

	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RespGetAds [id=" + id + ", name=" + name + "]";
	}

}
