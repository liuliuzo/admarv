package com.admarv.saas.fb.model.pageInfo;

/**
 * 
 * @author liuliu
 *
 */
public class PageInfo {

    private String id;
    private int fanCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFanCount() {
        return fanCount;
    }

    public void setFanCount(int fanCount) {
        this.fanCount = fanCount;
    }

    @Override
    public String toString() {
        return "PageInfo [id=" + id + ", fanCount=" + fanCount + "]";
    }
}
