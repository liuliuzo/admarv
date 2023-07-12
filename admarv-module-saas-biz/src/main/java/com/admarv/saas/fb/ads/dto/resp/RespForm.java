package com.admarv.saas.fb.ads.dto.resp;

import java.util.List;

/**
 * 
 * @author liuliu
 *
 */
public class RespForm {
    
    private String id;
    private String name;
    private String createdTime;
    private String cusomizedTcpaContent;
    private String expiredLeadsCount;
    private String followUpActionText;
    private String followUpActionUrl;
    private String isContinuedFlow;
    private String leadgenExportCsvUrl;
    private String leadsCount;
    private String locale;
    private String page;
    private String pageId;
    private String privacyPolicyUrl;
    private List qualifiers;
    private String tcpaCompliance;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCusomizedTcpaContent(String cusomizedTcpaContent) {
        this.cusomizedTcpaContent = cusomizedTcpaContent;
    }

    public String getCusomizedTcpaContent() {
        return cusomizedTcpaContent;
    }

    public void setExpiredLeadsCount(String expiredLeadsCount) {
        this.expiredLeadsCount = expiredLeadsCount;
    }

    public String getExpiredLeadsCount() {
        return expiredLeadsCount;
    }

    public void setFollowUpActionText(String followUpActionText) {
        this.followUpActionText = followUpActionText;
    }

    public String getFollowUpActionText() {
        return followUpActionText;
    }

    public void setFollowUpActionUrl(String followUpActionUrl) {
        this.followUpActionUrl = followUpActionUrl;
    }

    public String getFollowUpActionUrl() {
        return followUpActionUrl;
    }

    public void setIsContinuedFlow(String isContinuedFlow) {
        this.isContinuedFlow = isContinuedFlow;
    }

    public String getIsContinuedFlow() {
        return isContinuedFlow;
    }

    public void setLeadgenExportCsvUrl(String leadgenExportCsvUrl) {
        this.leadgenExportCsvUrl = leadgenExportCsvUrl;
    }

    public String getLeadgenExportCsvUrl() {
        return leadgenExportCsvUrl;
    }

    public void setLeadsCount(String leadsCount) {
        this.leadsCount = leadsCount;
    }

    public String getLeadsCount() {
        return leadsCount;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPrivacyPolicyUrl(String privacyPolicyUrl) {
        this.privacyPolicyUrl = privacyPolicyUrl;
    }

    public String getPrivacyPolicyUrl() {
        return privacyPolicyUrl;
    }

    public void setQualifiers(List qualifiers) {
        this.qualifiers = qualifiers;
    }

    public List getQualifiers() {
        return qualifiers;
    }

    public void setTcpaCompliance(String tcpaCompliance) {
        this.tcpaCompliance = tcpaCompliance;
    }

    public String getTcpaCompliance() {
        return tcpaCompliance;
    }

    @Override
    public String toString() {
        return "RepForm [id=" + id + ", name=" + name + ", createdTime=" + createdTime + ", cusomizedTcpaContent="
                + cusomizedTcpaContent + ", expiredLeadsCount=" + expiredLeadsCount + ", followUpActionText="
                + followUpActionText + ", followUpActionUrl=" + followUpActionUrl + ", isContinuedFlow="
                + isContinuedFlow + ", leadgenExportCsvUrl=" + leadgenExportCsvUrl + ", leadsCount=" + leadsCount
                + ", locale=" + locale + ", page=" + page + ", pageId=" + pageId + ", privacyPolicyUrl="
                + privacyPolicyUrl + ", qualifiers=" + qualifiers + ", tcpaCompliance=" + tcpaCompliance + "]";
    }
}
