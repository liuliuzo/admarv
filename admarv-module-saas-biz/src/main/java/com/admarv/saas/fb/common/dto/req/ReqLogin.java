package com.admarv.saas.fb.common.dto.req;

/**
 * 
 * @author liuliu
 *
 */
public class ReqLogin {
    
    private String userName;
    private String pswrd;
    
    public String getUserName() {
        return userName;
    }
    
	public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPswrd() {
        return pswrd;
    }
    
    public void setPswrd(String pswrd) {
        this.pswrd = pswrd;
    }
    
    @Override
    public String toString() {
        return "ReqLogin [userNam=" + userName + ", pswrd=" + pswrd + "]";
    }

}
