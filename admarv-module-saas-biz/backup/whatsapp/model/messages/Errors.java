package com.admarv.saas.whatsapp.model.messages;

public class Errors {
	
	private Integer code;
	private String title;
	private String message;
	private ErrorData errorData;

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorData getErrorData() {
		return this.errorData;
	}

	public void setErrorData(ErrorData errorData) {
		this.errorData = errorData;
	}

	@Override
	public String toString() {
		return "Errors [code=" + code + ", title=" + title + ", message=" + message + ", errorData=" + errorData + "]";
	}
}
