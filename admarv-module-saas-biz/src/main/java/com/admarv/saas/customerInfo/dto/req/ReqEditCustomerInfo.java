package com.admarv.saas.customerInfo.dto.req;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author liuliu
 *
 */
public class ReqEditCustomerInfo implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    private int id;

    private String name;

    private String email;

    private String cntct;

    private String ownerId;

    private String ownerName;

    private String position;

    private String fixedPhone;

    private String whatsapp;

    private String facebook;

    private String customerQuality;

    private String country;

    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date birthday;

    private String phoneNumber;

    private String skype;

    private String twitter;

    private String customerStatus;

    private String customerSource;

    private String otherRemarks;

    private String assignedTo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date lastContactTime;

    private String companyName;

    private String industry;

    private String linkedin;

    private String companyEmail;

    private String website;

    private String address;

    private String companySize;

    private String productRange;

    private String businessScope;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCntct() {
		return cntct;
	}

	public void setCntct(String cntct) {
		this.cntct = cntct;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFixedPhone() {
		return fixedPhone;
	}

	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getCustomerQuality() {
		return customerQuality;
	}

	public void setCustomerQuality(String customerQuality) {
		this.customerQuality = customerQuality;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}

	public String getOtherRemarks() {
		return otherRemarks;
	}

	public void setOtherRemarks(String otherRemarks) {
		this.otherRemarks = otherRemarks;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Date getLastContactTime() {
		return lastContactTime;
	}

	public void setLastContactTime(Date lastContactTime) {
		this.lastContactTime = lastContactTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	public String getProductRange() {
		return productRange;
	}

	public void setProductRange(String productRange) {
		this.productRange = productRange;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	@Override
	public String toString() {
		return "ReqEditCustomerInfo [id=" + id + ", name=" + name + ", email=" + email + ", cntct=" + cntct
				+ ", ownerId=" + ownerId + ", ownerName=" + ownerName + ", position=" + position + ", fixedPhone="
				+ fixedPhone + ", whatsapp=" + whatsapp + ", facebook=" + facebook + ", customerQuality="
				+ customerQuality + ", country=" + country + ", gender=" + gender + ", birthday=" + birthday
				+ ", phoneNumber=" + phoneNumber + ", skype=" + skype + ", twitter=" + twitter + ", customerStatus="
				+ customerStatus + ", customerSource=" + customerSource + ", otherRemarks=" + otherRemarks
				+ ", assignedTo=" + assignedTo + ", lastContactTime=" + lastContactTime + ", companyName=" + companyName
				+ ", industry=" + industry + ", linkedin=" + linkedin + ", companyEmail=" + companyEmail + ", website="
				+ website + ", address=" + address + ", companySize=" + companySize + ", productRange=" + productRange
				+ ", businessScope=" + businessScope + "]";
	}

}