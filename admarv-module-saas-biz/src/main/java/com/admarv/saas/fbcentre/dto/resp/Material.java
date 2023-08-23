package com.admarv.saas.fbcentre.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class Material {

	/**
	 * 正文
	 */
	private String body;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 描述
	 */
	private String desc;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Material [body=" + body + ", title=" + title + ", desc=" + desc + "]";
	}

}
