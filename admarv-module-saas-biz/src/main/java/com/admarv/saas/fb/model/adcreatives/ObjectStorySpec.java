package com.admarv.saas.fb.model.adcreatives;

import java.io.Serializable;

public class ObjectStorySpec implements Serializable {
	
	private static final long serialVersionUID = 7212588398929579232L;
	
	private String pageId;
	private String instagramActorId;
	private VideoData videoData;

	public String getPageId() {
		return this.pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getInstagramActorId() {
		return this.instagramActorId;
	}

	public void setInstagramActorId(String instagramActorId) {
		this.instagramActorId = instagramActorId;
	}

	public VideoData getVideoData() {
		return this.videoData;
	}

	public void setVideoData(VideoData videoData) {
		this.videoData = videoData;
	}

	@Override
	public String toString() {
		return "ObjectStorySpec [pageId=" + pageId + ", instagramActorId=" + instagramActorId + ", videoData="
				+ videoData + "]";
	}
}
