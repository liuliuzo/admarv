package com.admarv.saas.common.model;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class RatesInfo implements Serializable {

    private static final long serialVersionUID = -8384974521913203507L;

    private String provider;
    private String WARNINGUPGRADETOV6;
    private String terms;
    private String base;
    private String date;
    private Integer timeLastUpdated;
    private Rates rates;

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getWARNINGUPGRADETOV6() {
        return this.WARNINGUPGRADETOV6;
    }

    public void setWARNINGUPGRADETOV6(String WARNINGUPGRADETOV6) {
        this.WARNINGUPGRADETOV6 = WARNINGUPGRADETOV6;
    }

    public String getTerms() {
        return this.terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getBase() {
        return this.base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTimeLastUpdated() {
        return this.timeLastUpdated;
    }

    public void setTimeLastUpdated(Integer timeLastUpdated) {
        this.timeLastUpdated = timeLastUpdated;
    }

    public Rates getRates() {
        return this.rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "RatesInfo [provider=" + provider + ", WARNINGUPGRADETOV6=" + WARNINGUPGRADETOV6 + ", terms=" + terms
                + ", base=" + base + ", date=" + date + ", timeLastUpdated=" + timeLastUpdated + ", rates=" + rates
                + "]";
    }
}
