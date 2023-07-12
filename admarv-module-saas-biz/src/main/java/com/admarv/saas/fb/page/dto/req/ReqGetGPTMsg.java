package com.admarv.saas.fb.page.dto.req;

/**
 * 
 * @author liuliu
 *
 */
public class ReqGetGPTMsg {
    private String user;
    private String msg;
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ReqGetGPTMsg [user=" + user + ", msg=" + msg + "]";
    }
    
}
