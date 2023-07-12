package com.admarv.saas.fb.ads.dto.resp;

/**
 * 广告系列列表响应
 * 
 * @author liuliu
 *
 */
public class RespCampaign {
    
    private String id;
    private String nam;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    @Override
    public String toString() {
        return "RespCampaign [id=" + id + ", nam=" + nam + "]";
    }
}
