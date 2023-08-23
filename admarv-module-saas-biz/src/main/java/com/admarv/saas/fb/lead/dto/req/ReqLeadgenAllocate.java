package com.admarv.saas.fb.lead.dto.req;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class ReqLeadgenAllocate implements Serializable {

    private static final long serialVersionUID = 6248966534185766366L;

    /**
     * 询盘ID
     */
    private Integer id;

    private String userId;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ReqLeadgenAllocate [id=" + id + ", userId=" + userId + "]";
    }

}
