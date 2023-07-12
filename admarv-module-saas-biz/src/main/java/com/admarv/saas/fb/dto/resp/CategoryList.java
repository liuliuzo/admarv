package com.admarv.saas.fb.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class CategoryList {

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
        return "CategoryList [id=" + id + ", name=" + name + "]";
    }

}
