package com.admarv.saas.fb.lead.domain.model;

import java.io.Serializable;

/**
 * 客户化表单
 * 
 * @author liuliu
 *
 */
public class LeadCust implements Serializable {
    
    private static final long serialVersionUID = -730386426643839640L;
    
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
     * 当前集萃表单 :TODO 当前使用表单需要重新设计
     */
    private String whatapp;
    
    private String yourWhatsapp;
    
    private String phoneNumber;
    
    private String whichIndustryDoYouComeFrom;
    
    private String whichProductsDoYouIntersted;
    
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
        return "LeadCust [jobTitle=" + jobTitle + ", distributorOrCustomer=" + distributorOrCustomer
                + ", yourScrewSpecifications=" + yourScrewSpecifications + ", yourCompanyName=" + yourCompanyName
                + ", quantityRequired=" + quantityRequired + ", whatapp=" + whatapp + ", phoneNumber=" + phoneNumber
                + ", whichIndustryDoYouComeFrom=" + whichIndustryDoYouComeFrom + ", yourWhatsapp=" + yourWhatsapp
                + ", whichProductsDoYouIntersted=" + whichProductsDoYouIntersted + "]";
    }

}
