package com.admarv.saas.fb.model.post;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author liuliu
 *
 */
public class Post implements Serializable {
	
	private static final long serialVersionUID = 7575756362322566181L;
	
	private String id;
	private List<Actions> actions;
	private AdminCreator adminCreator;
	private String canReplyPrivately;
	private Coordinates coordinates;
	private String createdTime;
	private From from;
	private String fullPicture;
	private String icon;
	private String instagramEligibility;
	private String isEligibleForPromotion;
	private String isExpired;
	private String isHidden;
	private String isInstagramEligible;
	private String isPopular;
	private String isPublished;
	private String isSpherical;
	private String message;
	private List<MessageTags> messageTags;
	private String permalinkUrl;
	private Privacy privacy;
	private String promotableId;
	private String promotionStatus;
	private String statusType;
	private String subscribed;
	private String timelineVisibility;
	private String updatedTime;
	private List<String> videoBuyingEligibility;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Actions> getActions() {
		return this.actions;
	}

	public void setActions(List<Actions> actions) {
		this.actions = actions;
	}

	public AdminCreator getAdminCreator() {
		return this.adminCreator;
	}

	public void setAdminCreator(AdminCreator adminCreator) {
		this.adminCreator = adminCreator;
	}

	public String getCanReplyPrivately() {
		return this.canReplyPrivately;
	}

	public void setCanReplyPrivately(String canReplyPrivately) {
		this.canReplyPrivately = canReplyPrivately;
	}

	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public String getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public From getFrom() {
		return this.from;
	}

	public void setFrom(From from) {
		this.from = from;
	}

	public String getFullPicture() {
		return this.fullPicture;
	}

	public void setFullPicture(String fullPicture) {
		this.fullPicture = fullPicture;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getInstagramEligibility() {
		return this.instagramEligibility;
	}

	public void setInstagramEligibility(String instagramEligibility) {
		this.instagramEligibility = instagramEligibility;
	}

	public String getIsEligibleForPromotion() {
		return this.isEligibleForPromotion;
	}

	public void setIsEligibleForPromotion(String isEligibleForPromotion) {
		this.isEligibleForPromotion = isEligibleForPromotion;
	}

	public String getIsExpired() {
		return this.isExpired;
	}

	public void setIsExpired(String isExpired) {
		this.isExpired = isExpired;
	}

	public String getIsHidden() {
		return this.isHidden;
	}

	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}

	public String getIsInstagramEligible() {
		return this.isInstagramEligible;
	}

	public void setIsInstagramEligible(String isInstagramEligible) {
		this.isInstagramEligible = isInstagramEligible;
	}

	public String getIsPopular() {
		return this.isPopular;
	}

	public void setIsPopular(String isPopular) {
		this.isPopular = isPopular;
	}

	public String getIsPublished() {
		return this.isPublished;
	}

	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}

	public String getIsSpherical() {
		return this.isSpherical;
	}

	public void setIsSpherical(String isSpherical) {
		this.isSpherical = isSpherical;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<MessageTags> getMessageTags() {
		return this.messageTags;
	}

	public void setMessageTags(List<MessageTags> messageTags) {
		this.messageTags = messageTags;
	}

	public String getPermalinkUrl() {
		return this.permalinkUrl;
	}

	public void setPermalinkUrl(String permalinkUrl) {
		this.permalinkUrl = permalinkUrl;
	}

	public Privacy getPrivacy() {
		return this.privacy;
	}

	public void setPrivacy(Privacy privacy) {
		this.privacy = privacy;
	}

	public String getPromotableId() {
		return this.promotableId;
	}

	public void setPromotableId(String promotableId) {
		this.promotableId = promotableId;
	}

	public String getPromotionStatus() {
		return this.promotionStatus;
	}

	public void setPromotionStatus(String promotionStatus) {
		this.promotionStatus = promotionStatus;
	}

	public String getStatusType() {
		return this.statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getSubscribed() {
		return this.subscribed;
	}

	public void setSubscribed(String subscribed) {
		this.subscribed = subscribed;
	}

	public String getTimelineVisibility() {
		return this.timelineVisibility;
	}

	public void setTimelineVisibility(String timelineVisibility) {
		this.timelineVisibility = timelineVisibility;
	}

	public String getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public List<String> getVideoBuyingEligibility() {
		return this.videoBuyingEligibility;
	}

	public void setVideoBuyingEligibility(List<String> videoBuyingEligibility) {
		this.videoBuyingEligibility = videoBuyingEligibility;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", actions=" + actions + ", adminCreator=" + adminCreator + ", canReplyPrivately="
				+ canReplyPrivately + ", coordinates=" + coordinates + ", createdTime=" + createdTime + ", from=" + from
				+ ", fullPicture=" + fullPicture + ", icon=" + icon + ", instagramEligibility=" + instagramEligibility
				+ ", isEligibleForPromotion=" + isEligibleForPromotion + ", isExpired=" + isExpired + ", isHidden="
				+ isHidden + ", isInstagramEligible=" + isInstagramEligible + ", isPopular=" + isPopular
				+ ", isPublished=" + isPublished + ", isSpherical=" + isSpherical + ", message=" + message
				+ ", messageTags=" + messageTags + ", permalinkUrl=" + permalinkUrl + ", privacy=" + privacy
				+ ", promotableId=" + promotableId + ", promotionStatus=" + promotionStatus + ", statusType="
				+ statusType + ", subscribed=" + subscribed + ", timelineVisibility=" + timelineVisibility
				+ ", updatedTime=" + updatedTime + ", videoBuyingEligibility=" + videoBuyingEligibility + "]";
	}

}
