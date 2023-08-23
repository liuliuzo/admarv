package com.admarv.saas.mail.dto.req;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author liuliu
 *
 */
public class EmailRequest implements Serializable {
	
	private static final long serialVersionUID = 7057060316271752568L;
	
	private String userId;
	private String to;
	private String subject;
	private String text;
	private List<String> attachments;
	private String cc;
	private String bcc;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	@Override
	public String toString() {
		return "EmailRequest [userId=" + userId + ", to=" + to + ", subject=" + subject + ", text=" + text
				+ ", attachments=" + attachments + ", cc=" + cc + ", bcc=" + bcc + "]";
	}

}
