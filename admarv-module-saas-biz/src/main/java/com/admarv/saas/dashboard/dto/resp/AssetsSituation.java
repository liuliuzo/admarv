package com.admarv.saas.dashboard.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class AssetsSituation {

    private String fbAcctBal;

    private String tkAcctBal;

    public String getFbAcctBal() {
        return fbAcctBal;
    }

    public void setFbAcctBal(String fbAcctBal) {
        this.fbAcctBal = fbAcctBal;
    }

    public String getTkAcctBal() {
        return tkAcctBal;
    }

    public void setTkAcctBal(String tkAcctBal) {
        this.tkAcctBal = tkAcctBal;
    }

    @Override
    public String toString() {
        return "AssetsSituation [fbAcctBal=" + fbAcctBal + ", tkAcctBal=" + tkAcctBal + "]";
    }
}
