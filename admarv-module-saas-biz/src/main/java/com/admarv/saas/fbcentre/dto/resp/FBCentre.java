package com.admarv.saas.fbcentre.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class FBCentre {

	/**
	 * 数据总览
	 */
	private Header header;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "FBCentre [header=" + header + "]";
	}
}
