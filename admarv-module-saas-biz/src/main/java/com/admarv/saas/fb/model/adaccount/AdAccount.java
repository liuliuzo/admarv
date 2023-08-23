package com.admarv.saas.fb.model.adaccount;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class AdAccount implements Serializable {

    private static final long serialVersionUID = -6136147780211935861L;

    private String id;
    private String accountId;
    private String name;
    private String amountSpent;
    private String balance;
    private String currency;
    private Integer accountStatus;
    private String spendCap;
    private FundingSourceDetails fundingSourceDetails;
    private PromotePages promotePages;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAmountSpent() {
        return this.amountSpent;
    }

    public void setAmountSpent(String amountSpent) {
        this.amountSpent = amountSpent;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getAccountStatus() {
        return this.accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getSpendCap() {
        return this.spendCap;
    }

    public void setSpendCap(String spendCap) {
        this.spendCap = spendCap;
    }

    public FundingSourceDetails getFundingSourceDetails() {
        return this.fundingSourceDetails;
    }

    public void setFundingSourceDetails(FundingSourceDetails fundingSourceDetails) {
        this.fundingSourceDetails = fundingSourceDetails;
    }

    public PromotePages getPromotePages() {
        return this.promotePages;
    }

    public void setPromotePages(PromotePages promotePages) {
        this.promotePages = promotePages;
    }

    @Override
    public String toString() {
        return "AdAccount [id=" + id + ", accountId=" + accountId + ", name=" + name + ", amountSpent=" + amountSpent
                + ", balance=" + balance + ", currency=" + currency + ", accountStatus=" + accountStatus + ", spendCap="
                + spendCap + ", fundingSourceDetails=" + fundingSourceDetails + ", promotePages=" + promotePages + "]";
    }
    

}
