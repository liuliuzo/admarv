package com.admarv.saas.fb.dto.resp.insights;

/**
 * 
 * @author liuliu
 *
 */
public class Data {

    //账户ID
    private String accountId;
    //开始日期
    private String dateStart;
    //结束日期
    private String dateStop;
    //展示次数
    private String impressions;
    //花费
    private String spend;

    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDateStart() {
        return this.dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateStop() {
        return this.dateStop;
    }

    public void setDateStop(String dateStop) {
        this.dateStop = dateStop;
    }

    public String getImpressions() {
        return this.impressions;
    }

    public void setImpressions(String impressions) {
        this.impressions = impressions;
    }

    public String getSpend() {
        return this.spend;
    }

    public void setSpend(String spend) {
        this.spend = spend;
    }

    @Override
    public String toString() {
        return "Data [accountId=" + accountId + ", dateStart=" + dateStart + ", dateStop=" + dateStop + ", impressions="
                + impressions + ", spend=" + spend + "]";
    }
}
