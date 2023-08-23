package com.admarv.saas.fb.model.adaccountinsights;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Data implements Serializable {

    private static final long serialVersionUID = -6957405857556997335L;

    private String spend;

    private String impressions;

    private String clicks;

    private String cpc;

    private String conversions;

    private String costPerConversion;
    
    private String dateStart;
    
    private String dateStop;
    
    private String uniqueClicks;
    
    private String reach;
    
    public String getSpend() {
        return spend;
    }

    public void setSpend(String spend) {
        this.spend = spend;
    }

    public String getImpressions() {
        return impressions;
    }

    public void setImpressions(String impressions) {
        this.impressions = impressions;
    }

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getCpc() {
        return cpc;
    }

    public void setCpc(String cpc) {
        this.cpc = cpc;
    }

    public String getConversions() {
        return conversions;
    }

    public void setConversions(String conversions) {
        this.conversions = conversions;
    }

    public String getCostPerConversion() {
        return costPerConversion;
    }

    public void setCostPerConversion(String costPerConversion) {
        this.costPerConversion = costPerConversion;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateStop() {
        return dateStop;
    }

    public void setDateStop(String dateStop) {
        this.dateStop = dateStop;
    }

	public String getUniqueClicks() {
		return uniqueClicks;
	}

	public void setUniqueClicks(String uniqueClicks) {
		this.uniqueClicks = uniqueClicks;
	}

	public String getReach() {
		return reach;
	}

	public void setReach(String reach) {
		this.reach = reach;
	}

	@Override
	public String toString() {
		return "Data [spend=" + spend + ", impressions=" + impressions + ", clicks=" + clicks + ", cpc=" + cpc
				+ ", conversions=" + conversions + ", costPerConversion=" + costPerConversion + ", dateStart="
				+ dateStart + ", dateStop=" + dateStop + ", uniqueClicks=" + uniqueClicks + ", reach=" + reach + "]";
	}
}
