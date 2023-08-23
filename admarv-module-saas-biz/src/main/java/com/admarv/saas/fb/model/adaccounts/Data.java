package com.admarv.saas.fb.model.adaccounts;

/**
 * 
 * @author liuliu
 *
 */
public class Data {

    private String accountId;
    private String id;

    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Data [accountId=" + accountId + ", id=" + id + "]";
    }
}
