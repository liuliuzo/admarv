package com.admarv.saas.fb.model.adcreatives;

import java.io.Serializable;

public class VideoData implements Serializable {

	private static final long serialVersionUID = 7054177847273375278L;

	private String videoId;
	private String title;
	private String message;
	private String linkDescription;
	private CallToAction callToAction;
	private String imageUrl;
	private String imageHash;

	public String getVideoId() {
		return this.videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
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

	public String getLinkDescription() {
		return this.linkDescription;
	}

	public void setLinkDescription(String linkDescription) {
		this.linkDescription = linkDescription;
	}

	public CallToAction getCallToAction() {
		return this.callToAction;
	}

	public void setCallToAction(CallToAction callToAction) {
		this.callToAction = callToAction;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageHash() {
		return this.imageHash;
	}

	public void setImageHash(String imageHash) {
		this.imageHash = imageHash;
	}

	@Override
	public String toString() {
		return "VideoData [videoId=" + videoId + ", title=" + title + ", message=" + message + ", linkDescription="
				+ linkDescription + ", callToAction=" + callToAction + ", imageUrl=" + imageUrl + ", imageHash="
				+ imageHash + "]";
	}
	
}
