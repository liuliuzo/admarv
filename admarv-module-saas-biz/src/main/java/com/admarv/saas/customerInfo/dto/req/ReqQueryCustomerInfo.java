package com.admarv.saas.customerInfo.dto.req;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class ReqQueryCustomerInfo implements Serializable {
	
    private static final long serialVersionUID = 1L;

    private String name;

    private String email;

    private String cntct;

    private String ownerId;
    
    private String whatsapp;
    
    private int pageNum;
    
    private int pageSize;

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

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	@Override
	public String toString() {
		return "ReqQueryCustomerInfo [name=" + name + ", email=" + email + ", cntct=" + cntct + ", ownerId=" + ownerId
				+ ", whatsapp=" + whatsapp + ", pageNum=" + pageNum + ", pageSize=" + pageSize + "]";
	}
	
}