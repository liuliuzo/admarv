package com.admarv.saas.dashboard.dto.resp;

import java.math.BigDecimal;

/**
 * 获取账户详情信息
 * 
 * @author liuliu
 *
 */
public class FBAssetsSituation {

    private String id;
    
    private String amountSpent;
    
    private String amountSpentCN;
    
    private String balance;
    
    private String balanceCN;
    
    private String currency;
    
    private String acctId;
    
    private String acctStat;
    
    private String acctStatDesc;
    
    private String acctStatDescCN;
    
    private boolean balanceWarning;
    
    private BigDecimal percentage;

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
    
    
    public String getBalanceCN() {
        return balanceCN;
    }
    
    public void setBalanceCN(String balanceCN) {
        this.balanceCN = balanceCN;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public String getAcctStat() {
        return acctStat;
    }
    
    public void setAcctStat(String acctStat) {
        this.acctStat = acctStat;
    }
    
    public String getAcctId() {
        return acctId;
    }
    
    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }
    
    public String getAcctStatDesc() {
        return acctStatDesc;
    }
    
    public void setAcctStatDesc(String acctStatDesc) {
        this.acctStatDesc = acctStatDesc;
    }
    
    public String getAcctStatDescCN() {
        return acctStatDescCN;
    }
    
    public void setAcctStatDescCN(String acctStatDescCN) {
        this.acctStatDescCN = acctStatDescCN;
    }

	public String getAmountSpentCN() {
		return amountSpentCN;
	}

	public void setAmountSpentCN(String amountSpentCN) {
		this.amountSpentCN = amountSpentCN;
	}

	public boolean isBalanceWarning() {
		return balanceWarning;
	}

	public void setBalanceWarning(boolean balanceWarning) {
		this.balanceWarning = balanceWarning;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "FBAssetsSituation [id=" + id + ", amountSpent=" + amountSpent + ", amountSpentCN=" + amountSpentCN
				+ ", balance=" + balance + ", balanceCN=" + balanceCN + ", currency=" + currency + ", acctId=" + acctId
				+ ", acctStat=" + acctStat + ", acctStatDesc=" + acctStatDesc + ", acctStatDescCN=" + acctStatDescCN
				+ ", balanceWarning=" + balanceWarning + ", percentage=" + percentage + "]";
	}

}
