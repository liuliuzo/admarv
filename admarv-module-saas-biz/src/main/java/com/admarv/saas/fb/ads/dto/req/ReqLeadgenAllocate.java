package com.admarv.saas.fb.ads.dto.req;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class ReqLeadgenAllocate implements Serializable {

    private static final long serialVersionUID = 6248966534185766366L;

    private Integer id;

    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ReqLeadgenAllocate [id=" + id + ", userName=" + userName + "]";
    }

}
