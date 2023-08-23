package com.admarv.saas.dashboard.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class WeekTrendData {
    
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
        return "WeekTrendData [date=" + date + ", count=" + count + "]";
    }

}
