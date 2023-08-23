package com.admarv.saas.fb.common;

import com.admarv.saas.fb.model.insights.Paging;

/**
 * 同一响应格式
 * 
 * @author liuliu
 *
 */
public class PageResponse {

	private String code;

	private String message;

	private boolean success;

	private Object result;
	// 当前页
	private int pageNum;
	// 每页的数量
	private int pageSize;
	// 当前页的数量
	private long totalSize;

	/**
	 * FB翻页
	 */
	private Paging paging;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
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

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", message=" + message + ", success=" + success + ", result=" + result
				+ ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", totalSize=" + totalSize + ", paging=" + paging
				+ "]";
	}
}
