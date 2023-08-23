package com.admarv.saas.dashboard.dto.resp;

public class MonthlyData {

    private String date;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MonthlyData [date=" + date + ", count=" + count + "]";
    }
}
