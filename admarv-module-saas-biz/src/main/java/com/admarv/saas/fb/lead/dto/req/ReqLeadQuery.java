package com.admarv.saas.fb.lead.dto.req;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class ReqLeadQuery implements Serializable {
    
    private static final long serialVersionUID = -3820731881075578776L;
    
    /**
     * 来源
     */
    private String rsrc;
    
    /**
     * saas平台用户ID
     */
    private String userId;
    
    /**
     * 询盘所属的sass平台用户名称
     */
    private String userName;
    
    /**
     * 跟单员ID
     */
    private String ownerId;
    
    /**
     * 跟单员姓名
     */
    private String ownerName;
    
    /**
     * 询盘质量
     */
    private String leadAuality;
    
    /**
     * 跟踪状态：01-待跟进，02-已沟通，03-已报价，04-已成交
     */
    private String flwpStat;
    
    /**
     * 阅读状态：01-已读，02-未读
     */
    private String leadStat;
    
    /**
     * 国家编码
     */
    private String cntryCd;
    
    /**
     * 查询范围询盘开始时间
     */
    private String startDate;
    
    /**
     * 查询范围询盘结束时间
     */
    private String endDate;
    
    private int pageNum;
    
    private int pageSize;
    
    /**
     * 客户姓名
     */
    private String name;
    
    public String getRsrc() {
        return rsrc;
    }

    public void setRsrc(String rsrc) {
        this.rsrc = rsrc;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReqLeadQuery [rsrc=" + rsrc + ", userId=" + userId + ", userName=" + userName + ", ownerId=" + ownerId
                + ", ownerName=" + ownerName + ", leadAuality=" + leadAuality + ", flwpStat=" + flwpStat + ", leadStat="
                + leadStat + ", cntryCd=" + cntryCd + ", startDate=" + startDate + ", endDate=" + endDate + ", pageNum="
                + pageNum + ", pageSize=" + pageSize + ", name=" + name + "]";
    }

}
