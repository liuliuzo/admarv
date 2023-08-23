package com.admarv.saas.fb.lead.dto.req;

public class ReqDeleteLeadRole {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ReqDeleteLeadRole [userId=" + userId + "]";
    }
}
