package com.admarv.saas.fb.common.dto.req;

/**
 * 
 * @author liuliu
 *
 */
public class Register {
    
    private String userName;
    private String pswrd;
    private String email;
    private String phone;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Register [userName=" + userName + ", pswrd=" + pswrd + ", email=" + email + ", phone=" + phone + "]";
    }

}
