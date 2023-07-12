package com.admarv.saas.fb.dto.resp;

import java.util.List;

/**
 * /adaccounts 接口响应
 * 
 * @author liuliu
 *
 */
public class Accounts {
    private List<Data> data;
    private Paging paging;

    public List<Data> getData() {
        return this.data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return this.paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    @Override
    public String toString() {
        return "Accounts [data=" + data + ", paging=" + paging + "]";
    }
}
