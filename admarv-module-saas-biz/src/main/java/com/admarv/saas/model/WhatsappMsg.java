package com.admarv.saas.model;

import java.io.Serializable;

public class WhatsappMsg implements Serializable {
	
    private Integer id;

    private String whatsappFrom;

    private String whatsappTo;

    private String msg;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWhatsappFrom() {
        return whatsappFrom;
    }

    public void setWhatsappFrom(String whatsappFrom) {
        this.whatsappFrom = whatsappFrom == null ? null : whatsappFrom.trim();
    }

    public String getWhatsappTo() {
        return whatsappTo;
    }

    public void setWhatsappTo(String whatsappTo) {
        this.whatsappTo = whatsappTo == null ? null : whatsappTo.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", whatsappFrom=").append(whatsappFrom);
        sb.append(", whatsappTo=").append(whatsappTo);
        sb.append(", msg=").append(msg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}