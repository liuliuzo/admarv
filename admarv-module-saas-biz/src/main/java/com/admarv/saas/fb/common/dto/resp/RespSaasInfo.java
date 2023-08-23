package com.admarv.saas.fb.common.dto.resp;

import java.io.Serializable;

public class RespSaasInfo implements Serializable {

	private static final long serialVersionUID = 5598584013928656312L;

	private String whatsApp;

	public String getWhatsApp() {
		return whatsApp;
	}

	public void setWhatsApp(String whatsApp) {
		this.whatsApp = whatsApp;
	}

	@Override
	public String toString() {
		return "RespSaasInfo [whatsApp=" + whatsApp + "]";
	}
}
