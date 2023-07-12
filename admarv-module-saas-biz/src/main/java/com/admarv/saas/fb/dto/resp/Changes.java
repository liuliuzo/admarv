package com.admarv.saas.fb.dto.resp;

public class Changes {
    private String field;
    private Value value;

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
