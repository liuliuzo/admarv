package com.admarv.saas.fb.lead.dto.resp;

/**
 * 广告线索询盘响应
 * 
 * @author liuliu
 *
 */
public class RespLeadGenFile {
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
     * 地区
     */
    private String regn;
    
    /**
     * 来源
     */
    private String rsrc;
    
    /**
     * job_title
     */
    private String jobTitle;
    
    /**
     * are_you_a_distributor_or_a_final_customer
     */
    private String distributorOrCustomer;
    
    /**
     * your_screw_specifications
     */
    private String yourScrewSpecifications;
    
    /**
     * your_company_name
     */
    private String yourCompanyName;
    
    /**
     * quantity_required
     */
    private String quantityRequired;
    
    /**
     * whatsapp
     */
    private String whatapp;
    
    /**
     * 另外一份表单
     */
    private String phoneNumber;
    
    private String whichIndustryDoYouComeFrom;
    
    private String yourWhatsapp;
    
    private String whichProductsDoYouIntersted;

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

    public String getRegn() {
        return regn;
    }

    public void setRegn(String regn) {
        this.regn = regn;
    }

    public String getRsrc() {
        return rsrc;
    }

    public void setRsrc(String rsrc) {
        this.rsrc = rsrc;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDistributorOrCustomer() {
        return distributorOrCustomer;
    }

    public void setDistributorOrCustomer(String distributorOrCustomer) {
        this.distributorOrCustomer = distributorOrCustomer;
    }

    public String getYourScrewSpecifications() {
        return yourScrewSpecifications;
    }

    public void setYourScrewSpecifications(String yourScrewSpecifications) {
        this.yourScrewSpecifications = yourScrewSpecifications;
    }

    public String getYourCompanyName() {
        return yourCompanyName;
    }

    public void setYourCompanyName(String yourCompanyName) {
        this.yourCompanyName = yourCompanyName;
    }

    public String getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(String quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    public String getWhatapp() {
        return whatapp;
    }

    public void setWhatapp(String whatapp) {
        this.whatapp = whatapp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWhichIndustryDoYouComeFrom() {
        return whichIndustryDoYouComeFrom;
    }

    public void setWhichIndustryDoYouComeFrom(String whichIndustryDoYouComeFrom) {
        this.whichIndustryDoYouComeFrom = whichIndustryDoYouComeFrom;
    }

    public String getYourWhatsapp() {
        return yourWhatsapp;
    }

    public void setYourWhatsapp(String yourWhatsapp) {
        this.yourWhatsapp = yourWhatsapp;
    }

    public String getWhichProductsDoYouIntersted() {
        return whichProductsDoYouIntersted;
    }

    public void setWhichProductsDoYouIntersted(String whichProductsDoYouIntersted) {
        this.whichProductsDoYouIntersted = whichProductsDoYouIntersted;
    }

    @Override
    public String toString() {
        return "RespLeadGenFile [nam=" + nam + ", email=" + email + ", crteTm=" + crteTm + ", regn=" + regn + ", rsrc="
                + rsrc + ", jobTitle=" + jobTitle + ", distributorOrCustomer=" + distributorOrCustomer
                + ", yourScrewSpecifications=" + yourScrewSpecifications + ", yourCompanyName=" + yourCompanyName
                + ", quantityRequired=" + quantityRequired + ", whatapp=" + whatapp + ", phoneNumber=" + phoneNumber
                + ", whichIndustryDoYouComeFrom=" + whichIndustryDoYouComeFrom + ", yourWhatsapp=" + yourWhatsapp
                + ", whichProductsDoYouIntersted=" + whichProductsDoYouIntersted + "]";
    }

}
