package com.admarv.saas.fb.page.dto.req;

import java.util.List;

/**
 * 定时发送贴文的 DTO
 * 
 * @author liuliu
 *
 */
public class ReqSendSched {
    
    private List<String> fileList;
    private String msg;
    private String pageId;
    private String userId;
    private String sendDt;
    
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

    public String getSendDt() {
        return sendDt;
    }

    public void setSendDt(String sendDt) {
        this.sendDt = sendDt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ReqSendSched [fileList=" + fileList + ", msg=" + msg + ", pageId=" + pageId + ", userId=" + userId
                + ", sendDt=" + sendDt + "]";
    }
}
