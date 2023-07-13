package com.admarv.saas.fb.dto.resp.estimate;

public class DailyOutcomesCurve {

    private Integer spend;
    private Integer reach;
    private Integer impressions;
    private Integer actions;

    public Integer getSpend() {
        return this.spend;
    }

    public void setSpend(Integer spend) {
        this.spend = spend;
    }

    public Integer getReach() {
        return this.reach;
    }

    public void setReach(Integer reach) {
        this.reach = reach;
    }

    public Integer getImpressions() {
        return this.impressions;
    }

    public void setImpressions(Integer impressions) {
        this.impressions = impressions;
    }

    public Integer getActions() {
        return this.actions;
    }

    public void setActions(Integer actions) {
        this.actions = actions;
    }

}