package com.admarv.saas.model;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class SysUserFbBind implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;

    private String userId;

    private String userName;

    private String pageId;

    private String pageName;

    private String adAccountId;

    private String adAccountName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    public String getAdAccountId() {
        return adAccountId;
    }

    public void setAdAccountId(String adAccountId) {
        this.adAccountId = adAccountId == null ? null : adAccountId.trim();
    }

    public String getAdAccountName() {
        return adAccountName;
    }

    public void setAdAccountName(String adAccountName) {
        this.adAccountName = adAccountName == null ? null : adAccountName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", pageId=").append(pageId);
        sb.append(", pageName=").append(pageName);
        sb.append(", adAccountId=").append(adAccountId);
        sb.append(", adAccountName=").append(adAccountName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
}