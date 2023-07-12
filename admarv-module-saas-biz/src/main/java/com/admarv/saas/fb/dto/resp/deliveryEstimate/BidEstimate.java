package com.admarv.saas.fb.dto.resp.deliveryEstimate;

public class BidEstimate {

    private Integer minBid;
    private Integer medianBid;
    private Integer maxBid;

    public Integer getMinBid() {
        return this.minBid;
    }

    public void setMinBid(Integer minBid) {
        this.minBid = minBid;
    }

    public Integer getMedianBid() {
        return this.medianBid;
    }

    public void setMedianBid(Integer medianBid) {
        this.medianBid = medianBid;
    }

    public Integer getMaxBid() {
        return this.maxBid;
    }

    public void setMaxBid(Integer maxBid) {
        this.maxBid = maxBid;
    }

}