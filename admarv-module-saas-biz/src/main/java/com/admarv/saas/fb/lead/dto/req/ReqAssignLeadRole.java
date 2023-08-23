package com.admarv.saas.fb.lead.dto.req;

/**
 * 
 * @author liuliu
 *
 */
public class ReqAssignLeadRole {
    
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ReqAssignLeadRole [userId=" + userId + "]";
    }
}
