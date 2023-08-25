package com.admarv.saas.fb.page.dto.req;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author liuliu
 *
 */
public class ReqPublishVideoScheduler implements Serializable {
	
	private static final long serialVersionUID = 5411961334379965851L;
	
	private String userId;
	private String fileName;
	private String msg;
	private String pageId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
	private Date sendDate;

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

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	@Override
	public String toString() {
		return "ReqPublishVideoScheduler [userId=" + userId + ", fileName=" + fileName + ", msg=" + msg + ", pageId=" + pageId + ", sendDate=" + sendDate + "]";
	}
}
