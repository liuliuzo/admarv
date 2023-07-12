package com.admarv.saas.fb.dto.resp.adaccounts;

/**
 * 
 * @author liuliu
 *
 */
public class Paging {

    private Cursors cursors;

    public Cursors getCursors() {
        return this.cursors;
    }

    public void setCursors(Cursors cursors) {
        this.cursors = cursors;
    }

    @Override
    public String toString() {
        return "Paging [cursors=" + cursors + "]";
    }
}
