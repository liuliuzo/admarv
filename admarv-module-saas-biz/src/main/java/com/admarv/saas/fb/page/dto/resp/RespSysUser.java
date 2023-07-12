package com.admarv.saas.fb.page.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class RespSysUser {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "RespSysUser [userName=" + userName + "]";
    }

}
