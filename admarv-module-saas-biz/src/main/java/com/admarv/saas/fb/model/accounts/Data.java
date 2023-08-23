package com.admarv.saas.fb.model.accounts;

/**
 * 
 * @author liuliu
 *
 */
public class Data {
    private String id;
    private String name;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Data [id=" + id + ", name=" + name + "]";
    }

}
