package com.ssm.maven.core.entity;

public class CDBean {
    private double cd;
    private int hour;

    public double getCd() {
        return cd;
    }

    public void setCd(double cd) {
        this.cd = cd;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "CDBean{" +
                "cd=" + cd +
                ", hour=" + hour +
                '}';
    }
}
