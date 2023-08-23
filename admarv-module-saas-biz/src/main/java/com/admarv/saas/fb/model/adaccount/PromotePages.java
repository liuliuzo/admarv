package com.admarv.saas.fb.model.adaccount;

import java.io.Serializable;
import java.util.List;

import com.admarv.saas.fb.model.insights.Paging;

/**
 * 
 * @author liuliu
 *
 */
public class PromotePages implements Serializable {
    
    private static final long serialVersionUID = 2228268948472299158L;
    private List<PageData> data;
    private Paging paging;

    public List<PageData> getData() {
        return this.data;
    }

    public void setData(List<PageData> data) {
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
        return "PromotePages [data=" + data + ", paging=" + paging + "]";
    }

}
