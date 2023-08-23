package com.admarv.saas.fb.model.campaignsinsights;

import java.io.Serializable;

import com.restfb.Facebook;

/**
 * 广告系列成效
 * 
 * @author liuliu
 *
 */
public class Data implements Serializable {

    private static final long serialVersionUID = -2773435376172314897L;

    @Facebook
    private String accountCurrency;
    @Facebook
    private String accountId;
    @Facebook
    private String accountName;
    @Facebook
    private String actionValues;
    @Facebook
    private int actions;
    @Facebook
    private int activityRecency;
    @Facebook
    private int adClickActions;
    @Facebook
    private String adFormatAsset;
    @Facebook
    private String adId;
    @Facebook
    private int adImpressionActions;
    @Facebook
    private String adName;
    @Facebook
    private String adsetId;
    @Facebook
    private String adsetName;
    @Facebook
    private String ageTargeting;
    @Facebook
    private String attributionSetting;
    @Facebook
    private double auctionBid;
    @Facebook
    private double auctionCompetitiveness;
    @Facebook
    private double auctionMaxCompetitorBid;
    @Facebook
    private String bodyAsset;
    @Facebook
    private String buyingType;
    @Facebook
    private String campaignId;
    @Facebook
    private String campaignName;
    @Facebook
    private double canvasAvgViewPercent;
    @Facebook
    private double canvasAvgViewTime;
    @Facebook
    private int catalogSegmentActions;
    @Facebook
    private double catalogSegmentValue;
    @Facebook
    private double catalogSegmentValueMobilePurchaseRoas;
    @Facebook
    private double catalogSegmentValueOmniPurchaseRoas;
    @Facebook
    private double catalogSegmentValueWebsitePurchaseRoas;
    @Facebook
    private int clicks;
    @Facebook
    private double coarseConversionValue;
    @Facebook
    private String comparisonNode;
    @Facebook
    private double conversionValues;
    @Facebook
    private int conversions;
    @Facebook
    private int convertedProductQuantity;
    @Facebook
    private double convertedProductValue;
    @Facebook
    private double costPer15SecVideoView;
    @Facebook
    private double costPer2SecContinuousVideoView;
    @Facebook
    private double costPerActionType;
    @Facebook
    private double costPerAdClick;
    @Facebook
    private double costPerConversion;
    @Facebook
    private double costPerDdaCountbyConvs;
    @Facebook
    private double costPerInlineLinkClick;
    @Facebook
    private double costPerInlinePostEngagement;
    @Facebook
    private double costPerOneThousandAdImpression;
    @Facebook
    private double costPerOutboundClick;
    @Facebook
    private double costPerThruplay;
    @Facebook
    private double costPerUniqueActionType;
    @Facebook
    private double costPerUniqueClick;
    @Facebook
    private double costPerUniqueConversion;
    @Facebook
    private double costPerUniqueInlineLinkClick;
    @Facebook
    private double costPerUniqueOutboundClick;
    @Facebook
    private String country;
    @Facebook
    private double cpc;
    @Facebook
    private double cpm;
    @Facebook
    private double cpp;
    @Facebook
    private long createdTime;
    @Facebook
    private double ctr;

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getActionValues() {
        return actionValues;
    }

    public void setActionValues(String actionValues) {
        this.actionValues = actionValues;
    }

