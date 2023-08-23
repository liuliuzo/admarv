package com.admarv.saas.fb.lead.domain.model;

/**
 * 广告庄户查询
 * 
 * @author liuliu
 *
 */
public class AdAccount {
    
    private String id;
    private String name;
    private String accountId;
    private String amountSpent;
    private String balance;
    private String currency;
    private String accountStatus;
    private String spendCap;
    private String minDailyBudget;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(String amountSpent) {
        this.amountSpent = amountSpent;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpendCap() {
        return spendCap;
    }

    public void setSpendCap(String spendCap) {
        this.spendCap = spendCap;
    }

	public String getMinDailyBudget() {
		return minDailyBudget;
	}

	public void setMinDailyBudget(String minDailyBudget) {
		this.minDailyBudget = minDailyBudget;
	}

	@Override
	public String toString() {
		return "AdAccount [id=" + id + ", name=" + name + ", accountId=" + accountId + ", amountSpent=" + amountSpent
				+ ", balance=" + balance + ", currency=" + currency + ", accountStatus=" + accountStatus + ", spendCap="
				+ spendCap + ", minDailyBudget=" + minDailyBudget + "]";
	}
}