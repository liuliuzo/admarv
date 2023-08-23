package com.admarv.saas.fb.lead.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class RespAllUser {
    private String userId;

    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ReqAllUser [userId=" + userId + ", userName=" + userName + "]";
    }

}
