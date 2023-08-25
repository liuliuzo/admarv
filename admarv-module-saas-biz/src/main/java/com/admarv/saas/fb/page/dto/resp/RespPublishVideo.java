package com.admarv.saas.fb.page.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class RespPublishVideo {
	
	private String id;
	private String postId;
	private String timelineId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getTimelineId() {
		return timelineId;
	}

	public void setTimelineId(String timelineId) {
		this.timelineId = timelineId;
	}

	@Override
	public String toString() {
		return "RespPublishVideo [id=" + id + ", postId=" + postId + ", timelineId=" + timelineId + "]";
	}

}
