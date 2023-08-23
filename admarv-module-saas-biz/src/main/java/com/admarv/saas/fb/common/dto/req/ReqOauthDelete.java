package com.admarv.saas.fb.common.dto.req;

public class ReqOauthDelete {
    private String userId;
    private String pltfrm;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPltfrm() {
        return pltfrm;
    }

    public void setPltfrm(String pltfrm) {
        this.pltfrm = pltfrm;
    }

    @Override
    public String toString() {
        return "ReqOauthDelete [userId=" + userId + ", pltfrm=" + pltfrm + "]";
    }

}
