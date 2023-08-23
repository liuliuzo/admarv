package com.admarv.saas.chat.whatsapp.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class GlobalWhatsAppNotice {
	
	private String fromNum;
	private int count;

	public String getFromNum() {
		return fromNum;
	}

	public void setFromNum(String fromNum) {
		this.fromNum = fromNum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "GlobalWhatsAppNotice [fromNum=" + fromNum + ", count=" + count + "]";
	}
}
