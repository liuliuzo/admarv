package com.admarv.saas.fb.model.post;

public class Privacy {
	private String allow;
	private String deny;
	private String description;
	private String friends;
	private String value;

	public String getAllow() {
		return this.allow;
	}

	public void setAllow(String allow) {
		this.allow = allow;
	}

	public String getDeny() {
		return this.deny;
	}

	public void setDeny(String deny) {
		this.deny = deny;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFriends() {
		return this.friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
