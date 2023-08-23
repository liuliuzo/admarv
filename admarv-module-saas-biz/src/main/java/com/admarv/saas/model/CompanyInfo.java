package com.admarv.saas.model;

import java.io.Serializable;

public class CompanyInfo implements Serializable {
    private Integer id;

    private String coId;

    private String coName;

    private String cntct;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoId() {
        return coId;
    }

    public void setCoId(String coId) {
        this.coId = coId == null ? null : coId.trim();
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName == null ? null : coName.trim();
    }

    public String getCntct() {
        return cntct;
    }

    public void setCntct(String cntct) {
        this.cntct = cntct == null ? null : cntct.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", coId=").append(coId);
        sb.append(", coName=").append(coName);
        sb.append(", cntct=").append(cntct);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}