    public int getActions() {
        return actions;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public int getActivityRecency() {
        return activityRecency;
    }

    public void setActivityRecency(int activityRecency) {
        this.activityRecency = activityRecency;
    }

    public int getAdClickActions() {
        return adClickActions;
    }

    public void setAdClickActions(int adClickActions) {
        this.adClickActions = adClickActions;
    }

    public String getAdFormatAsset() {
        return adFormatAsset;
    }

    public void setAdFormatAsset(String adFormatAsset) {
        this.adFormatAsset = adFormatAsset;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public int getAdImpressionActions() {
        return adImpressionActions;
    }

    public void setAdImpressionActions(int adImpressionActions) {
        this.adImpressionActions = adImpressionActions;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdsetId() {
        return adsetId;
    }

    public void setAdsetId(String adsetId) {
        this.adsetId = adsetId;
    }

    public String getAdsetName() {
        return adsetName;
    }

    public void setAdsetName(String adsetName) {
        this.adsetName = adsetName;
    }

    public String getAgeTargeting() {
        return ageTargeting;
    }

    public void setAgeTargeting(String ageTargeting) {
        this.ageTargeting = ageTargeting;
    }

    public String getAttributionSetting() {
        return attributionSetting;
    }

    public void setAttributionSetting(String attributionSetting) {
        this.attributionSetting = attributionSetting;
    }

    public double getAuctionBid() {
        return auctionBid;
    }

    public void setAuctionBid(double auctionBid) {
        this.auctionBid = auctionBid;
    }

    public double getAuctionCompetitiveness() {
        return auctionCompetitiveness;
    }

    public void setAuctionCompetitiveness(double auctionCompetitiveness) {
        this.auctionCompetitiveness = auctionCompetitiveness;
    }

    public double getAuctionMaxCompetitorBid() {
        return auctionMaxCompetitorBid;
    }

    public void setAuctionMaxCompetitorBid(double auctionMaxCompetitorBid) {
        this.auctionMaxCompetitorBid = auctionMaxCompetitorBid;
    }

    public String getBodyAsset() {
        return bodyAsset;
    }

    public void setBodyAsset(String bodyAsset) {
        this.bodyAsset = bodyAsset;
    }

    public String getBuyingType() {
        return buyingType;
    }

    public void setBuyingType(String buyingType) {
        this.buyingType = buyingType;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public double getCanvasAvgViewPercent() {
        return canvasAvgViewPercent;
    }

    public void setCanvasAvgViewPercent(double canvasAvgViewPercent) {
        this.canvasAvgViewPercent = canvasAvgViewPercent;
    }

    public double getCanvasAvgViewTime() {
        return canvasAvgViewTime;
    }

    public void setCanvasAvgViewTime(double canvasAvgViewTime) {
        this.canvasAvgViewTime = canvasAvgViewTime;
    }

    public int getCatalogSegmentActions() {
        return catalogSegmentActions;
    }

    public void setCatalogSegmentActions(int catalogSegmentActions) {
        this.catalogSegmentActions = catalogSegmentActions;
    }

    public double getCatalogSegmentValue() {
        return catalogSegmentValue;
    }

    public void setCatalogSegmentValue(double catalogSegmentValue) {
        this.catalogSegmentValue = catalogSegmentValue;
    }

    public double getCatalogSegmentValueMobilePurchaseRoas() {
        return catalogSegmentValueMobilePurchaseRoas;
    }

    public void setCatalogSegmentValueMobilePurchaseRoas(double catalogSegmentValueMobilePurchaseRoas) {
        this.catalogSegmentValueMobilePurchaseRoas = catalogSegmentValueMobilePurchaseRoas;
    }

    public double getCatalogSegmentValueOmniPurchaseRoas() {
        return catalogSegmentValueOmniPurchaseRoas;
    }

    public void setCatalogSegmentValueOmniPurchaseRoas(double catalogSegmentValueOmniPurchaseRoas) {
        this.catalogSegmentValueOmniPurchaseRoas = catalogSegmentValueOmniPurchaseRoas;
    }

    public double getCatalogSegmentValueWebsitePurchaseRoas() {
        return catalogSegmentValueWebsitePurchaseRoas;
    }

    public void setCatalogSegmentValueWebsitePurchaseRoas(double catalogSegmentValueWebsitePurchaseRoas) {
        this.catalogSegmentValueWebsitePurchaseRoas = catalogSegmentValueWebsitePurchaseRoas;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public double getCoarseConversionValue() {
        return coarseConversionValue;
    }

    public void setCoarseConversionValue(double coarseConversionValue) {
        this.coarseConversionValue = coarseConversionValue;
    }

    public String getComparisonNode() {
        return comparisonNode;
    }

    public void setComparisonNode(String comparisonNode) {
        this.comparisonNode = comparisonNode;
    }

    public double getConversionValues() {
        return conversionValues;
    }

    public void setConversionValues(double conversionValues) {
        this.conversionValues = conversionValues;
    }

    public int getConversions() {
        return conversions;
    }

    public void setConversions(int conversions) {
        this.conversions = conversions;
    }

    public int getConvertedProductQuantity() {
        return convertedProductQuantity;
    }

    public void setConvertedProductQuantity(int convertedProductQuantity) {
        this.convertedProductQuantity = convertedProductQuantity;
    }

    public double getConvertedProductValue() {
        return convertedProductValue;
    }

    public void setConvertedProductValue(double convertedProductValue) {
        this.convertedProductValue = convertedProductValue;
    }

    public double getCostPer15SecVideoView() {
        return costPer15SecVideoView;
    }

    public void setCostPer15SecVideoView(double costPer15SecVideoView) {
        this.costPer15SecVideoView = costPer15SecVideoView;
    }

    public double getCostPer2SecContinuousVideoView() {
        return costPer2SecContinuousVideoView;
    }

    public void setCostPer2SecContinuousVideoView(double costPer2SecContinuousVideoView) {
        this.costPer2SecContinuousVideoView = costPer2SecContinuousVideoView;
    }

    public double getCostPerActionType() {
        return costPerActionType;
    }

    public void setCostPerActionType(double costPerActionType) {
        this.costPerActionType = costPerActionType;
    }

    public double getCostPerAdClick() {
        return costPerAdClick;
    }

    public void setCostPerAdClick(double costPerAdClick) {
        this.costPerAdClick = costPerAdClick;
    }

    public double getCostPerConversion() {
        return costPerConversion;
    }

    public void setCostPerConversion(double costPerConversion) {
        this.costPerConversion = costPerConversion;
    }

    public double getCostPerDdaCountbyConvs() {
        return costPerDdaCountbyConvs;
    }

    public void setCostPerDdaCountbyConvs(double costPerDdaCountbyConvs) {
        this.costPerDdaCountbyConvs = costPerDdaCountbyConvs;
    }

    public double getCostPerInlineLinkClick() {
        return costPerInlineLinkClick;
    }

    public void setCostPerInlineLinkClick(double costPerInlineLinkClick) {
        this.costPerInlineLinkClick = costPerInlineLinkClick;
    }

    public double getCostPerInlinePostEngagement() {
        return costPerInlinePostEngagement;
    }

    public void setCostPerInlinePostEngagement(double costPerInlinePostEngagement) {
        this.costPerInlinePostEngagement = costPerInlinePostEngagement;
    }

    public double getCostPerOneThousandAdImpression() {
        return costPerOneThousandAdImpression;
    }

    public void setCostPerOneThousandAdImpression(double costPerOneThousandAdImpression) {
        this.costPerOneThousandAdImpression = costPerOneThousandAdImpression;
    }

    public double getCostPerOutboundClick() {
        return costPerOutboundClick;
    }

    public void setCostPerOutboundClick(double costPerOutboundClick) {
        this.costPerOutboundClick = costPerOutboundClick;
    }

    public double getCostPerThruplay() {
        return costPerThruplay;
    }

    public void setCostPerThruplay(double costPerThruplay) {
        this.costPerThruplay = costPerThruplay;
    }

    public double getCostPerUniqueActionType() {
        return costPerUniqueActionType;
    }

    public void setCostPerUniqueActionType(double costPerUniqueActionType) {
        this.costPerUniqueActionType = costPerUniqueActionType;
    }

    public double getCostPerUniqueClick() {
        return costPerUniqueClick;
    }

    public void setCostPerUniqueClick(double costPerUniqueClick) {
        this.costPerUniqueClick = costPerUniqueClick;
    }

    public double getCostPerUniqueConversion() {
        return costPerUniqueConversion;
    }

    public void setCostPerUniqueConversion(double costPerUniqueConversion) {
        this.costPerUniqueConversion = costPerUniqueConversion;
    }

    public double getCostPerUniqueInlineLinkClick() {
        return costPerUniqueInlineLinkClick;
    }

    public void setCostPerUniqueInlineLinkClick(double costPerUniqueInlineLinkClick) {
        this.costPerUniqueInlineLinkClick = costPerUniqueInlineLinkClick;
    }

    public double getCostPerUniqueOutboundClick() {
        return costPerUniqueOutboundClick;
    }

    public void setCostPerUniqueOutboundClick(double costPerUniqueOutboundClick) {
        this.costPerUniqueOutboundClick = costPerUniqueOutboundClick;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getCpc() {
        return cpc;
    }

    public void setCpc(double cpc) {
        this.cpc = cpc;
    }

    public double getCpm() {
        return cpm;
    }

    public void setCpm(double cpm) {
        this.cpm = cpm;
    }

    public double getCpp() {
        return cpp;
    }

    public void setCpp(double cpp) {
        this.cpp = cpp;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public double getCtr() {
        return ctr;
    }

    public void setCtr(double ctr) {
        this.ctr = ctr;
    }

    @Override
    public String toString() {
        return "CampaignsInsights [accountCurrency=" + accountCurrency + ", accountId=" + accountId + ", accountName="
                + accountName + ", actionValues=" + actionValues + ", actions=" + actions + ", activityRecency="
                + activityRecency + ", adClickActions=" + adClickActions + ", adFormatAsset=" + adFormatAsset
                + ", adId=" + adId + ", adImpressionActions=" + adImpressionActions + ", adName=" + adName
                + ", adsetId=" + adsetId + ", adsetName=" + adsetName + ", ageTargeting=" + ageTargeting
                + ", attributionSetting=" + attributionSetting + ", auctionBid=" + auctionBid
                + ", auctionCompetitiveness=" + auctionCompetitiveness + ", auctionMaxCompetitorBid="
                + auctionMaxCompetitorBid + ", bodyAsset=" + bodyAsset + ", buyingType=" + buyingType + ", campaignId="
                + campaignId + ", campaignName=" + campaignName + ", canvasAvgViewPercent=" + canvasAvgViewPercent
                + ", canvasAvgViewTime=" + canvasAvgViewTime + ", catalogSegmentActions=" + catalogSegmentActions
                + ", catalogSegmentValue=" + catalogSegmentValue + ", catalogSegmentValueMobilePurchaseRoas="
                + catalogSegmentValueMobilePurchaseRoas + ", catalogSegmentValueOmniPurchaseRoas="
                + catalogSegmentValueOmniPurchaseRoas + ", catalogSegmentValueWebsitePurchaseRoas="
                + catalogSegmentValueWebsitePurchaseRoas + ", clicks=" + clicks + ", coarseConversionValue="
                + coarseConversionValue + ", comparisonNode=" + comparisonNode + ", conversionValues="
                + conversionValues + ", conversions=" + conversions + ", convertedProductQuantity="
                + convertedProductQuantity + ", convertedProductValue=" + convertedProductValue
                + ", costPer15SecVideoView=" + costPer15SecVideoView + ", costPer2SecContinuousVideoView="
                + costPer2SecContinuousVideoView + ", costPerActionType=" + costPerActionType + ", costPerAdClick="
                + costPerAdClick + ", costPerConversion=" + costPerConversion + ", costPerDdaCountbyConvs="
                + costPerDdaCountbyConvs + ", costPerInlineLinkClick=" + costPerInlineLinkClick
                + ", costPerInlinePostEngagement=" + costPerInlinePostEngagement + ", costPerOneThousandAdImpression="
                + costPerOneThousandAdImpression + ", costPerOutboundClick=" + costPerOutboundClick
                + ", costPerThruplay=" + costPerThruplay + ", costPerUniqueActionType=" + costPerUniqueActionType
                + ", costPerUniqueClick=" + costPerUniqueClick + ", costPerUniqueConversion=" + costPerUniqueConversion
                + ", costPerUniqueInlineLinkClick=" + costPerUniqueInlineLinkClick + ", costPerUniqueOutboundClick="
                + costPerUniqueOutboundClick + ", country=" + country + ", cpc=" + cpc + ", cpm=" + cpm + ", cpp=" + cpp
                + ", createdTime=" + createdTime + ", ctr=" + ctr + "]";
    }

}
