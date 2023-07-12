package com.admarv.saas.fb.dto.resp.insights;

import java.util.List;

/**
 * 
 * @author liuliu
 *
 */
public class Insights {

    private List<Data>  data;
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
        return "Insights [data=" + data + ", paging=" + paging + "]";
    }

}
