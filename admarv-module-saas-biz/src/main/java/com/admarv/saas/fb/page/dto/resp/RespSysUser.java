package com.admarv.saas.fb.page.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class RespSysUser {

    private String userName;
    private String userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RespSysUser [userName=" + userName + ", userId=" + userId + "]";
    }

}
