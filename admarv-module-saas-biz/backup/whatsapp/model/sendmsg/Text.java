package com.admarv.saas.whatsapp.model.sendmsg;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Text implements Serializable {

	private static final long serialVersionUID = 6899322330074295452L;

	private boolean previewUrl;
	private String body;

	public boolean isPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(boolean previewUrl) {
		this.previewUrl = previewUrl;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Text [previewUrl=" + previewUrl + ", body=" + body + "]";
	}

}
