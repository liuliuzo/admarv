package com.admarv.saas.fb.model.post;

public class MessageTags {
	private String id;
	private String name;
	private Integer offset;
	private Integer length;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOffset() {
		return this.offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLength() {
		return this.length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
}
