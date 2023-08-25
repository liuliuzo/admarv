package com.admarv.saas.fb.page.dto.req;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class ReqPublishVideo implements Serializable{
	
	private static final long serialVersionUID = -5973652528514077845L;
	
	private String userId;
	private String fileName;
	private String msg;
	private String pageId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	@Override
	public String toString() {
		return "ReqPublishVideo [userId=" + userId + ", fileName=" + fileName + ", msg=" + msg + ", pageId=" + pageId
				+ "]";
	}
}
