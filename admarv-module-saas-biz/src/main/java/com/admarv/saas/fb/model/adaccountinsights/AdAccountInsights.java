package com.admarv.saas.fb.model.adaccountinsights;

import java.util.List;

import com.admarv.saas.fb.model.insights.Paging;

/**
 * 
 * @author liuliu
 *
 */
public class AdAccountInsights {
    private List<Data>  data;
    private Paging paging;
    
    /**
     * domain data
     */
    private String date;
    private String spend;
    
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSpend() {
		return spend;
	}

	public void setSpend(String spend) {
		this.spend = spend;
	}

	@Override
	public String toString() {
		return "AdAccountInsights [data=" + data + ", paging=" + paging + ", date=" + date + ", spend=" + spend + "]";
	}
	
}
