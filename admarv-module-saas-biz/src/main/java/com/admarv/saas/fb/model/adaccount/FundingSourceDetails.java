package com.admarv.saas.fb.model.adaccount;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class FundingSourceDetails implements Serializable {

    private static final long serialVersionUID = 4047596074947004178L;
    
    private String id;
    private String displayString;
    private Integer type;

    public String getId() {
      return this.id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getDisplayString() {
      return this.displayString;
    }

    public void setDisplayString(String displayString) {
      this.displayString = displayString;
    }

    public Integer getType() {
      return this.type;
    }

    public void setType(Integer type) {
      this.type = type;
    }

    @Override
    public String toString() {
        return "FundingSourceDetails [id=" + id + ", displayString=" + displayString + ", type=" + type + "]";
    }
}
