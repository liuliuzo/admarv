package com.admarv.saas.fb.dto.resp.estimate;

import java.util.List;

public class Data {
    
    private BidEstimate bidEstimate;
    private List<DailyOutcomesCurve> dailyOutcomesCurve;
    private Integer estimateDau;
    private Integer estimateMau;
    private String estimateReady;

    public BidEstimate getBidEstimate() {
        return this.bidEstimate;
    }

    public void setBidEstimate(BidEstimate bidEstimate) {
        this.bidEstimate = bidEstimate;
    }

    public List<DailyOutcomesCurve> getDailyOutcomesCurve() {
        return this.dailyOutcomesCurve;
    }

    public void setDailyOutcomesCurve(List<DailyOutcomesCurve> dailyOutcomesCurve) {
        this.dailyOutcomesCurve = dailyOutcomesCurve;
    }

    public Integer getEstimateDau() {
        return this.estimateDau;
    }

    public void setEstimateDau(Integer estimateDau) {
        this.estimateDau = estimateDau;
    }

    public Integer getEstimateMau() {
        return this.estimateMau;
    }

    public void setEstimateMau(Integer estimateMau) {
        this.estimateMau = estimateMau;
    }

    public String getEstimateReady() {
        return this.estimateReady;
    }

    public void setEstimateReady(String estimateReady) {
        this.estimateReady = estimateReady;
    }

}
