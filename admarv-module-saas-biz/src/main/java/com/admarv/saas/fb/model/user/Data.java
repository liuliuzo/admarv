package com.admarv.saas.fb.model.user;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Data implements Serializable  {
    
    private static final long serialVersionUID = -7049386998528699661L;
    
    private Integer height;
    private String isSilhouette;
    private String url;
    private Integer width;

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getIsSilhouette() {
        return this.isSilhouette;
    }

    public void setIsSilhouette(String isSilhouette) {
        this.isSilhouette = isSilhouette;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Data [height=" + height + ", isSilhouette=" + isSilhouette + ", url=" + url + ", width=" + width + "]";
    }
}
