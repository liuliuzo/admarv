package com.admarv.saas.fb.model.user;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class FBUser implements Serializable {

    private static final long serialVersionUID = 5274821622882814005L;

    private String id;
    private String name;
    private Picture picture;

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

    public Picture getPicture() {
        return this.picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "FBUser [id=" + id + ", name=" + name + ", picture=" + picture + "]";
    } 

}
