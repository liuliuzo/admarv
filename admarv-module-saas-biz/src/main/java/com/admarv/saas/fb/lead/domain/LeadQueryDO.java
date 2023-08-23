package com.admarv.saas.fb.lead.domain;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author liuliu
 *
 */
public class LeadQueryDO {

    private String userId;
    private String userName;
    private String leadAuality;
    private String flwpStat;
    private String leadStat;
    private String rsrc;
    private String cntryCd;
    private String startDate;
    private String endDate;
    private int pageSize;
    private int pageNum;
    private String ownerId;
    private String ownerName;
    private String name;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLeadAuality() {
        return leadAuality;
    }

    public void setLeadAuality(String leadAuality) {
        this.leadAuality = leadAuality;
    }

    public String getFlwpStat() {
        return flwpStat;
    }

    public void setFlwpStat(String flwpStat) {
        this.flwpStat = flwpStat;
    }

    public String getLeadStat() {
        return leadStat;
    }

    public void setLeadStat(String leadStat) {
        this.leadStat = leadStat;
    }

    public String getRsrc() {
        return rsrc;
    }

    public void setRsrc(String rsrc) {
        this.rsrc = rsrc;
    }

    public String getCntryCd() {
        return cntryCd;
    }

    public void setCntryCd(String cntryCd) {
        this.cntryCd = cntryCd;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isNotBlank(name)) {
            this.name = "%" + name + "%";
        }
    }

    @Override
    public String toString() {
        return "LeadQueryDO [userId=" + userId + ", userName=" + userName + ", leadAuality=" + leadAuality
                + ", flwpStat=" + flwpStat + ", leadStat=" + leadStat + ", rsrc=" + rsrc + ", cntryCd=" + cntryCd
                + ", startDate=" + startDate + ", endDate=" + endDate + ", pageSize=" + pageSize + ", pageNum="
                + pageNum + ", ownerId=" + ownerId + ", ownerName=" + ownerName + ", name=" + name + "]";
    }

}
