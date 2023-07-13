package com.admarv.saas.fb.common.dto.req;

/**
 * 
 * @author liuliu
 *
 */
public class ReqLogin {
    
    private String userNam;
    private String pswrd;
    
    public String getUserNam() {
        return userNam;
    }
    
    public void setUserNam(String userNam) {
        this.userNam = userNam;
    }
    
    public String getPswrd() {
        return pswrd;
    }
    
    public void setPswrd(String pswrd) {
        this.pswrd = pswrd;
    }
    
    @Override
    public String toString() {
        return "ReqLogin [userNam=" + userNam + ", pswrd=" + pswrd + "]";
    }

}
