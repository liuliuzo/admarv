package com.admarv.saas.fb.ads.dto.resp;

/**
 * 广告线索询盘响应
 * 
 * @author liuliu
 *
 */
public class RespLeadGen {
    /**
     * 姓名
     */
    private String nam;
    /**
     * 邮件
     */
    private String email;
    
    /**
     * 表单创建时间
     */
    private String crteTm;
    
    /**
     * 询盘单状态
     */
    private String leadStat;
    
    /**
     * 地区
     */
    private String regn;
    
    /**
     * 跟进状态
     */
    private String flwpStat;
    
    /**
     * 来源
     */
    private String rsrc;

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCrteTm() {
        return crteTm;
    }

    public void setCrteTm(String crteTm) {
        this.crteTm = crteTm;
    }

    public String getLeadStat() {
        return leadStat;
    }

    public void setLeadStat(String leadStat) {
        this.leadStat = leadStat;
    }

    public String getRegn() {
        return regn;
    }

    public void setRegn(String regn) {
        this.regn = regn;
    }

    public String getFlwpStat() {
        return flwpStat;
    }

    public void setFlwpStat(String flwpStat) {
        this.flwpStat = flwpStat;
    }

    public String getRsrc() {
        return rsrc;
    }

    public void setRsrc(String rsrc) {
        this.rsrc = rsrc;
    }

    @Override
    public String toString() {
        return "RespLeadGen [nam=" + nam + ", email=" + email + ", crteTm=" + crteTm + ", leadStat=" + leadStat
                + ", regn=" + regn + ", flwpStat=" + flwpStat + ", rsrc=" + rsrc + "]";
    }

}
