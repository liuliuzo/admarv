package com.admarv.saas.fb.common.dto.req;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class ReqSubmitFBBind implements Serializable {
	
	private static final long serialVersionUID = -2284567573895693595L;

	/**
     * 绑定FB公共主页
     */
    private String pageId;
    
    private String pageName;
    /**
     * 绑定广告账户
     */
    private String adAccountId;
    
    private String adAccountName;

    /**
     * 用户名称
     */
    private String userName;
    
    /**
     * 用户ID
     */
    private String userID;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getAdAccountId() {
        return adAccountId;
    }

    public void setAdAccountId(String adAccountId) {
        this.adAccountId = adAccountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getAdAccountName() {
		return adAccountName;
	}

	public void setAdAccountName(String adAccountName) {
		this.adAccountName = adAccountName;
	}

	@Override
	public String toString() {
		return "ReqSubmitFBBind [pageId=" + pageId + ", pageName=" + pageName + ", adAccountId=" + adAccountId
				+ ", adAccountName=" + adAccountName + ", userName=" + userName + ", userID=" + userID + "]";
	}
	
}
