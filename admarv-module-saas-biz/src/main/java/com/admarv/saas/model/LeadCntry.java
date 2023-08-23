package com.admarv.saas.model;

/**
 * 
 * @author liuliu
 *
 */
public class LeadCntry {
    
    private String regn;
    private int count;

    public String getRegn() {
        return regn;
    }

    public void setRegn(String regn) {
        this.regn = regn;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "LeadCntry [regn=" + regn + ", count=" + count + "]";
    }
}
