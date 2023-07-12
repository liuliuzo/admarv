package com.admarv.saas.fb.ads.dto.req;

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

    @Override
    public String toString() {
        return "ReqLeadgenEdit [id=" + id + ", leadStat=" + leadStat + ", flwpStat=" + flwpStat + "]";
    }
}
