package com.admarv.saas.fb.model.adaccount;

import java.io.Serializable;

public class PageData implements Serializable{
    
    private static final long serialVersionUID = -6106197212015868586L;
    
    private String name;
    private String id;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
