package com.admarv.saas.customerInfo.dto.req;

/**
 * 
 * @author liuliu
 *
 */
public class ReqSelectCustomerInfo {
	
	private String name;

	private String email;

	private String cntct;

	private String ownerId;

	private String whatsapp;

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

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	@Override
	public String toString() {
		return "ReqSelectCustomerInfo [name=" + name + ", email=" + email + ", cntct=" + cntct + ", ownerId=" + ownerId + ", whatsapp=" + whatsapp + "]";
	}
}
