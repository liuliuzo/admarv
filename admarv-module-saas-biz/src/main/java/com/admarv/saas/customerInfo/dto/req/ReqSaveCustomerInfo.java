package com.admarv.saas.customerInfo.dto.req;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class ReqSaveCustomerInfo implements Serializable {
	
    private static final long serialVersionUID = 1L;

    private String name;

    private String email;

    private String cntct;

    private String ownerId;
    
    private String ownerName;
    
    private String whatsapp;
   
    private String country;
    
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

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "ReqSaveCustomerInfo [name=" + name + ", email=" + email + ", cntct=" + cntct + ", ownerId=" + ownerId + ", ownerName=" + ownerName + ", whatsapp=" + whatsapp + ", country=" + country + "]";
	}
}