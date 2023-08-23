package com.admarv.saas.fb.lead.dto.req;

import java.io.Serializable;

/**
 * 询盘编辑请求
 * 
 * @author liuliu
 *
 */
public class ReqLeadgenEdit implements Serializable {
    
    private static final long serialVersionUID = 3161192938258176953L;

    private Integer id;
    /**
     * 阅读状态：01-已读，02-未读
     */
    private String leadStat;

    /**
     * 跟踪状态：01-待跟进，02-已沟通，03-已报价，04-已成交
     */
    private String flwpStat;
    
    /**
     * 质量：01-较好，02-普通，03-较差
     */
    private String leadAuality;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeadStat() {
        return leadStat;
    }

    public void setLeadStat(String leadStat) {
        this.leadStat = leadStat;
    }

    public String getFlwpStat() {
        return flwpStat;
    }

    public void setFlwpStat(String flwpStat) {
        this.flwpStat = flwpStat;
    }
    
    public String getLeadAuality() {
        return leadAuality;
    }

    public void setLeadAuality(String leadAuality) {
        this.leadAuality = leadAuality;
    }

    @Override
    public String toString() {
        return "ReqLeadgenEdit [id=" + id + ", leadStat=" + leadStat + ", flwpStat=" + flwpStat + ", leadAuality="
                + leadAuality + "]";
    }
}
