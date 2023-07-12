package com.admarv.saas.fb.ads.domain.model;

/**
 * 广告庄户查询
 * 
 * @author liuliu
 *
 */
public class Adaccount {
    
    private String id;
    private long amountSpent;
    private long balance;
    private String currency;
    private String accountStatus;
    private String accountId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(long amountSpent) {
        this.amountSpent = amountSpent;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "RespAdaccount [id=" + id + ", amountSpent=" + amountSpent + ", balance=" + balance + ", currency="
                + currency + ", accountStatus=" + accountStatus + ", accountId=" + accountId + "]";
    }
}