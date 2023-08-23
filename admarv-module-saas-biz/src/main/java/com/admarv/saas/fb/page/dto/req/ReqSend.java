package com.admarv.saas.fb.page.dto.req;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 发送贴文的 DTO
 * 
 * @author liuliu
 *
 */
public class ReqSend {
    
    private List<String> fileList;
    private String msg;
    private String pageId;
    private String userId;
    
    public List<String> getFileList() {
        return fileList;
    }
    
    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ReqSend [fileList=" + fileList + ", msg=" + msg + ", pageId=" + pageId + ", userId=" + userId + "]";
	}
    
}
