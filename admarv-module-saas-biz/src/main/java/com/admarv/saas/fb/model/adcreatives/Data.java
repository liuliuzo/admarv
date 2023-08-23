package com.admarv.saas.fb.model.adcreatives;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Data implements Serializable {

	private static final long serialVersionUID = 2285716666463803817L;

	private String id; 		  // 唯一标识符
	private String accountId; // 账户ID
	private String body;      // 内容
	private String authorizationCategory; // 授权类别
	private String actorId;               // 操作者ID
	private DegreesOfFreedomSpec degreesOfFreedomSpec; // 自由度规范
	private String enableDirectInstall;                // 启用直接安装
	private String enableLaunchInstantApp; 			   // 启用即时应用程序启动
	private String instagramActorId; 				   // Instagram操作者ID
	private String instagramPermalinkUrl; 			   // Instagram永久链接URL
	private String instagramUserId; 				   // Instagram用户ID
	private String name; 							   // 名称
	private ObjectStorySpec objectStorySpec; 		   // 对象故事规范
	private String objectType; 						   // 对象类型
	private String thumbnailUrl; 					   // 缩略图URL
	private String title; 							   // 标题

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAuthorizationCategory() {
		return this.authorizationCategory;
	}

	public void setAuthorizationCategory(String authorizationCategory) {
		this.authorizationCategory = authorizationCategory;
	}

	public String getActorId() {
		return this.actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public DegreesOfFreedomSpec getDegreesOfFreedomSpec() {
		return this.degreesOfFreedomSpec;
	}

	public void setDegreesOfFreedomSpec(DegreesOfFreedomSpec degreesOfFreedomSpec) {
		this.degreesOfFreedomSpec = degreesOfFreedomSpec;
	}

	public String getEnableDirectInstall() {
		return this.enableDirectInstall;
	}

	public void setEnableDirectInstall(String enableDirectInstall) {
		this.enableDirectInstall = enableDirectInstall;
	}

	public String getEnableLaunchInstantApp() {
		return this.enableLaunchInstantApp;
	}

	public void setEnableLaunchInstantApp(String enableLaunchInstantApp) {
		this.enableLaunchInstantApp = enableLaunchInstantApp;
	}

	public String getInstagramActorId() {
		return this.instagramActorId;
	}

	public void setInstagramActorId(String instagramActorId) {
		this.instagramActorId = instagramActorId;
	}

	public String getInstagramPermalinkUrl() {
		return this.instagramPermalinkUrl;
	}

	public void setInstagramPermalinkUrl(String instagramPermalinkUrl) {
		this.instagramPermalinkUrl = instagramPermalinkUrl;
	}

	public String getInstagramUserId() {
		return this.instagramUserId;
	}

	public void setInstagramUserId(String instagramUserId) {
		this.instagramUserId = instagramUserId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObjectStorySpec getObjectStorySpec() {
		return this.objectStorySpec;
	}

	public void setObjectStorySpec(ObjectStorySpec objectStorySpec) {
		this.objectStorySpec = objectStorySpec;
	}

	public String getObjectType() {
		return this.objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getThumbnailUrl() {
		return this.thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", accountId=" + accountId + ", body=" + body + ", authorizationCategory="
				+ authorizationCategory + ", actorId=" + actorId + ", degreesOfFreedomSpec=" + degreesOfFreedomSpec
				+ ", enableDirectInstall=" + enableDirectInstall + ", enableLaunchInstantApp=" + enableLaunchInstantApp
				+ ", instagramActorId=" + instagramActorId + ", instagramPermalinkUrl=" + instagramPermalinkUrl
				+ ", instagramUserId=" + instagramUserId + ", name=" + name + ", objectStorySpec=" + objectStorySpec
				+ ", objectType=" + objectType + ", thumbnailUrl=" + thumbnailUrl + ", title=" + title + "]";
	}

}
