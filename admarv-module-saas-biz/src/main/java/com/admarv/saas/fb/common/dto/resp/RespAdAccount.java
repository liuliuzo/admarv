package com.admarv.saas.fb.common.dto.resp;

import java.io.Serializable;

import com.admarv.saas.fb.model.adaccount.PromotePages;

/**
 * 
 * @author liuliu
 *
 */
public class RespAdAccount implements Serializable {

    private static final long serialVersionUID = -6136147780211935861L;

    private String id;
    private String accountId;
    private String name;
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

    public PromotePages getPromotePages() {
        return promotePages;
    }

    public void setPromotePages(PromotePages promotePages) {
        this.promotePages = promotePages;
    }

    @Override
    public String toString() {
        return "RespAdAccount [id=" + id + ", accountId=" + accountId + ", name=" + name + ", promotePages="
                + promotePages + "]";
    }
}
