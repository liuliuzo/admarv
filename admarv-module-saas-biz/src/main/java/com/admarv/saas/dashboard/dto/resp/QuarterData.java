package com.admarv.saas.dashboard.dto.resp;

public class QuarterData {

    private String date;
    private int count;
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "QuarterData [date=" + date + ", count=" + count + "]";
    }
}
