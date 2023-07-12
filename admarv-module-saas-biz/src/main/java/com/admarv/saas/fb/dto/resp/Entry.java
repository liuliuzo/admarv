package com.admarv.saas.fb.dto.resp;

import java.util.List;

public class Entry {
    private Integer id;
    private Integer time;
    private List<Changes> changes;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTime() {
        return this.time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<Changes> getChanges() {
        return this.changes;
    }

    public void setChanges(List<Changes> changes) {
        this.changes = changes;
    }
}
