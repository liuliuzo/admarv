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
    private String user;
    
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
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public static void main(String[] args) throws Exception {
        ReqSend reqSend = new ReqSend();
        List<String> fileList = new ArrayList<>();
        fileList.add("/www/wwwroot/liuliu/fb/123.png");
        reqSend.setFileList(fileList);
        reqSend.setMsg("hello world");
        reqSend.setPageId("101151723027483");
        reqSend.setUser("1");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(reqSend);
        System.out.println(json);
    }
}
