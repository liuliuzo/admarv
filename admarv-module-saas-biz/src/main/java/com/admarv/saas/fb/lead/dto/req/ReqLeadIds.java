package com.admarv.saas.fb.lead.dto.req;

import java.util.List;

/**
 * 
 * @author liuliu
 *
 */
public class ReqLeadIds {
    
    private List<Integer> ids;
    private String userId;
    
    public List<Integer> getIds() {
        return ids;
    }
    
    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ReqLeadIds [ids=" + ids + ", userId=" + userId + "]";
    }
}
