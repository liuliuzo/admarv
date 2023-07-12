package com.admarv.saas.fb.page.dto.resp;

/**
 * /admarv/pages
 * 
 * @author liuliu
 *
 */
public class Pages {
    private String pageId;
    private String pageName;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    @Override
    public String toString() {
        return "RspAccount [pageId=" + pageId + ", pageName=" + pageName + "]";
    }

}
