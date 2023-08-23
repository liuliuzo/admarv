package com.admarv.saas.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author liuliu
 *
 */
public class CustomerInfo implements Serializable {
	
	private static final long serialVersionUID = -362814959082289783L;

	private Integer id;

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

    private Date birthday;

    private String phoneNumber;

    private String skype;

    private String twitter;

    private String customerStatus;

    private String customerSource;

    private String otherRemarks;

    private String assignedTo;

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

    private Boolean delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCntct() {
        return cntct;
    }

    public void setCntct(String cntct) {
        this.cntct = cntct == null ? null : cntct.trim();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getFixedPhone() {
        return fixedPhone;
    }

    public void setFixedPhone(String fixedPhone) {
        this.fixedPhone = fixedPhone == null ? null : fixedPhone.trim();
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp == null ? null : whatsapp.trim();
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook == null ? null : facebook.trim();
    }

    public String getCustomerQuality() {
        return customerQuality;
    }

    public void setCustomerQuality(String customerQuality) {
        this.customerQuality = customerQuality == null ? null : customerQuality.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
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
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype == null ? null : skype.trim();
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter == null ? null : twitter.trim();
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus == null ? null : customerStatus.trim();
    }

    public String getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource == null ? null : customerSource.trim();
    }

    public String getOtherRemarks() {
        return otherRemarks;
    }

    public void setOtherRemarks(String otherRemarks) {
        this.otherRemarks = otherRemarks == null ? null : otherRemarks.trim();
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo == null ? null : assignedTo.trim();
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
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin == null ? null : linkedin.trim();
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail == null ? null : companyEmail.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize == null ? null : companySize.trim();
    }

    public String getProductRange() {
        return productRange;
    }

    public void setProductRange(String productRange) {
        this.productRange = productRange == null ? null : productRange.trim();
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope == null ? null : businessScope.trim();
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", cntct=").append(cntct);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", ownerName=").append(ownerName);
        sb.append(", position=").append(position);
        sb.append(", fixedPhone=").append(fixedPhone);
        sb.append(", whatsapp=").append(whatsapp);
        sb.append(", facebook=").append(facebook);
        sb.append(", customerQuality=").append(customerQuality);
        sb.append(", country=").append(country);
        sb.append(", gender=").append(gender);
        sb.append(", birthday=").append(birthday);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", skype=").append(skype);
        sb.append(", twitter=").append(twitter);
        sb.append(", customerStatus=").append(customerStatus);
        sb.append(", customerSource=").append(customerSource);
        sb.append(", otherRemarks=").append(otherRemarks);
        sb.append(", assignedTo=").append(assignedTo);
        sb.append(", lastContactTime=").append(lastContactTime);
        sb.append(", companyName=").append(companyName);
        sb.append(", industry=").append(industry);
        sb.append(", linkedin=").append(linkedin);
        sb.append(", companyEmail=").append(companyEmail);
        sb.append(", website=").append(website);
        sb.append(", address=").append(address);
        sb.append(", companySize=").append(companySize);
        sb.append(", productRange=").append(productRange);
        sb.append(", businessScope=").append(businessScope);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}